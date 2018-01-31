package com.dummies.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author taumal
 * @since 1/31/18 4:26 PM
 */
@Controller
@RequestMapping(value = "/login")
public class AuthController {
    @SuppressWarnings({"UnusedDeclaration"})
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setRequiredFields("userName", "password");
    }

    @RequestMapping(method = RequestMethod.GET)
    protected String show(HttpServletRequest request, ModelMap model) throws Exception {
        /*if (Auth.isLoggedIn(request)) {
            return "redirect:/";
        }
        model.put("auth", new User());*/

        return getFormView();
    }

    protected String getFormView() {
        return "auth/login";
    }
}
