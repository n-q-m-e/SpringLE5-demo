package com.spring.demo.controller;

import com.spring.demo.controller.api.TeacherApiController;
import com.spring.demo.dto.TeacherDto;
import com.spring.demo.dto.TeachersDto;
import com.spring.demo.exception.EntityNotFoundException;
import com.spring.demo.service.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@WebMvcTest(TeacherApiController.class)
public class teacherControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Mock
    private TeacherApiController teacherApiController;

    @MockBean
    private TeacherService teacherService;

    @Before
    public void setup(){
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .build();
    }

    @Test
    public void shouldGetAllTeachers() throws Exception {
        TeacherDto teacherDto = new TeacherDto(1,"foo","bar","Spring");
        TeacherDto teacherDto2 = new TeacherDto(2,"jhon","doe","Unknown");

        given(teacherService.findAll()).willReturn(new TeachersDto(Arrays.asList(teacherDto,teacherDto2)));

        mvc.perform(get("/api/teachers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.teachers", hasSize(2)))
                .andExpect(jsonPath("$.teachers[0].id", is(teacherDto.getId())))
                .andExpect(jsonPath("$.teachers[1].id", is(teacherDto2.getId())));
    }

    @Test
    public void shouldGetTeacher() throws Exception {
        TeacherDto teacherDto = new TeacherDto(1,"foo","bar","Spring");
        given(teacherService.findById(1)).willReturn(teacherDto);

        mvc.perform(get("/api/teachers/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.id", is(teacherDto.getId())));
    }

    @Test
    public void shouldNotFindTeacherById() throws Exception {
        given(teacherService.findById(666)).willThrow(EntityNotFoundException.class);

        mvc.perform(get("/api/teachers/666")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(404));
    }
}
