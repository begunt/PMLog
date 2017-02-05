package by.a1qa.helpers;

import by.a1qa.model.Report;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static by.a1qa.helpers.CommonData.AQA_JIRA_HOST;

/**
 * Created by alexei.khilchuk on 06/02/2017.
 */
public class JiraHelper {
    static Logger LOG = LoggerFactory.getLogger(JiraHelper.class.getName());


    public static boolean isAqaCredentialValid(Report report){
        return (getAqaJiraClient(report) == null) ? false : true;
    }

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
}
