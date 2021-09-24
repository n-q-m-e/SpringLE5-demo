package com.spring.demo.service;

import com.spring.demo.dto.TeacherDto;
import com.spring.demo.dto.TeachersDto;
import com.spring.demo.exception.EntityNotFoundException;
import com.spring.demo.model.TeacherModel;
import com.spring.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public TeachersDto findAll(){
        List<TeacherModel> teacherModels = teacherRepository.findAll();
        List<TeacherDto> teachers = teacherModels.stream().map(this::convert).collect(Collectors.toList());
        return new TeachersDto(teachers);
    }

    public TeacherDto findById(int id){
        TeacherModel teacherModel = teacherRepository.findById(id);
        if(teacherModel != null){
            return convert(teacherModel);
        } else{
            throw new EntityNotFoundException(MessageFormat.format("Following teacher has not been found : {0}",id));
        }
    }

    private TeacherDto convert(TeacherModel teacherModel){
        return new TeacherDto(teacherModel.getId(),
                teacherModel.getFirstName(),
                teacherModel.getLastName(),
                teacherModel.getSubject());
    }

}
