package by.a1qa.controller;

import by.a1qa.model.Dropdown;
import by.a1qa.service.DropdownService;
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

/**
 * Created by tbegu_000 on 22.01.2017.
 */
@Controller
@RequestMapping("/DropdownController")
public class DropdownController {
    private DropdownService dropdownService;
    
    @Autowired(required = true)
    @Qualifier(value = "dropdownService")
    
    public void setDropdownService(DropdownService dropdownService) {
        this.dropdownService = dropdownService;
    }
    @RequestMapping(value = "dropdowns", method = RequestMethod.GET)
    public String listDropdowns(Model model, HttpServletRequest request){
        String page = accessCheck(request, "dropdowns");
        if (page != "accessdenied") {
            model.addAttribute("dropdown", new Dropdown());
            model.addAttribute("listDropdowns", this.dropdownService.listDropdowns());
        }
        return page;
    }

    @RequestMapping(value = "/dropdowns/add", method = RequestMethod.POST)
    public String addDropdown(@ModelAttribute("dropdown") Dropdown dropdown, HttpServletRequest request){
        String page = accessCheck(request, "redirect:/dropdownController/dropdowns");
        if (page != "accessdenied") {
            if (dropdown.getIdDropdown() == 0) {
                this.dropdownService.addDropdown(dropdown);
            } else {
                this.dropdownService.updateDropdown(dropdown);
            }
        }
        return page;
    }

    @RequestMapping("/remove/{id}")
    public String removeDropdown(@PathVariable("id") int id, HttpServletRequest request){
        String page = accessCheck(request, "redirect:/dropdownController/dropdowns");
        if (page != "accessdenied") {
            this.dropdownService.removeDropdown(id);
        }
        return page;
    }

    @RequestMapping("edit/{id}")
    public String editDropdown(@PathVariable("id") int id, Model model, HttpServletRequest request){
        String page = accessCheck(request, "dropdowns");
        if (page != "accessdenied") {
            model.addAttribute("dropdown", this.dropdownService.getDropdownById(id));
            model.addAttribute("listDropdowns", this.dropdownService.listDropdowns());
        }
        return page;
    }

    public String accessCheck(HttpServletRequest request, String page) {
        HttpSession session = request.getSession();
        /*Dropdown dropdown = (Dropdown) session.getAttribute("dropdownSession");
        if (dropdown.getFirstName() == null)
            return "accessdenied";
        else */return page;
    }
}
