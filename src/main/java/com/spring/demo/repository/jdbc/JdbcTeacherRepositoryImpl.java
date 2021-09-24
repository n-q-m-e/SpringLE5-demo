package com.spring.demo.repository.jdbc;

import com.spring.demo.model.TeacherModel;
import com.spring.demo.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@Repository
@ConditionalOnProperty(name="demo.repository", havingValue = "jdbc")
public class JdbcTeacherRepositoryImpl implements TeacherRepository {

    private static final String FIND_ALL_QUERY="SELECT * FROM teacher;";
    private static final String FIND_BY_ID_QUERY="SELECT * FROM teacher WHERE id = ?;";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TeacherModel> findAll() {
        return jdbcTemplate.query(FIND_ALL_QUERY, new TeacherRowMapper());
    }

    @Override
    public TeacherModel findById(int id) {
        try{
            return jdbcTemplate.queryForObject(FIND_BY_ID_QUERY,new Object[]{id},new TeacherRowMapper());
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public void save(TeacherModel teacherModel) {
        throw new NotImplementedException();
    }
}
