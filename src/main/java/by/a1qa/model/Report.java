package by.a1qa.model;

/**
 * Created by alexei.khilchuk on 23/01/2017.
 */
public class Report {
    private int idReport;
    private String timestamp;
    private String person;
    private String product;
    private String project;
    private String activity;
    private String sprint;
    private String build;
    private String devices;
    private String environment;
    private String time;
    private String comment;
    private String link;
    private int numberOfCheckedStories;
    private int numberOfReopenedStories;
    private String linkToReopenedStories;
    private int numberOfCheckedDefects;
    private int numberOfReopenedDefects;
    private String linkToReopenedDefects;
    private String milestone;
    private String testruns;
    private String numberOfCheckedCases;

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
        this.time = time;
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

    @Override
    public String toString() {
        return "Report{" +
                "idReport=" + idReport +
                ", timestamp='" + timestamp + '\'' +
                ", person='" + person + '\'' +
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
                ", Milestone='" + milestone + '\'' +
                ", Testruns='" + testruns + '\'' +
                ", numberOfCheckedCases='" + numberOfCheckedCases + '\'' +
                '}';
    }
}
