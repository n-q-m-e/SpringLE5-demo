package com.spring.demo.controller.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

public class AbstractHtmlController {

    @Autowired
    private Environment env;

    @ModelAttribute
    public void commonAttribute(Model model){
        model.addAttribute("version", env.getProperty("application.version","undefined"));
    }



}
