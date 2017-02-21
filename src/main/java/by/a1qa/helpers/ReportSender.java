package by.a1qa.helpers;


import by.a1qa.controller.ReportController;
import by.a1qa.model.Report;
import javafx.util.Pair;
import net.rcarz.jiraclient.JiraClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.*;

import static by.a1qa.helpers.CommonData.*;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by alexei.khilchuk on 27/01/2017.
 */
public class ReportSender extends ReportController implements ApplicationListener<ContextClosedEvent> {
    static Logger LOG = LoggerFactory.getLogger(ReportSender.class.getName());

    static ScheduledFuture<?> taskHandle = null;
    private static final ScheduledExecutorService executorService =
            Executors.newScheduledThreadPool(1);

    private static BlockingQueue<Pair<Report, JiraClient>> reportsQueue = new LinkedBlockingQueue<Pair<Report, JiraClient>>();

    private static boolean initialized = false;
    private static int numOfProcessedReports = 0;

    private static void init(){
        if (!initialized) {
            LOG.info("Initializing send report scheduler...");
            initialized = true;
            final Runnable sender = new Runnable() {
                public void run() {
                    LOG.info("Checking reports queue");
                    if (!reportsQueue.isEmpty()){
                        LOG.info("Reports queue not empty. Processing sending...");
                        Pair<Report, JiraClient> reportAndClientPair;

                        try {
                            LOG.info("Taking report from the queue...");
                            synchronized (ReportSender.class){
                                reportAndClientPair = reportsQueue.take();
                            }
                        } catch (InterruptedException e) {
                            try {
                                LOG.error("There was an error during pulling report from the queue", e);
                                Thread.sleep(750);
                            } catch (InterruptedException ignored) {}
                            try {
                                reportAndClientPair = reportsQueue.take();
                            }  catch (InterruptedException e1) {
                                Report report = new Report();
                                report.setPerson("ERROR DURING PULLING FROM QUEUE. PLEASE REPORT TO DEV.");

                                reportAndClientPair = new Pair<>(report, null);
                                LOG.error("There was an error during re-pulling report from the queue", e1);
                            }
                        }
                        try {
                            Report report = reportAndClientPair.getKey();
                            report.setProduct(report.getProduct().replace('0', 'o'));
                            SpreadsheetHelper.submitReport(report, ALL_REPORTS_TAB_NAME, true);
                            //SpreadsheetHelper.submitReport(report, report.getProduct(),false);
                        } catch (InvocationTargetException | IllegalAccessException | IOException e) {
                            LOG.error("There was an error during submitting report to the Spreadsheet", e);
                        }
                        numOfProcessedReports++;
                        if (numOfProcessedReports >= POSITION_CACHE_RESET_REPORTS_INTERVAL && reportsQueue.isEmpty()){
                            SpreadsheetHelper.clearColumnPositionCache();
                            numOfProcessedReports = 0;
                        }
                    }

                }
            };
            taskHandle = executorService.scheduleAtFixedRate(sender, 10, REPORT_SEND_EXEC_INTERVAL, SECONDS);
            LOG.info("Initializing send report scheduler DONE");
        }
    }

    public synchronized static void addReportToQueue(Report report, JiraClient jiraClient) {
        ReportSender.init();
        LOG.info(String.format("Adding report to queue: \n %s", report.getPerson()));
        try {
            synchronized (ReportSender.class){
                reportsQueue.put(new Pair<>(report, jiraClient));
            }
        } catch (InterruptedException e) {
            try {
                LOG.error("There was an error during adding report to the queue. Trying again...", e);
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
            addReportToQueue(report, jiraClient);
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        if (taskHandle != null)
            taskHandle.cancel(true);
        executorService.shutdown();
    }
}
