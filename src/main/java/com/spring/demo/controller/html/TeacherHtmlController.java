package com.spring.demo.controller.html;

import com.spring.demo.dto.TeacherDto;
import com.spring.demo.dto.TeachersDto;
import com.spring.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TeacherHtmlController extends AbstractHtmlController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping("/teachers")
    public String teachers(Model model){
        TeachersDto teachersDto = teacherService.findAll();
        model.addAttribute("teachers", teachersDto.getTeachers());
        return "teachers";
    }

    @RequestMapping("/teachers/create")
    public String initCreateTeacher(Model model){
        model.addAttribute("action","save");
        model.addAttribute("teacher", new TeacherDto());
        return "teacher-create-update";
    }

    @PostMapping("/teachers/create")
    public String createTeacher(TeacherDto teacher){
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @RequestMapping("/teachers/{teacherId}")
    public String teacher(Model model, @PathVariable int teacherId){
        model.addAttribute("action","update");
        model.addAttribute("teacher", teacherService.findById(teacherId));
        return "teacher-create-update";
    }

    @PostMapping("/teachers/{teacherId}")
    public String updateTeacher(@PathVariable int teacherId, TeacherDto teacherDto){
        teacherService.save(teacherDto);
        return "redirect:/teachers";
    }

}