package by.a1qa.helpers;


import by.a1qa.controller.ReportController;
import by.a1qa.model.Report;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import javafx.util.Pair;
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

    private static BlockingQueue<Report> reportsQueue = new LinkedBlockingQueue<Report>();

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
                        Report report;

                        try {
                            LOG.info("Taking report from the queue...");
                            synchronized (ReportSender.class){
                                report = reportsQueue.take();
                            }
                        } catch (InterruptedException e) {
                            try {
                                LOG.error("There was an error during pulling report from the queue", e);
                                Thread.sleep(750);
                            } catch (InterruptedException ignored) {}
                            try {
                                report = reportsQueue.take();
                            }  catch (InterruptedException e1) {
                                Report reportError = new Report();
                                reportError.setPerson("ERROR DURING PULLING FROM QUEUE. PLEASE REPORT TO DEV.");

                                report = reportError;
                                LOG.error("There was an error during re-pulling report from the queue", e1);
                            }
                        }
                        report.setProduct(report.getProduct().replace('0', 'o'));

                        for (int attempts = 0; attempts < REPORT_RESEND_ATTEMPTS_AMOUNT; attempts++){
                            boolean isSuccess = false;
                            try {
                                SpreadsheetHelper.submitReport(report, ALL_REPORTS_TAB_NAME, true);
                                //SpreadsheetHelper.submitReport(report, report.getProduct(),false); // for submitting into separate tab
                                isSuccess = true;
                            } catch (InvocationTargetException | IllegalAccessException | IOException e1) {
                                LOG.error("There was an error during submitting report to the Spreadsheet - ATTEMPT TO RESEND", e1);
                            }
                            if (isSuccess) break;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ignored) {}
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

    public synchronized static void addReportToQueue(Report report) {
        ReportSender.init();
        LOG.info(String.format("Adding report to queue: \n %s", report.getPerson()));
        try {
            synchronized (ReportSender.class){
                reportsQueue.put(report);
            }
        } catch (InterruptedException e) {
            try {
                LOG.error("There was an error during adding report to the queue. Trying again...", e);
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
            addReportToQueue(report);
        }
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        if (taskHandle != null)
            taskHandle.cancel(true);
        executorService.shutdown();
    }
}
