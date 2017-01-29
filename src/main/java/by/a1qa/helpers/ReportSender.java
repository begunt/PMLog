package by.a1qa.helpers;


import by.a1qa.model.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;

import static by.a1qa.helpers.CommonData.ALL_REPORTS_TAB_NAME;
import static by.a1qa.helpers.CommonData.POSITION_CACHE_RESET_REPORTS_INTERVAL;
import static by.a1qa.helpers.CommonData.REPORT_SEND_EXEC_INTERVAL;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by alexei.khilchuk on 27/01/2017.
 */
public class ReportSender {
    static Logger LOG = LoggerFactory.getLogger(ReportSender.class.getName());

    private static BlockingQueue<Report> reportsQueue = new LinkedBlockingQueue<Report>();
    private static final ScheduledExecutorService executorService =
            Executors.newScheduledThreadPool(1);
    private static boolean initialized = false;
    private static int numOfProcessedReports = 0;

    private static void init(){
        if (!initialized) {
            LOG.info("Initializing send report scheduler...");
            initialized = true;
            final Runnable sender = new Runnable() {
                public void run() {
                    if (!reportsQueue.isEmpty()){
                        Report report;
                        try {
                            report = reportsQueue.take();
                        } catch (InterruptedException e) {
                            try {
                                LOG.error("There was an error during pulling report from the queue", e);
                                Thread.sleep(750);
                            } catch (InterruptedException ignored) {}
                            try {
                                report = reportsQueue.take();
                            }  catch (InterruptedException e1) {
                                report = new Report();
                                report.setPerson("ERROR DURING PULLING FROM QUEUE. PLEASE REPORT TO DEV.");
                                LOG.error("There was an error during re-pulling report from the queue", e1);
                            }
                        }
                        try {
                            SpreadsheetHelper.submitReport(report, ALL_REPORTS_TAB_NAME, true);
                            SpreadsheetHelper.submitReport(report, report.getProduct(),false);
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
            executorService.scheduleAtFixedRate(sender, 10, REPORT_SEND_EXEC_INTERVAL, SECONDS);
            LOG.info("Initializing send report scheduler DONE");
        }
    }

    public static void addReportToQueue(Report report) {
        ReportSender.init();
        LOG.info(String.format("Adding report to queue: \n %s", report.toString()));
        try {
            reportsQueue.put(report);
        } catch (InterruptedException e) {
            try {
                LOG.error("There was an error during adding report to the queue. Trying again...", e);
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
            addReportToQueue(report);
        }
    }
}
