package by.a1qa.controller;

import by.a1qa.model.Project;
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

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Controller
@RequestMapping("/projectController")
public class ProjectController {
    private ProjectService projectService;

    @Autowired(required = true)
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(value = "projects", method = RequestMethod.GET)
    public String listProjects(@ModelAttribute("project") Model model, HttpServletRequest request) {
        String page = accessCheck(request, "projects");
        if (page != "accessdenied") {
            model.addAttribute("project", new Project());
            model.addAttribute("listProjects", this.projectService.listProjects());
        }
        return page;
    }

    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") Project project, HttpServletRequest request) {

        String page = accessCheck(request, "redirect:/projectController/projects");
        if (project.getIdProject() == 0)
            this.projectService.addProject(project);
        else this.projectService.updateProject(project);

        return page;
    }

    @RequestMapping("/remove/{id}")
    public String removeProject(@PathVariable("id") int id, HttpServletRequest request) {
        String page = accessCheck(request, "redirect:/projectController/projects");
        if (page != "accessdenied") {
            this.projectService.removeProject(id);
        }

        return page;
    }

    @RequestMapping("edit/{id}")
    public String editProject(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        String page = accessCheck(request, "projects");
        if (page != "accessdenied") {
            model.addAttribute("project", this.projectService.getProjectById(id));
            model.addAttribute("listProjects", this.projectService.listProjects());
        }

        return page;
    }
    
    public String accessCheck(HttpServletRequest request, String page) {
        return page;
    }
}
