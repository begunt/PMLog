package by.a1qa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

import static by.a1qa.helpers.CommonData.CommonMethods.timeToMinParse;

/**
 * Created by alexei.khilchuk on 23/01/2017.
 */
@Entity
@Table(name = "report")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Report implements Serializable{

    @Id
    @Column(name = "idReport")
    private int idReport;

    @Column(name = "selectedProject")
    private int selectedProject;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "person")
    private String person;

    @Column(name = "password")
    private String password;

    @Column(name = "product")
    private String product;

    @Column(name = "project")
    private String project;

    @Column(name = "activity")
    private String activity;

    @Column(name = "sprint")
    private String sprint;

    @Column(name = "build")
    private String build;

    @Column(name = "devices")
    private String devices;

    @Column(name = "environment")
    private String environment;

    @Column(name = "time")
    private String time;

    @Column(name = "comment")
    private String comment;

    @Column(name = "link")
    private String link;

    @Column(name = "numberOfCheckedStories")
    private int numberOfCheckedStories;

    @Column(name = "numberOfReopenedStories")
    private int numberOfReopenedStories;

    @Column(name = "linkToReopenedStories")
    private String linkToReopenedStories;

    @Column(name = "numberOfCheckedDefects")
    private int numberOfCheckedDefects;

    @Column(name = "numberOfReopenedDefects")
    private int numberOfReopenedDefects;

    @Column(name = "linkToReopenedDefects")
    private String linkToReopenedDefects;

    @Column(name = "milestone")
    private String milestone;

    @Column(name = "testruns")
    private String testruns;

    @Column(name = "numberOfCheckedCases")
    private String numberOfCheckedCases;

    public Report(){}

    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getSprint() {
        return sprint;
    }

    public void setSprint(String sprint) {
        this.sprint = sprint;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getDevices() {
        return devices;
    }

    public void setDevices(String devices) {
        this.devices = devices;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = String.valueOf(timeToMinParse(time));
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getNumberOfCheckedStories() {
        return numberOfCheckedStories;
    }

    public void setNumberOfCheckedStories(int numberOfCheckedStories) {
        this.numberOfCheckedStories = numberOfCheckedStories;
    }

    public int getNumberOfReopenedStories() {
        return numberOfReopenedStories;
    }

    public void setNumberOfReopenedStories(int numberOfReopenedStories) {
        this.numberOfReopenedStories = numberOfReopenedStories;
    }

    public String getLinkToReopenedStories() {
        return linkToReopenedStories;
    }

    public void setLinkToReopenedStories(String linkToReopenedStories) {
        this.linkToReopenedStories = linkToReopenedStories;
    }

    public int getNumberOfCheckedDefects() {
        return numberOfCheckedDefects;
    }

    public void setNumberOfCheckedDefects(int numberOfCheckedDefects) {
        this.numberOfCheckedDefects = numberOfCheckedDefects;
    }

    public int getNumberOfReopenedDefects() {
        return numberOfReopenedDefects;
    }

    public void setNumberOfReopenedDefects(int numberOfReopenedDefects) {
        this.numberOfReopenedDefects = numberOfReopenedDefects;
    }

    public String getLinkToReopenedDefects() {
        return linkToReopenedDefects;
    }

    public void setLinkToReopenedDefects(String linkToReopenedDefects) {
        this.linkToReopenedDefects = linkToReopenedDefects;
    }

    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    public String getTestruns() {
        return testruns;
    }

    public void setTestruns(String testruns) {
        this.testruns = testruns;
    }

    public String getNumberOfCheckedCases() {
        return numberOfCheckedCases;
    }

    public void setNumberOfCheckedCases(String numberOfCheckedCases) {
        this.numberOfCheckedCases = numberOfCheckedCases;
    }

    public int getSelectedProject() {
        return selectedProject;
    }

    public void setSelectedProject(int selectedProject) {
        this.selectedProject = selectedProject;
    }

    @Override
    public String toString() {
        return "Report{" +
                "idReport=" + idReport +
                ", selectedProject=" + selectedProject +
                ", timestamp='" + timestamp + '\'' +
                ", person='" + person + '\'' +
                ", password='" + password + '\'' +
                ", product='" + product + '\'' +
                ", project='" + project + '\'' +
                ", activity='" + activity + '\'' +
                ", sprint='" + sprint + '\'' +
                ", build='" + build + '\'' +
                ", devices='" + devices + '\'' +
                ", environment='" + environment + '\'' +
                ", time='" + time + '\'' +
                ", comment='" + comment + '\'' +
                ", link='" + link + '\'' +
                ", numberOfCheckedStories=" + numberOfCheckedStories +
                ", numberOfReopenedStories=" + numberOfReopenedStories +
                ", linkToReopenedStories='" + linkToReopenedStories + '\'' +
                ", numberOfCheckedDefects=" + numberOfCheckedDefects +
                ", numberOfReopenedDefects=" + numberOfReopenedDefects +
                ", linkToReopenedDefects='" + linkToReopenedDefects + '\'' +
                ", milestone='" + milestone + '\'' +
                ", testruns='" + testruns + '\'' +
                ", numberOfCheckedCases='" + numberOfCheckedCases + '\'' +
                '}';
    }
}
