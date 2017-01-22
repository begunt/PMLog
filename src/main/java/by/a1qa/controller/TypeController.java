package by.a1qa.controller;

import by.a1qa.model.Type;
import by.a1qa.service.TypeService;
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
@RequestMapping("/typeController")
public class TypeController {
    private TypeService typeService;

    @Autowired(required = true)
    @Qualifier(value = "typeService")
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    @RequestMapping(value = "types", method = RequestMethod.GET)
    public String listTypes(Model model, HttpServletRequest request) {
        String page = accessCheck(request, "types");
        if (page != "accessdenied") {
            model.addAttribute("type", new Type());
            model.addAttribute("listTypes", this.typeService.listTypes());
        }
        return page;
    }

    @RequestMapping(value = "/types/add", method = RequestMethod.POST)
    public String addType(@ModelAttribute("type") Type type, HttpServletRequest request) {
        String page = accessCheck(request, "redirect:/typeController/types");
        if (type.getIdType() == 0)
            this.typeService.addType(type);
        else this.typeService.updateType(type);

        return page;
    }

    @RequestMapping("/remove/{id}")
    public String removeType(@PathVariable("id") int id, HttpServletRequest request) {
        String page = accessCheck(request, "redirect:/typeController/types");
        if (page != "accessdenied") {
            this.typeService.removeType(id);
        }

        return page;
    }

    @RequestMapping("edit/{id}")
    public String editType(@PathVariable("id") int id, Model model, HttpServletRequest request) {
        String page = accessCheck(request, "types");
        if (page != "accessdenied") {
            model.addAttribute("type", this.typeService.getTypeById(id));
            model.addAttribute("listTypes", this.typeService.listTypes());
        }

        return page;
    }

    public String accessCheck(HttpServletRequest request, String page) {
        return page;
    }
}
