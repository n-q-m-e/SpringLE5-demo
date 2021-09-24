package com.spring.demo.repository;

import com.spring.demo.model.TeacherModel;

import java.util.List;


public interface TeacherRepository {

    List<TeacherModel> findAll();
    TeacherModel findById(int id);

    void save(TeacherModel teacherModel);

}