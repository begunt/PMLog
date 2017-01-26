package by.a1qa.dao;

import by.a1qa.model.Report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by tbegu_000 on 26.01.2017.
 */
public class ReportDao1 {

    public List<Report> addReport(Report report, List<Report> listOfReports) {
        report = parsingReport(report);
        report.setIdReport(listOfReports.size()+1);
        report.setTimestamp(getCurrentTimeStamp());

        listOfReports.add(report);
        return listOfReports;
    }

    public List<Report> updateReport(Report report, List<Report> listOfReports) {
        report = parsingReport(report);
        int index = getIndexOfReportById(listOfReports, report);

        listOfReports.get(index).setActivity(report.getActivity());
        listOfReports.get(index).setBuild(report.getBuild());
        listOfReports.get(index).setComment(report.getComment());
        listOfReports.get(index).setDevices(report.getDevices());
        listOfReports.get(index).setEnvironment(report.getEnvironment());
        listOfReports.get(index).setLink(report.getLink());
        listOfReports.get(index).setTimestamp(report.getTimestamp());
        listOfReports.get(index).setPerson(report.getPerson());
        listOfReports.get(index).setProduct(report.getProduct());
        listOfReports.get(index).setProject(report.getProject());
        listOfReports.get(index).setSprint(report.getSprint());
        listOfReports.get(index).setTime(report.getTime());
        listOfReports.get(index).setNumberOfCheckedStories(report.getNumberOfCheckedStories());
        listOfReports.get(index).setNumberOfReopenedStories(report.getNumberOfReopenedStories());
        listOfReports.get(index).setLinkToReopenedStories(report.getLinkToReopenedStories());
        listOfReports.get(index).setNumberOfCheckedDefects(report.getNumberOfCheckedDefects());
        listOfReports.get(index).setNumberOfReopenedDefects(report.getNumberOfReopenedDefects());
        listOfReports.get(index).setLinkToReopenedDefects(report.getLinkToReopenedDefects());
        listOfReports.get(index).setMilestone(report.getMilestone());
        listOfReports.get(index).setTestruns(report.getTestruns());
        listOfReports.get(index).setNumberOfCheckedCases(report.getNumberOfCheckedCases());
        return  listOfReports;
    }

    public List<Report> removeReport(int id, List<Report> listOfReports) {

        for(int i=0; i<listOfReports.size();i++){
            if(listOfReports.get(i).getIdReport() == id)
                listOfReports.remove(i);
        }
        return listOfReports;
    }

    public Report getReportById(List<Report> listOfReports, int id) {
        for(int i=0; i<listOfReports.size();i++){
            if(listOfReports.get(i).getIdReport() == id)
                return listOfReports.get(i);
        }
        return new Report();
    }

    public Integer getIndexOfReportById(List<Report> listOfReports, Report report){
        for(int i=0; i<listOfReports.size();i++){
            if(listOfReports.get(i).getIdReport() == report.getIdReport())
                return i;
        }
        return 0;
    }
    public String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
    public Report parsingReport(Report report) {
        report.setProduct(getCorrectString(report.getProduct()));
        report.setProject(getCorrectString(report.getProject()));
        report.setActivity(getCorrectString(report.getActivity()));
        report.setSprint(getCorrectString(report.getSprint()));
        report.setBuild(getCorrectString(report.getBuild()));
        report.setDevices(getCorrectString(report.getDevices()));
        report.setEnvironment(getCorrectString(report.getEnvironment()));
        report.setTime(getCorrectString(report.getTime()));
        report.setComment(getCorrectString(report.getComment()));
        report.setLink(getCorrectString(report.getLink()));
        report.setLinkToReopenedStories(getCorrectString(report.getLinkToReopenedStories()));
        report.setLinkToReopenedDefects(getCorrectString(report.getLinkToReopenedDefects()));
        report.setMilestone(getCorrectString(report.getMilestone()));
        report.setTestruns(getCorrectString(report.getTestruns()));
        return report;
    }
    public String getCorrectString(String str){
        if(!str.equals("")) {
            for (String tempval : str.split(",")) {
                if (!tempval.equals(""))
                    return tempval;
            }
        }
        return "";
    }
}
