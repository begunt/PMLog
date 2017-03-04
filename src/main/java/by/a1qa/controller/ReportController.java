package by.a1qa.controller;

import by.a1qa.dao.ListOfReportsDao;
import by.a1qa.dao.ReportDao1;
import by.a1qa.helpers.ExcelExporter;
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

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static by.a1qa.helpers.CommonData.AQA_JIRA_CLIENT_SESSION_ATTR;

/**
 * Created by tbegu_000 on 24.01.2017.
 */
@Controller
@RequestMapping("/reportController")
public class ReportController {

    private static List<Report> listOfReports = new ArrayList<>();
    private ReportDao1 reportDao = new ReportDao1();
    private static ListOfReportsDao listOfReportsDao = new ListOfReportsDao();
    private ProjectService projectService;
    private FieldService fieldService;
    private DropdownService dropdownService;
    private String personEmail = new String();
    private ListOfReportsService listOfReportsService;

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
    public synchronized String listReports(Model model, HttpServletRequest request) {

        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());
        model.addAttribute("report", new Report());
        model.addAttribute("listFields", this.fieldService.listFields());
        model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, (String)request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR)));
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

        if (report.getIdReport() == 0)
            listOfReports = this.reportDao.addReport(report, listOfReports);
        else listOfReports = this.reportDao.updateReport(report, listOfReports);

        //re-checking in case if there the same IDs - KOSTYL'
        if (listOfReports.size() > 1){
            int prevId = listOfReports.get(0).getIdReport();
            for (int i = 1; i < listOfReports.size(); i++ ){
                if (listOfReports.get(i).getIdReport() == prevId) {
                    listOfReports.get(i).setIdReport(prevId + 1);
                    prevId = listOfReports.get(i).getIdReport();
                }
            }
        }

        return "/reportController/reports";
    }

    @RequestMapping("/remove/{id}")
    public synchronized String removeReport(@PathVariable("id") int id, HttpServletRequest request) {
        listOfReports = this.reportDao.removeReport(id, listOfReports);

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

        synchronized (ReportController.class) {
            model.addAttribute("report", this.reportDao.getReportById(listOfReports, id));
            model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, personEmail));
        }

        Gson gson = new Gson();
        String task = gson.toJson(model);

        return "tasks";
    }

    /*@RequestMapping(value = "sent", method = RequestMethod.POST)
    public String sentReport(@ModelAttribute("listOfReports") List<Report> listOfReports, Model model, HttpServletRequest request) {
*/
    @RequestMapping("sent")
    public synchronized String sentListOfReports( Model model, HttpServletRequest request){
        List<Report> tempListOfReports, listOfReportsFromBD;
        String person = (String)request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR);
        tempListOfReports = this.listOfReportsDao.getListOfReportsByPerson(listOfReports, person);//пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ
        this.listOfReportsService.addListOfReports(tempListOfReports);// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅ
        listOfReportsFromBD = this.listOfReportsService.listOfReports();// пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ

        //пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ пїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ
        for (Report report: listOfReportsFromBD){
            if(report.getPerson().equals(person))
                ReportSender.addReportToQueue(report);
        }
        try {
            model.addAttribute("fileToDownload", ExcelExporter.exportReportsToFile(tempListOfReports));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.listOfReportsService.removeListOfReports(tempListOfReports);//пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ
        listOfReports = listOfReportsDao.removeListOfReportsByPerson(listOfReports, person); //пїЅпїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅ пїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ пїЅпїЅпїЅпїЅпїЅпїЅ

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
        model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, person));
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
        model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, personEmail));
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
