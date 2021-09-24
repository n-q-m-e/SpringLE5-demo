package com.spring.demo.controller.api;

import com.spring.demo.dto.TeacherDto;
import com.spring.demo.dto.TeachersDto;
import com.spring.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TeacherApiController extends AbstractApiController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value="/teachers")
    @ResponseBody
    public TeachersDto getTeacher(){
        return teacherService.findAll();
    }

    @RequestMapping(value="/teachers/{id}")
    @ResponseBody
    public TeacherDto getTeacher(@PathVariable int id){
        return teacherService.findById(id);
    }

}
