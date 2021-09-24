package com.spring.demo.repository;

import com.spring.demo.model.TeacherModel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Test
    public void shouldFindTeacherById(){
        TeacherModel teacherModel = teacherRepository.findById(1);
        Assert.assertEquals(1,teacherModel.getId());
        Assert.assertEquals("Alain",teacherModel.getFirstName());
        Assert.assertEquals("Husson",teacherModel.getLastName());
        Assert.assertEquals("Anglais",teacherModel.getSubject());
    }

    @Test
    public void shouldReturnNullWhenTeacherIsNotFound(){
        TeacherModel teacherModel = teacherRepository.findById(999);
        Assert.assertNull(teacherModel);
    }

    @Test
    public void shouldFindAllTeacher(){
        List<TeacherModel> teacherModels = teacherRepository.findAll();
        Assert.assertNotNull(teacherModels);
        Assert.assertEquals(5, teacherModels.size());
    }

    @Test
    @Sql(executionPhase= Sql.ExecutionPhase.BEFORE_TEST_METHOD,  scripts = "classpath:clear.sql")
    public void shouldReturnEmptyCollectionWhenNoTeachers(){
        List<TeacherModel> teacherModels = teacherRepository.findAll();
        Assert.assertNotNull(teacherModels);
        Assert.assertEquals(0, teacherModels.size());
    }
}
