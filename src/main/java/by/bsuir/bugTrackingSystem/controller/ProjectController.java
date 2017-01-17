package by.bsuir.bugTrackingSystem.controller;

import by.bsuir.bugTrackingSystem.model.Employee;
import by.bsuir.bugTrackingSystem.model.Issue;
import by.bsuir.bugTrackingSystem.model.Project;
import by.bsuir.bugTrackingSystem.service.EmployeeService;
import by.bsuir.bugTrackingSystem.service.IssueService;
import by.bsuir.bugTrackingSystem.service.ProjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by tbegu_000 on 05.11.2016.
 */
@Controller
@RequestMapping ("/projectController")
public class ProjectController {
    private ProjectService projectService;
    private EmployeeService employeeService;
    private IssueService issueService;

    @Autowired(required = true)
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired(required = true)
    @Qualifier(value = "employeeService")
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Autowired(required = true)
    @Qualifier(value = "issueService")
    public void setIssueService(IssueService issueService) {
        this.issueService = issueService;
    }

    @RequestMapping(value = "projects", method = RequestMethod.GET)
    public String listProjects(Model model, HttpServletRequest request){
        String page = accessCheck(request, "projects");
        if (page != "accessdenied") {
            model.addAttribute("project", new Project());
            model.addAttribute("listProjects", this.projectService.listProjects());
        }
        return page;
    }

    @RequestMapping(value = "/projects/add", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") Project project, HttpServletRequest request){
        String page = accessCheck(request, "redirect:/projectController/projects");
        if (page != "accessdenied") {
            if (project.getIdProject() == 0) {
                this.projectService.addProject(project);
            } else {
                this.projectService.updateProject(project);
            }
        }
        return page;
    }

    @RequestMapping("/remove/{id}")
    public String removeProject(@PathVariable("id") int id, HttpServletRequest request){
        String page = accessCheck(request, "redirect:/projectController/projects");
        if (page != "accessdenied") {
            this.projectService.removeProject(id);
        }
        return page;
    }

    @RequestMapping("edit/{id}")
    public String editProject(@PathVariable("id") int id, Model model, HttpServletRequest request){
        String page = accessCheck(request, "projects");
        if (page != "accessdenied") {
            model.addAttribute("project", this.projectService.getProjectById(id));
            model.addAttribute("listProjects", this.projectService.listProjects());
        }
        return page;
    }

