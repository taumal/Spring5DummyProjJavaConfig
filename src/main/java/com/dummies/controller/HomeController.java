package com.dummies.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author taumal
 * @since 11/18/17 7:43 PM
 */
@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String dummy(){
        return "index";
    }
}
