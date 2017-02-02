package by.a1qa.controller;

import by.a1qa.dao.ReportDao1;
import by.a1qa.helpers.ReportSender;
import by.a1qa.model.Project;
import by.a1qa.model.Report;
import by.a1qa.service.DropdownService;
import by.a1qa.service.FieldService;
import by.a1qa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbegu_000 on 24.01.2017.
 */
@Controller
@RequestMapping("/reportController")
public class ReportController {

    private List<Report> listOfReports = new ArrayList<>();
    private ReportDao1 reportDao = new ReportDao1();
    private ProjectService projectService;
    private FieldService fieldService;
    private DropdownService dropdownService;
    private String personEmail;

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
    public String listReports(Model model, HttpServletRequest request) {

        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        //modelAndView.addObject("listFields", this.fieldService.listFields());
        Report reportWithPerson = new Report();
        reportWithPerson.setPerson(personEmail);
        model.addAttribute("report", reportWithPerson);
        model.addAttribute("listFields", this.fieldService.listFields());
        model.addAttribute("listReports", listOfReports);
        model.addAttribute("forAddButton", "");
        return "tasks";
    }

    @RequestMapping(value = "/reports/add",
            method = RequestMethod.POST,
            produces="text/plain",
            headers="Content-Type=application/json")
    @ResponseBody
    public String addReport(@RequestBody Report report, HttpServletRequest request) {
        report.setSelectedProject(projectService.getProjectByName(report.getProduct()));
        report.getSelectedProject().setCustomFields(this.fieldService.listFieldsByIdProject(report.getSelectedProject
                ().getIdProject()));
        personEmail = report.getPerson();
        if (report.getIdReport() == 0)
            listOfReports = this.reportDao.addReport(report, listOfReports);
        else listOfReports = this.reportDao.updateReport(report, listOfReports);

        return "/reportController/reports";
    }

    @RequestMapping("/remove/{id}")
    public String removeReport(@PathVariable("id") int id, HttpServletRequest request) {
        listOfReports = this.reportDao.removeReport(id, listOfReports);

        return "redirect:/reportController/reports";
    }

    @RequestMapping("edit/{id}")
    public String editReport(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        model.addAttribute("report", this.reportDao.getReportById(listOfReports, id));
        model.addAttribute("listReports", listOfReports);

        return "tasks";
    }

    /*@RequestMapping(value = "sent", method = RequestMethod.POST)
    public String sentReport(@ModelAttribute("listOfReports") List<Report> listOfReports, Model model, HttpServletRequest request) {
*/
    @RequestMapping(value = "sent", method = RequestMethod.GET)
    public String sentReport (Model model, HttpServletRequest request){
        for (Report report: listOfReports){
            ReportSender.addReportToQueue(report);
        }
        listOfReports.clear();
        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        //modelAndView.addObject("listFields", this.fieldService.listFields());
        Report reportWithPerson = new Report();
        reportWithPerson.setPerson(personEmail);
        model.addAttribute("report", reportWithPerson);
        model.addAttribute("listFields", this.fieldService.listFields());
        model.addAttribute("listReports", listOfReports);
        return "tasks";
    }

    @RequestMapping(value = "/updateAddButton", method = RequestMethod.GET)
    public String updateAddButton(Model model, HttpServletRequest request) {
        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        //modelAndView.addObject("listFields", this.fieldService.listFields());
        Report reportWithPerson = new Report();
        reportWithPerson.setPerson(personEmail);
        model.addAttribute("report", reportWithPerson);
        model.addAttribute("listFields", this.fieldService.listFields());
        model.addAttribute("listReports", listOfReports);
        model.addAttribute("forAddButton", "updating");

        return "tasks";
    }
}