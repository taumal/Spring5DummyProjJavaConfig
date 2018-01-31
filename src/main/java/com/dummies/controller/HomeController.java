package com.dummies.controller;

import com.dummies.exception.WebSecurityException;
import com.dummies.model.Home;
import com.dummies.web.SessionKeys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author taumal
 * @since 11/18/17 7:43 PM
 */
@Controller
@RequestMapping(value = "/")
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model, HttpServletResponse response) throws WebSecurityException {

        HttpSession session = request.getSession();

        if (session.getAttribute(SessionKeys.AUTH) == null) {
            return "redirect:/login";
        } else {
            return "redirect:/home";
        }

    }

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String home(Home home, ModelMap model){
        home.setGreetings("Hello From Spring 5");
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM, yyyy");
        home.setToday(format.format(Calendar.getInstance().getTime()));
        model.put("home", home);
        return "home";
    }
}
