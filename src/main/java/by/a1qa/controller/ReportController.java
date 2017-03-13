package by.a1qa.controller;

import by.a1qa.dao.ListOfReportsDao;
import by.a1qa.dao.ReportDao1;
import by.a1qa.helpers.ExcelHelper;
import by.a1qa.helpers.ReportSender;
import by.a1qa.model.Project;
import by.a1qa.model.Report;
import by.a1qa.service.DropdownService;
import by.a1qa.service.FieldService;
import by.a1qa.service.ListOfReportsService;
import by.a1qa.service.ProjectService;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static by.a1qa.helpers.CommonData.AQA_JIRA_CLIENT_SESSION_ATTR;

/**
 * Created by tbegu_000 on 24.01.2017.
 */
@Controller
@RequestMapping("/reportController")
public class ReportController {

    private static List<Report> listOfReports = Collections.synchronizedList(new ArrayList());
    private ReportDao1 reportDao = new ReportDao1();
    private static ListOfReportsDao listOfReportsDao = new ListOfReportsDao();
    private ProjectService projectService;
    private FieldService fieldService;
    private DropdownService dropdownService;
    private String personEmail = new String();
    private ListOfReportsService listOfReportsService;
    private ExcelHelper excelHelper = new ExcelHelper();

    @Autowired(required = true)
    @Qualifier(value = "listOfReportsService")
    public void setListOfReportsService(ListOfReportsService listOfReportsService) {
        this.listOfReportsService = listOfReportsService;
    }

    @Autowired(required = true)
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired(required = true)
    @Qualifier(value = "fieldService")
    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }

    @Autowired(required = true)
    @Qualifier(value = "dropdownService")
    public void setDropdownService(DropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }


    @RequestMapping(value = "reports", method = RequestMethod.GET)
    public synchronized String listReports(Model model, HttpServletRequest request) throws IOException {
        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());
        model.addAttribute("report", new Report());
        model.addAttribute("listFields", this.fieldService.listFields());
        synchronized (listOfReports) {
            model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, (String) request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR)));
        }
        model.addAttribute("forAddButton", "");
        return "tasks";
    }

    @RequestMapping(value = "/reports/add",
            method = RequestMethod.POST,
            produces="text/plain",
            headers="Content-Type=application/json")
    @ResponseBody
    public synchronized String addReport(@RequestBody Report report, HttpServletRequest request) {
        report.setSelectedProject(projectService.getProjectByName(report.getProduct()).getIdProject());
        Project selectedProject = this.projectService.getProjectById(report.getSelectedProject());
        selectedProject.setCustomFields(this.fieldService.listFieldsByIdProject(report.getSelectedProject()));
        this.personEmail = report.getPerson();

        synchronized (listOfReports){
            if (report.getIdReport() == 0)
                listOfReports = this.reportDao.addReport(report, listOfReports);
            else listOfReports = this.reportDao.updateReport(report, listOfReports);
        }


        return "/reportController/reports";
    }

    @RequestMapping("/remove/{id}")
    public synchronized String removeReport(@PathVariable("id") int id, HttpServletRequest request) {
        synchronized (listOfReports) {
            listOfReports = this.reportDao.removeReport(id, listOfReports);
        }
        return "redirect:/reportController/reports";
    }

    @RequestMapping("edit/{id}")
    public synchronized String editReport(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        synchronized (listOfReports){
            model.addAttribute("report", this.reportDao.getReportById(listOfReports, id));
            model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, (String) request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR)));
        }

        return "tasks";
    }

    /*@RequestMapping(value = "sent", method = RequestMethod.POST)
    public String sentReport(@ModelAttribute("listOfReports") List<Report> listOfReports, Model model, HttpServletRequest request) {
*/
    @RequestMapping("sent")
    public synchronized String sentListOfReports( Model model, HttpServletRequest request){
        List<Report> tempListOfReports, listOfReportsFromBD;
        String person = (String)request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR);
        synchronized (listOfReports) {
            tempListOfReports = this.listOfReportsDao.getListOfReportsByPerson(listOfReports, person);//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        }
        this.listOfReportsService.addListOfReports(tempListOfReports);// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅ
        listOfReportsFromBD = this.listOfReportsService.listOfReports();// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ

        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ пїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
        for (Report report: listOfReportsFromBD){
            if(report.getPerson().equals(person))
                ReportSender.addReportToQueue(report);
        }
        try {
            model.addAttribute("fileToDownload", ExcelHelper.exportReportsToFile(tempListOfReports, (String)request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (listOfReports) {
            this.listOfReportsService.removeListOfReports(tempListOfReports);//пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ
            listOfReports = listOfReportsDao.removeListOfReportsByPerson(listOfReports, person); //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        }

        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        //modelAndView.addObject("listFields", this.fieldService.listFields());
        /*Report reportWithPerson = new Report();
        reportWithPerson.setPerson(personEmail);
        reportWithPerson.setPassword(password);
        model.addAttribute("report", reportWithPerson);*/
        model.addAttribute("report", new Report());
        model.addAttribute("listFields", this.fieldService.listFields());
        synchronized (listOfReports) {
            model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, person));
        }
        return "tasks";
    }

    @RequestMapping(value = "/updateAddButton", method = RequestMethod.GET)
    public synchronized String updateAddButton(Model model, HttpServletRequest request) {
        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        //modelAndView.addObject("listFields", this.fieldService.listFields());
        /*Report reportWithPerson = new Report();
        reportWithPerson.setPerson(personEmail);
        reportWithPerson.setPassword(password);
        model.addAttribute("report", reportWithPerson);*/
        model.addAttribute("report", new Report());
        model.addAttribute("listFields", this.fieldService.listFields());
        synchronized (listOfReports) {
            model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, (String) request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR)));
        }
        model.addAttribute("forAddButton", "updating");

        return "tasks";
    }

    @RequestMapping(value = "/files/{file_name}", method = RequestMethod.GET)
    public synchronized void getFile(
            @PathVariable("file_name") String fileName,
            HttpServletResponse response) {
        try {
            InputStream is = new FileInputStream(fileName + ".xls");
            IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
            is.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @RequestMapping(value = "history", method = RequestMethod.GET)
    public synchronized String listOfReportsFromHistory(Model model, HttpServletRequest request) throws IOException {
        synchronized (listOfReports) {
            model.addAttribute("listOfReportsFromHistory", ExcelHelper.getReportsHistory((String) request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR)));
        }

        return "history";
    }

    public ListOfReportsDao getListOfReportsDao() {
        return listOfReportsDao;
    }

    public void setListOfReportsDao(ListOfReportsDao listOfReportsDao) {
        this.listOfReportsDao = listOfReportsDao;
    }

    public List<Report> getListOfReports() {
        return listOfReports;
    }

    public void setListOfReports(List<Report> listOfReports) {
        this.listOfReports = listOfReports;
    }


}

