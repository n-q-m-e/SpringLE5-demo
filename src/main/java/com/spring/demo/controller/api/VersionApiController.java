package com.spring.demo.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;

@Controller
public class VersionApiController extends AbstractApiController{

    @Autowired
    private Environment env;

    @RequestMapping(value="/version")
    @ResponseBody
    public String getVersion(){
        String version = env.getProperty("application.version");
        return MessageFormat.format("Version {0} - Demo Application",version);
    }
}
