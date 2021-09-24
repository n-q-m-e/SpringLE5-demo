package com.spring.demo.repository.springdata;

import com.spring.demo.model.TeacherModel;
import com.spring.demo.repository.TeacherRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
@ConditionalOnProperty(name="demo.repository", havingValue = "springdata")
public interface SpringDataTeacherRepository extends TeacherRepository, Repository<TeacherModel,Integer> {
}
