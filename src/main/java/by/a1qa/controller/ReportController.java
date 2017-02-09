package by.a1qa.controller;

import by.a1qa.dao.ListOfReportsDao;
import by.a1qa.dao.ReportDao1;
import by.a1qa.helpers.ReportSender;
import by.a1qa.model.Project;
import by.a1qa.model.Report;
import by.a1qa.service.DropdownService;
import by.a1qa.service.FieldService;
import by.a1qa.service.ListOfReportsService;
import by.a1qa.service.ProjectService;
import net.rcarz.jiraclient.JiraClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private static String personEmail;
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
    public String listReports(Model model, HttpServletRequest request) {

        model.addAttribute("project", new Project());
        List<Project> listOfProjects = this.projectService.listProjects();

        for (Project project : listOfProjects) {
            project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
        }
        model.addAttribute("listProjects", listOfProjects);
        model.addAttribute("listDropdown", this.dropdownService.listDropdowns());

        //modelAndView.addObject("listFields", this.fieldService.listFields());

        /*HttpSession session = request.getSession();
        String person = (String)session.getAttribute("personFromReportSession");*/

        /*Report reportWithPerson = new Report();
        reportWithPerson.setPerson(personEmail);
        reportWithPerson.setPassword(password);
        model.addAttribute("report", reportWithPerson);*/
        model.addAttribute("report", new Report());
        model.addAttribute("listFields", this.fieldService.listFields());
        model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, personEmail));
        model.addAttribute("forAddButton", "");
        return "tasks";
    }

    @RequestMapping(value = "/reports/add",
            method = RequestMethod.POST,
            produces="text/plain",
            headers="Content-Type=application/json")
    @ResponseBody
    public String addReport(@RequestBody Report report, HttpServletRequest request) {
        report.setSelectedProject(projectService.getProjectByName(report.getProduct()).getIdProject());
        Project selectedProject = this.projectService.getProjectById(report.getSelectedProject());
        selectedProject.setCustomFields(this.fieldService.listFieldsByIdProject(report.getSelectedProject()));
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
        model.addAttribute("listReports", this.listOfReportsDao.getListOfReportsByPerson(listOfReports, personEmail));

        return "tasks";
    }

    /*@RequestMapping(value = "sent", method = RequestMethod.POST)
    public String sentReport(@ModelAttribute("listOfReports") List<Report> listOfReports, Model model, HttpServletRequest request) {
*/
    @RequestMapping("sent/{person:.*}")
    public String sentListOfReports(@PathVariable("person") String person, Model model, HttpServletRequest request){
        List<Report> tempListOfReports, listOfReportsFromBD;
        tempListOfReports = this.listOfReportsDao.getListOfReportsByPerson(listOfReports, person);//�������� ������ �������� �� ������ ������ �� ������
        this.listOfReportsService.addListOfReports(tempListOfReports);// �������� ���������� ������ � ��
        listOfReportsFromBD = this.listOfReportsService.listOfReports();// �������� ������ �� ��

        //�������� ��� ������� �� �� � ���� ����
        for (Report report: listOfReportsFromBD){
            if(report.getPerson().equals(person))
                ReportSender.addReportToQueue(report, (JiraClient) request.getSession().getAttribute(AQA_JIRA_CLIENT_SESSION_ATTR));
        }

        this.listOfReportsService.removeListOfReports(tempListOfReports);//������� ����������� � ���� ���� ������� �� ��
        listOfReports = listOfReportsDao.removeListOfReportsByPerson(listOfReports, person); //������� �� �� ������ ������

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
    public String updateAddButton(Model model, HttpServletRequest request) {
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
