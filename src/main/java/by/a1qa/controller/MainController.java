package by.a1qa.controller;

import by.a1qa.model.Employee;
import by.a1qa.model.Project;
import by.a1qa.service.DropdownService;
import by.a1qa.service.FieldService;
import by.a1qa.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by tbegu_000 on 06.11.2016.
 */
@Controller
@SessionAttributes("employeeSession")
public class MainController {

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginPMLog (@ModelAttribute("employee") Employee employee){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("project", new Project());
            List<Project> listOfProjects = this.projectService.listProjects();

            for (Project project: listOfProjects){
                project.setCustomFields(this.fieldService.listFieldsByIdProject(project.getIdProject()));
            }

            modelAndView.addObject("listProjects", listOfProjects);

            modelAndView.addObject("listDropdown", this.dropdownService.listDropdowns());

            //modelAndView.addObject("listFields", this.fieldService.listFields());
            modelAndView.setViewName("tasks");
            return modelAndView;

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logoutBTS(HttpSession request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("success", new Boolean(true));
        modelAndView.setViewName("index");
        request.removeAttribute("employeeSession");

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
