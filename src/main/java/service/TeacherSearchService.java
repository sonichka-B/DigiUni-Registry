package service;

import domain.Teacher;
import repository.TeacherRepository;

public class TeacherSearchService {
    private static final TeacherRepository teacherRepository = new TeacherRepository();

    public void showAllTeachers() {
        for (Teacher teacher : teacherRepository.findAll()) {
            System.out.println(teacher);
        }
    }

    public void findTeacherByFullName(String fullName) {
        System.out.println("--- Звіт: Викладач з ПІБ " + fullName + " " + " ---");
        Teacher teacher = teacherRepository.findByFullName(fullName);
        if (teacher != null) {
            System.out.println(teacher);
        } else {
            System.out.println("Викладача з таким ПІБ не знайдено");
        }
    }

    public void findTeacherById(String id) {
        System.out.println("--- Звіт: Викладач з id " + id + " " + " ---");
        Teacher teacher = teacherRepository.findById(id);
        if (teacher != null) {
            System.out.println(teacher);
        } else {
            System.out.println("Викладача з таким id не знайдено");
        }
    }
}
