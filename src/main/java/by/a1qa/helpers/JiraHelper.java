package by.a1qa.helpers;

import by.a1qa.model.Report;
import net.rcarz.jiraclient.*;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static by.a1qa.helpers.CommonData.AQA_JIRA_HOST;

/**
 * Created by alexei.khilchuk on 06/02/2017.
 */
public class JiraHelper {
    static Logger LOG = LoggerFactory.getLogger(JiraHelper.class.getName());

    public static JiraClient getAqaJiraClient(Report report){
          return getJiraClient(AQA_JIRA_HOST,
                  report.getPerson(), report.getPassword());
    }

    private static JiraClient getJiraClient(String host, String login, String password){
        JiraClient client;
        BasicCredentials jiraCredentials = new BasicCredentials(login, password);
        try {
            client = new JiraClient(host, jiraCredentials);
            client.getProjects();
        } catch (JiraException e) {
            LOG.error("Unable to login into A1QA Jira", e);
            client = null;
        }
        return client;
    }

    public static void sendWorklogToJira(JiraClient client, String issueKey,
                                         String time, String comment)  throws JiraException {

        TimeZone tz = TimeZone.getTimeZone("UTC+03:00");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        RestClient restClient = client.getRestClient();
        JSONObject fieldmap = new JSONObject();
        fieldmap.put("started", nowAsISO + ".000+0300");
        fieldmap.put("timeSpentSeconds", Long.valueOf(time));
        fieldmap.put("comment", comment);
        String url = String.format("/rest/api/%1s/issue/%2s/worklog", "latest", client.getIssue(issueKey).getKey());

        try {
            restClient.post(url, fieldmap);
        } catch (Exception ex) {
            throw new JiraException("Failed add worklog to issue " + issueKey, ex);
        }
    }
}
