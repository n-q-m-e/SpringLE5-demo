package com.spring.demo.service;

import com.spring.demo.dto.TeacherDto;
import com.spring.demo.dto.TeachersDto;
import com.spring.demo.exception.EntityNotFoundException;
import com.spring.demo.model.TeacherModel;
import com.spring.demo.repository.TeacherRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TeacherService.class)
public class TeacherServiceTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    public void shouldFindTeacherById(){
        TeacherModel fooBar = new TeacherModel(1,"Foo","Bar","Spring");
        Mockito.when(teacherRepository.findById(1)).thenReturn(fooBar);

        TeacherDto teacherDto = teacherService.findById(1);
        Assert.assertNotNull(teacherDto);
        Assert.assertEquals(fooBar.getFirstName(),teacherDto.getFirstName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void shouldThrowExceptionWhenTeacherIsNotFound(){
        Mockito.when(teacherRepository.findById(666)).thenReturn(null);
        teacherService.findById(666);
    }

    @Test
    public void shouldFindAllTeachers(){
        TeacherModel fooBar = new TeacherModel(1,"Foo","Bar","Spring");
        TeacherModel fooBar2 = new TeacherModel(2,"Jhon","Doe","Unknow");
        Mockito.when(teacherRepository.findAll()).thenReturn(Arrays.asList(fooBar,fooBar2));

        TeachersDto teachersDto = teacherService.findAll();
        Assert.assertNotNull(teachersDto);
        Assert.assertEquals(2, teachersDto.getTeachers().size());
    }

    @Test
    public void shouldReturnEmptyCollectionWhenNoTeacher(){
        Mockito.when(teacherRepository.findAll()).thenReturn(new ArrayList<>());
        TeachersDto teachersDto = teacherService.findAll();
        Assert.assertNotNull(teachersDto);
        Assert.assertEquals(0, teachersDto.getTeachers().size());
    }


}
