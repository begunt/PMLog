package by.a1qa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "idProject")
    private int idProject;

    @Column
    private String name;

    @Column
    private String jiraProjectKeyA1QA;

    @Column
    private String getJiraProjectKeyPM;

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJiraProjectKeyA1QA() {
        return jiraProjectKeyA1QA;
    }

    public void setJiraProjectKeyA1QA(String jiraProjectKeyA1QA) {
        this.jiraProjectKeyA1QA = jiraProjectKeyA1QA;
    }

    public String getGetJiraProjectKeyPM() {
        return getJiraProjectKeyPM;
    }

    public void setGetJiraProjectKeyPM(String getJiraProjectKeyPM) {
        this.getJiraProjectKeyPM = getJiraProjectKeyPM;
    }
}
