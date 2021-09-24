package com.spring.demo.validator;

import com.spring.demo.dto.TeacherDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TeacherValidator implements Validator {

    private static final String FIELD_IS_REQUIRED = "Field is required !";

    @Override
    public boolean supports(Class<?> aClass) {
        return TeacherDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TeacherDto teacherDto = (TeacherDto) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",FIELD_IS_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",FIELD_IS_REQUIRED);
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "subject",FIELD_IS_REQUIRED);

    }
}
