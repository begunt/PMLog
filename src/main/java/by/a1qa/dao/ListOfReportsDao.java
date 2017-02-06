package by.a1qa.dao;

import by.a1qa.model.Report;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by tbegu_000 on 04.02.2017.
 */
@Repository
public class ListOfReportsDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addListOfReports(List<Report> listOfReports) {
        for (Report report : listOfReports) {
            Session session = this.sessionFactory.getCurrentSession();
            session.persist(report);
            logger.info("Report successfully saved. Report details: " + report);
        }
    }

    public void updateListOfReports(List<Report> listOfReports) {
        for (Report report : listOfReports) {
            Session session = this.sessionFactory.getCurrentSession();
            session.update(report);
            logger.info("Report successfully update. Report details: " + report);
        }
    }

    public void removeListOfReports(List<Report> listOfReports) {
        for (Report report : listOfReports) {
            Session session = this.sessionFactory.getCurrentSession();
            Report report1 = (Report) session.load(Report.class, new Integer(report.getIdReport()));

            if (report != null) {
                session.delete(report1);
            }
            logger.info("Report successfully removed. Report details: " + report1);
        }
    }

    public List<Report> getListOfReportsByPerson(List<Report> listOfReports, String person) {
        List<Report> tempListOfReports = new ArrayList<>();
        String personFromList;
        for(int i=0; i<listOfReports.size();i++){
            personFromList = listOfReports.get(i).getPerson();
            if(personFromList.equals(person))
                tempListOfReports.add(listOfReports.get(i));
        }
        return tempListOfReports;
    }

    public List<Report> removeListOfReportsByPerson(List<Report> currentListOfReports, String person) {
        Iterator iterator = currentListOfReports.iterator();
        while (iterator.hasNext()){
            Report report = (Report) iterator.next();
            if (report.getPerson().equals(person)){
                iterator.remove();
            }
        }
        return currentListOfReports;
    }

    public List<Report> listOfReports() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Report> reportList = session.createQuery("from Report").list();

        for(Report report: reportList){
            logger.info("report list: " + report);
        }

        return reportList;
    }
}
