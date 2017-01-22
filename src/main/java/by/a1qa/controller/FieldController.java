package by.a1qa.controller;

import by.a1qa.model.Field;
import by.a1qa.model.Field;
import by.a1qa.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class FieldController {
    private FieldService fieldService;

    @Autowired(required = true)
    @Qualifier(value = "fieldService")

    public void setFieldService(FieldService fieldService) {
        this.fieldService = fieldService;
    }
    @RequestMapping(value = "fields", method = RequestMethod.GET)
    public String listFields(Model model, HttpServletRequest request){
        String page = accessCheck(request, "fields");
        if (page != "accessdenied") {
            model.addAttribute("field", new Field());
            model.addAttribute("listFields", this.fieldService.listFields());
        }
        return page;
    }

    @RequestMapping(value = "/fields/add", method = RequestMethod.POST)
    public String addField(@ModelAttribute("field") Field field, HttpServletRequest request){
        String page = accessCheck(request, "redirect:/fieldController/fields");
        if (page != "accessdenied") {
            if (field.getIdField() == 0) {
                this.fieldService.addField(field);
            } else {
                this.fieldService.updateField(field);
            }
        }
        return page;
    }

    @RequestMapping("/remove/{id}")
    public String removeField(@PathVariable("id") int id, HttpServletRequest request){
        String page = accessCheck(request, "redirect:/fieldController/fields");
        if (page != "accessdenied") {
            this.fieldService.removeField(id);
        }
        return page;
    }

    @RequestMapping("edit/{id}")
    public String editField(@PathVariable("id") int id, Model model, HttpServletRequest request){
        String page = accessCheck(request, "fields");
        if (page != "accessdenied") {
            model.addAttribute("field", this.fieldService.getFieldById(id));
            model.addAttribute("listFields", this.fieldService.listFields());
        }
        return page;
    }

    public String accessCheck(HttpServletRequest request, String page) {
        HttpSession session = request.getSession();
        /*Field field = (Field) session.getAttribute("fieldSession");
        if (field.getFirstName() == null)
            return "accessdenied";
        else */return page;
    }
}
