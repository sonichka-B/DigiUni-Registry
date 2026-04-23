package service;

import domain.Teacher;
import repository.TeacherRepository;

import java.util.Optional;

public class TeacherSearchService {
    private  TeacherRepository teacherRepository;
    public boolean existsById(String id) {
        return teacherRepository.findById(id).isPresent();
    }
    public boolean existsByName(String name) {
        return teacherRepository.findByName(name).isPresent();
    }
    public void showAllTeachers() {
        for (Teacher teacher : teacherRepository.findAll()) {
            System.out.println(teacher);
        }
    }

    public void findTeacherByFullName(String fullName) {
        System.out.println("--- Звіт: Викладач з ПІБ " + fullName + " ---");
        Optional<Teacher> teacher = teacherRepository.findByName(fullName);
        if (teacher.isPresent()) {
            System.out.println(teacher.get());
        } else {
            System.out.println("Викладача з таким ПІБ не знайдено");
        }
    }

    public void findTeacherById(String id) {
        System.out.println("--- Звіт: Викладач з id " + id + " " + " ---");
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            System.out.println(teacher.get());
        } else {
            System.out.println("Викладача з таким id не знайдено");
        }
    }
    public void setTeacherRepository(TeacherRepository repository) {
        this.teacherRepository = repository;
    }
}
