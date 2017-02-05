package by.a1qa.service;

import by.a1qa.dao.ListOfReportsDao;
import by.a1qa.model.Report;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tbegu_000 on 04.02.2017.
 */
@Service
@Transactional
public class ListOfReportsService {

    private ListOfReportsDao listOfReportsDao;

    public void setReportDao(ListOfReportsDao listOfReportsDao) {
        this.listOfReportsDao = listOfReportsDao;
    }

    public void addListOfReports(List<Report> listOfReports) {
        this.listOfReportsDao.addListOfReports(listOfReports);
    }

    public void updateListOfReports(List<Report> listOfReports) {
        this.listOfReportsDao.updateListOfReports(listOfReports);
    }

    public void removeListOfReports(List<Report> listOfReports) {
        this.listOfReportsDao.removeListOfReports(listOfReports);
    }
    public List<Report> listOfReports() {
        return this.listOfReportsDao.listOfReports();
    }

}