    @RequestMapping("projectdata/{id}")
    public String projectData(@PathVariable("id") int id, Model model, HttpServletRequest request){
        String page = accessCheck(request, "projectdata");
        if (page != "accessdenied") {
            model.addAttribute("project", this.projectService.getProjectById(id));
        }
        return page;
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchProject (Model model, HttpServletRequest request){
        model.addAttribute("project", new Project());
        model.addAttribute("listProjects", this.projectService.listProjects());

        return accessCheck(request,"projectsearch");
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search (@ModelAttribute("project") Project project, HttpServletRequest request, Model model){
        List<Project> searchListProjects = new ArrayList<Project>();

        for(Project projectFromBD: this.projectService.listProjects()){
            int indexInStr = -1;

            if(project.getName() != null) {
                indexInStr = projectFromBD.getName().indexOf(project.getName());
                if (indexInStr != -1)
                    searchListProjects.add(projectFromBD);
            }

            if(project.getDescription() != null) {
                indexInStr = projectFromBD.getDescription().indexOf(project.getDescription());
                if (indexInStr != -1)
                    searchListProjects.add(projectFromBD);
            }

            if(projectFromBD.getIdProject() == project.getIdProject()) {
                searchListProjects.add(projectFromBD);
            }
        }

        model.addAttribute("project", new Project());
        model.addAttribute("listProjects", searchListProjects);

        return accessCheck(request,"projectsearch");
    }

    @RequestMapping(value = "filter", method = RequestMethod.POST)
    public String filter (@ModelAttribute("project") Project project, HttpServletRequest request, Model model){
        List <Project> searchListProjects = new ArrayList<Project>();
        ObjectMapper oMapper = new ObjectMapper();
        // object -> Map
        Map<String, Object> mapProject = oMapper.convertValue(project, Map.class);

        for(Project projectFromBD: this.projectService.listProjects()){
            boolean flag = true;
            Map<String, Object> mapProjectFromBD = oMapper.convertValue(projectFromBD, Map.class);
            for (String key : mapProjectFromBD.keySet()) {
                if (mapProject.get(key) instanceof String)
                    if (!mapProject.get(key).equals("0") && !mapProject.get(key).equals(mapProjectFromBD.get(key))) {
                        flag = false;
                    }
                    else {
                        if (!mapProject.get(key).equals(mapProjectFromBD.get(key)) && !mapProject.get(key).equals(0)) {
                            flag = false;
                        }
                    }
            }
            if (flag)
                searchListProjects.add(projectFromBD);
        }

        model.addAttribute("project", new Project());
        model.addAttribute("listProjects", searchListProjects);

        return accessCheck(request,"projectsearch");
    }

    @RequestMapping(value = "statistics", method = RequestMethod.GET)
    public String statistic(Model model, HttpServletRequest request) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("listEmployees", this.employeeService.listEmployees());
        model.addAttribute("statisticByCount", statisticByCount());
        model.addAttribute("statisticByWeight", statisticByWeight());
        model.addAttribute("statisticByActivity", statisticByActivity());
        return accessCheck(request, "projectstatistics");
    }

    public Map<String, Double> statisticByWeight() {
        Map<String, Double> result = new HashMap<>();
        for (Employee employee : this.employeeService.listEmployees())
            result.put(employee.getIdEmployee() + "", 0.0);
        int countOfIssues = 0, weight = 0;

        for (Issue issue : this.issueService.listIssues()) {
            countOfIssues++;
            for (Employee employee : this.employeeService.listEmployees())
                if (issue.getIdEmployee() == employee.getIdEmployee()) {
                    if (issue.getSeverity().equals("Critical"))
                        weight = 5;
                    else if (issue.getSeverity().equals("Major"))
                        weight = 4;
                    else if (issue.getSeverity().equals("Average"))
                        weight = 3;
                    else if (issue.getSeverity().equals("Minor"))
                        weight = 2;
                    else if (issue.getSeverity().equals("Enchancement"))
                        weight = 1;
                    result.put(employee.getIdEmployee() + "", result.get(employee.getIdEmployee()) + 1 * weight);
                }
        }

        for (Employee employee : this.employeeService.listEmployees())
            result.put(employee.getIdEmployee() + "", result.get(employee.getIdEmployee()) / countOfIssues);

        return result;
    }
    public Map<String, Double> statisticByCount() {
        Map<String, Double> result = new HashMap<>();
        for (Employee employee : this.employeeService.listEmployees())
            result.put(employee.getIdEmployee() + "", 0.0);
        int countOfIssues = 0, weight = 0;

        for (Issue issue : this.issueService.listIssues()) {
            countOfIssues++;
            for (Employee employee : this.employeeService.listEmployees())
                if (issue.getIdEmployee() == employee.getIdEmployee())
                    result.put(employee.getIdEmployee() + "", result.get(employee.getIdEmployee()) + 1);

        }

        for (Employee employee : this.employeeService.listEmployees())
            result.put(employee.getIdEmployee() + "", result.get(employee.getIdEmployee()) / countOfIssues);

        return result;
    }
    public List<Map> statisticByActivity() {
        List<Map> listEmployeeMap = new ArrayList<Map>();
        Map<String, Double> result = new HashMap<>();
        int countOfIssues = 0;

        for(int i=1; i<=12; i++)
            if(i<10)
                result.put("0"+i+"", 0.0);
            else result.put(i+"", 0.0);

        for (Issue issue : this.issueService.listIssues()) {
            for (Employee employee : this.employeeService.listEmployees())
                if (issue.getIdEmployee() == employee.getIdEmployee())
                    result.put(employee.getIdEmployee() + "", result.get(employee.getIdEmployee()) + 1);
        }

        for(Employee employee : this.employeeService.listEmployees()){
            for (Issue issue : this.issueService.listIssues()) {
                if(issue.getIdEmployee() == employee.getIdEmployee()) {
                    String reportDate = issue.getCreatedDate();
                    String str[] = reportDate.split("-");

                    countOfIssues++;
                    for (int i = 1; i <= 12; i++) {
                        if (i < 10) {
                            if (str[1].equals("0" + i + "")) {
                                result.put("0" + i + "", result.get("0" + i + "") + 1);
                            }
                        } else if (str[1].equals(i + "")) {
                            result.put(i + "", result.get(i + "") + 1);
                        }
                    }
                }
            }
            listEmployeeMap.add(result);
        }

        return listEmployeeMap;
    }

    public String accessCheck(HttpServletRequest request, String page){
        HttpSession session = request.getSession();
        Employee employee = (Employee)session.getAttribute("employeeSession");
        if(employee.getFirstName()==null)
            return "accessdenied";
        else return page;
    }
}
