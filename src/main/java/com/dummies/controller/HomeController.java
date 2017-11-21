package com.dummies.controller;

import com.dummies.model.Home;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Calendar;

/**
 * @author taumal
 * @since 11/18/17 7:43 PM
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String home(Home home, ModelMap model){
        home.setGreetings("Hello From Spring 5");
        home.setToday(Calendar.getInstance().getTime());
        model.put("home", home);
        return "home";
    }
}
