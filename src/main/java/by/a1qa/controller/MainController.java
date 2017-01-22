package by.a1qa.controller;

import by.a1qa.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by tbegu_000 on 06.11.2016.
 */
@Controller
@SessionAttributes("employeeSession")
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView loginPMLog (@ModelAttribute("employee") Employee employee){
            ModelAndView modelAndView = new ModelAndView();
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
