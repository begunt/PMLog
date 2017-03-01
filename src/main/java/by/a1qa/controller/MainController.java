package by.a1qa.controller;

import by.a1qa.model.Project;
import by.a1qa.model.Report;
import by.a1qa.service.DropdownService;
import by.a1qa.service.FieldService;
import by.a1qa.service.ProjectService;
import by.a1qa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static by.a1qa.helpers.CommonData.AQA_JIRA_CLIENT_SESSION_ATTR;

/**
 * Created by tbegu_000 on 06.11.2016.
 */
@Controller
@SessionAttributes("reportSession")
public class MainController {

    private ProjectService projectService;
    private FieldService fieldService;
    private UserService userService;
    private DropdownService dropdownService;
    private ReportController reportController = new ReportController();

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

    @Autowired(required = true)
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("report", new Report());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginPMLog (@ModelAttribute("report") Report report, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        Report reportToModel = new Report();
        reportToModel.setPerson(report.getPerson());

        if (this.userService.ifUserExists(report.getPerson())){
       /* if (true){*/
            request.getSession().setAttribute(AQA_JIRA_CLIENT_SESSION_ATTR, report.getPerson());

            modelAndView.addObject("project", new Project());
            List<Project> listOfProjects = this.projectService.listProjects();
            for (Project project: listOfProjects){
                project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
            }
            modelAndView.addObject("listProjects", listOfProjects);
            modelAndView.addObject("report", reportToModel);
            modelAndView.addObject("listDropdown", this.dropdownService.listDropdowns());
            List<Report> listOfReports = reportController.getListOfReports();
            if(!listOfReports.isEmpty())
                modelAndView.addObject("listReports",
                        reportController.getListOfReportsDao().getListOfReportsByPerson(listOfReports, report.getPerson()));
            modelAndView.addObject("reportSession", reportToModel);
            modelAndView.setViewName("tasks");
        }
        else{
            modelAndView.addObject("wrongPass", new Boolean(true));
            modelAndView.addObject("report", new Report());
            modelAndView.setViewName("index");
        }

        return modelAndView;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("report", new Report());
        modelAndView.setViewName("index");
        request.removeAttribute("reportSession");
        request.removeAttribute(AQA_JIRA_CLIENT_SESSION_ATTR);

        return modelAndView;
    }

    /*@RequestMapping(value = "/mainmenu",method = RequestMethod.GET)
    public ModelAndView mainMenu(HttpServletRequest request) {
        return accessCheck(request, "mainMenu");
    }
*/
    /*public ModelAndView accessCheck(HttpServletRequest request, String page){
        ModelAndView modelAndView = new ModelAndView();
        HttpSession session = request.getSession();
        Employee employee = (Employee)session.getAttribute("employeeSession");

        if(employee.getFirstName()==null){
            modelAndView.setViewName("accessdenied");
            return modelAndView;
        }
        else {
            modelAndView.setViewName(page);
            return modelAndView;
        }
    }*/
}
