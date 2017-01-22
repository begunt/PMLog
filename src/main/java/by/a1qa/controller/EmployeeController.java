package by.a1qa.controller;

import by.a1qa.model.Employee;
import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import org.springframework.stereotype.Controller;


@Controller
public class EmployeeController {

    private JiraClient aqaJiraClient = null;
    private JiraClient pmJiraClient = null;

    public boolean isAqaCredentialValid(Employee employee){
        return (getAqaJiraClient(employee) == null) ? false : true;
    }

    public boolean isPmCredentialValid(Employee employee){
        return (getPmJiraClient(employee) == null) ? false : true;
    }

    public JiraClient getAqaJiraClient(Employee employee){
        if (aqaJiraClient==null){
            aqaJiraClient = getJiraClient("https://jira.a1qa.com",
                    employee.getAqaLogin(), employee.getAqaPassword());
        }
        return aqaJiraClient;
    }

    public JiraClient getPmJiraClient(Employee employee){
        if (pmJiraClient==null){
            pmJiraClient = getJiraClient("http://jira.productmadness.com",
                    employee.getPmLogin(), employee.getPmPassword());
        }
        return pmJiraClient;
    }

    private JiraClient getJiraClient(String host, String login, String password){
        JiraClient client;
        BasicCredentials jiraCredentials = new BasicCredentials(login, password);
        try {
            client = new JiraClient(host, jiraCredentials);
            client.getProjects();
        } catch (JiraException e) {
            e.printStackTrace();
            client = null;
        }
        return client;
    }

}

