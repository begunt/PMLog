package by.a1qa.dao;

import by.a1qa.model.Report;

import java.util.List;

/**
 * Created by tbegu_000 on 26.01.2017.
 */
public class ReportDao1 {
    public List<Report> addReport(Report report, List<Report> listOfReports) {
        listOfReports.add(report);
        return listOfReports;
    }

    public List<Report> updateReport(Report report, List<Report> listOfReports) {
        listOfReports.get(report.getIdReport()).setActivity(report.getActivity());
        listOfReports.get(report.getIdReport()).setBuild(report.getBuild());
        listOfReports.get(report.getIdReport()).setComment(report.getComment());
        listOfReports.get(report.getIdReport()).setDevices(report.getDevices());
        listOfReports.get(report.getIdReport()).setEnvironment(report.getEnvironment());
        listOfReports.get(report.getIdReport()).setLink(report.getLink());
        listOfReports.get(report.getIdReport()).setTimestamp(report.getTimestamp());
        listOfReports.get(report.getIdReport()).setPerson(report.getPerson());
        listOfReports.get(report.getIdReport()).setProduct(report.getProduct());
        listOfReports.get(report.getIdReport()).setProject(report.getProject());
        listOfReports.get(report.getIdReport()).setSprint(report.getSprint());
        listOfReports.get(report.getIdReport()).setTime(report.getTime());
        listOfReports.get(report.getIdReport()).setNumberOfCheckedStories(report.getNumberOfCheckedStories());
        listOfReports.get(report.getIdReport()).setNumberOfReopenedStories(report.getNumberOfReopenedStories());
        listOfReports.get(report.getIdReport()).setLinkToReopenedStories(report.getLinkToReopenedStories());
        listOfReports.get(report.getIdReport()).setNumberOfCheckedDefects(report.getNumberOfCheckedDefects());
        listOfReports.get(report.getIdReport()).setNumberOfReopenedDefects(report.getNumberOfReopenedDefects());
        listOfReports.get(report.getIdReport()).setLinkToReopenedDefects(report.getLinkToReopenedDefects());
        listOfReports.get(report.getIdReport()).setMilestone(report.getMilestone());
        listOfReports.get(report.getIdReport()).setTestruns(report.getTestruns());
        listOfReports.get(report.getIdReport()).setNumberOfCheckedCases(report.getNumberOfCheckedCases());
        return  listOfReports;
    }

    public List<Report> removeReport(int id, List<Report> listOfReports) {
        listOfReports.remove(id);
        return listOfReports;
    }

    public Report getReportById(List<Report> listOfReports, int id) {
        return listOfReports.get(id);
    }
}
