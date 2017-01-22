package by.a1qa.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "idProject")
    private int idProject;

    @Column(name = "name")
    private String name;

    @Column(name = "jiraProjectKeyA1QA")
    private String jiraProjectKeyA1QA;

    @Column(name = "jiraProjectKeyPM")
    private String jiraProjectKeyPM;

    @Transient
    private List<Field> customFields;

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
        return jiraProjectKeyPM;
    }

    public void setGetJiraProjectKeyPM(String getJiraProjectKeyPM) {
        this.jiraProjectKeyPM = getJiraProjectKeyPM;
    }

    public List<Field> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(List<Field> customFields) {
        this.customFields = customFields;
    }
}
