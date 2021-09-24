package com.spring.demo.repository.jdbc;

import com.spring.demo.model.TeacherModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherRowMapper implements RowMapper<TeacherModel> {

    @Override
    public TeacherModel mapRow(ResultSet resultSet, int i) throws SQLException {
        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setId(resultSet.getInt("id"));
        teacherModel.setFirstName(resultSet.getString("first_name"));
        teacherModel.setLastName(resultSet.getString("last_name"));
        teacherModel.setSubject(resultSet.getString("subject"));
        return teacherModel;
    }
}
