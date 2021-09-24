package com.spring.demo.controller.html;

import com.spring.demo.dto.TeacherDto;
import com.spring.demo.dto.TeachersDto;
import com.spring.demo.service.TeacherService;
import com.spring.demo.validator.TeacherValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class TeacherHtmlController extends AbstractHtmlController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherValidator teacherValidator;

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setValidator(teacherValidator);
    }

    @RequestMapping("/teachers")
    public String teachers(Model model){
        TeachersDto teachersDto = teacherService.findAll();
        model.addAttribute("teachers", teachersDto.getTeachers());
        return "teachers";
    }

    @RequestMapping("/teachers/create")
    public String initCreateTeacher(Model model){
        model.addAttribute("action","create");
        if(!model.containsAttribute("teacher")){
            model.addAttribute("teacher",new TeacherDto());
        }
        return "teacher-create-update";
    }

    @PostMapping("/teachers/create")
    public String createTeacher(@Valid TeacherDto teacher, BindingResult result, RedirectAttributes redirectAttributes){
        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.teacher",result);
            redirectAttributes.addFlashAttribute("teacher",teacher);
            return "redirect:/teachers/create";
        }
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

    @RequestMapping("/teachers/{teacherId}")
    public String teacher(Model model, @PathVariable int teacherId){
        model.addAttribute("action","update");
        if(!model.containsAttribute("teacher")){
            model.addAttribute("teacher",teacherService.findById(teacherId));
        }
        return "teacher-create-update";
    }

    @PostMapping("/teachers/{teacherId}")
    public String updateTeacher(@PathVariable int teacherId, @Valid TeacherDto teacher, BindingResult result, RedirectAttributes redirectAttributes){
        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.teacher",result);
            redirectAttributes.addFlashAttribute("teacher",teacher);
            return "redirect:/teachers/" + teacherId;

        }
        teacherService.save(teacher);
        return "redirect:/teachers";
    }

}
