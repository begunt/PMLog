package by.a1qa.controller;

import by.a1qa.dao.ReportDao1;
import by.a1qa.model.Project;
import by.a1qa.model.Report;
import by.a1qa.service.DropdownService;
import by.a1qa.service.FieldService;
import by.a1qa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

        for (Project project: listOfProjects){
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        //modelAndView.addObject("listFields", this.fieldService.listFields());
        model.addAttribute("report", new Report());
        model.addAttribute("listReports",listOfReports);
        return "tasks";
    }

    @RequestMapping(value = "/reports/add", method = RequestMethod.POST)
    public String addReport(@ModelAttribute("report") Report report, HttpServletRequest request) {
        if (report.getIdReport() == 0)
            listOfReports = this.reportDao.addReport(report, listOfReports);
        else listOfReports = this.reportDao.updateReport(report, listOfReports);

        return "redirect:/reportController/reports";
    }

    @RequestMapping("/remove/{id}")
    public String removeReport(@PathVariable("id") int id, HttpServletRequest request) {
        listOfReports = this.reportDao.removeReport(id, listOfReports);

        return "redirect:/reportController/reports";
    }

    @RequestMapping("edit/{id}")
    public String editReport(@PathVariable("id") int id, Model model, HttpServletRequest request) {        
        model.addAttribute("report", this.reportDao.getReportById(listOfReports, id));
        model.addAttribute("listReports", listOfReports);

        return "tasks";
    }
}
