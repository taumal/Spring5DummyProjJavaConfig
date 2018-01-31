package com.dummies.controller;

import com.dummies.dao.DummyDAO;
import com.dummies.model.Dummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.thymeleaf.spring5.view.ThymeleafView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author taumal
 * @since 11/13/17 06:45 PM
 */
@Controller
@RequestMapping(value = "/dummy")
public class DummyController {
/*

    @Autowired
    private DummyDAO dummyDAO;

    @RequestMapping(value = "/addDummyData", method = RequestMethod.GET)
    public String showForm(HttpServletRequest request, ModelMap model) throws IOException {

        int id = ServletRequestUtils.getIntParameter(request, "id", 0);

        Dummy dummy;

        if (id > 0) {
            dummy = dummyDAO.getDummyDataById(id);
        } else {
            dummy = new Dummy();
        }

        model.put("dummy", dummy);

        return "addDummyData";

    }

    @RequestMapping(value = "/addDummyData", method = RequestMethod.POST)
    public String submit(@ModelAttribute(value = "dummy") final Dummy dummy, final BindingResult result, final ModelMap model){

        if (!result.hasErrors()){
            dummyDAO.saveOrUpdate(dummy);
//            return "redirect: addDummyData";
        }

        model.put("dummy", dummy);

        return "addDummyData";
    }

    @RequestMapping(value = "/dummyData", method = RequestMethod.GET)
    public String dummyData(ModelMap model) throws IOException {

        List<Dummy> dummyData = dummyDAO.getAllDummyData();
        model.put("dummyData", dummyData);

        return "dummyData";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        int id = ServletRequestUtils.getIntParameter(request, "id", 0);
        dummyDAO.delete(id);
        return "redirect:/dummy/dummyData";
    }

    @ModelAttribute("countries")
    public List<String> populateSuppliers() {
        List<String> countries = new ArrayList();
        countries.add("Afganistan");
        countries.add("Bangladesh");
        countries.add("India");
        countries.add("UK");
        countries.add("USA");
        return countries;
    }
*/
}
