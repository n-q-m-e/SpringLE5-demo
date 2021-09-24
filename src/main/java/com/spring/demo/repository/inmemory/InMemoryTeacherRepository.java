package com.spring.demo.repository.inmemory;

import com.spring.demo.model.TeacherModel;
import com.spring.demo.repository.TeacherRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(name="demo.repository", havingValue = "inMemory")
public class InMemoryTeacherRepository implements TeacherRepository {

    private static final List<TeacherModel> teachers = Arrays.asList(
        new TeacherModel(1, "Alain", "Husson", "Anglais"),
        new TeacherModel(2,"Philippe","Kubiak", "Informatique"),
        new TeacherModel(3,"Thierry","Fricheteau", "Gestion de projet"),
        new TeacherModel(4,"Nicolas","Oxoby", "Electricité générale"),
        new TeacherModel(5,"Amandine","Leriche", "Mathématique")
    );

    @Override
    public List<TeacherModel> findAll() {
        return teachers;
    }

    @Override
    public TeacherModel findById(int id) {
        return teachers
                .stream()
                .filter((x) -> x.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(TeacherModel teacherModel) {
        throw new NotImplementedException();
    }

    public static void main(String[] args) {
        System.out.println(teachers.stream().map(TeacherModel::getSubject).distinct().collect(Collectors.toList()));
        System.out.println(teachers
                .stream()
                .sorted(Comparator.comparing(TeacherModel::getLastName).thenComparing(TeacherModel::getLastName))
                .collect(Collectors.toList()));

    }

}
