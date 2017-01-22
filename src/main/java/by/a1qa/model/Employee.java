package by.a1qa.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by tbegu_000 on 06.11.2016.
 */
@Entity
public class Employee {
    private String aqaLogin;
    private String aqaPassword;
    private String pmLogin;
    private String pmPassword;
   // private JiraClient aqaJiraClient;
   // private JiraClient pmJiraClient;
    @Id
    private int idEmployee;

    public String getAqaLogin() {
        return aqaLogin;
    }

    public void setAqaLogin(String aqaLogin) {
        this.aqaLogin = aqaLogin;
    }

    public String getAqaPassword() {
        return aqaPassword;
    }

    public void setAqaPassword(String aqaPassword) {
        this.aqaPassword = aqaPassword;
    }

    public String getPmLogin() {
        return pmLogin;
    }

    public void setPmLogin(String pmLogin) {
        this.pmLogin = pmLogin;
    }

    public String getPmPassword() {
        return pmPassword;
    }

    public void setPmPassword(String pmPassword) {
        this.pmPassword = pmPassword;
    }

/*
    public JiraClient getAqaJiraClient() {
        return aqaJiraClient;
    }

    public void setAqaJiraClient(JiraClient aqaJiraClient) {
        this.aqaJiraClient = aqaJiraClient;
    }

    public JiraClient getPmJiraClient() {
        return pmJiraClient;
    }

    public void setPmJiraClient(JiraClient pmJiraClient) {
        this.pmJiraClient = pmJiraClient;
    }
    */
}
