package service;

import domain.Student;
import domain.Teacher;
import repository.TeacherRepository;

public class TeacherService {
    private final TeacherRepository teacherRepository = new TeacherRepository();

    public void addTeacher(Teacher teacher) {
        if(teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        if(teacher.getFirstName() == null || teacher.getMiddleName() == null || teacher.getLastName() == null) {
            throw new IllegalArgumentException("Teacher's name fields cannot be null");
        }
        teacherRepository.save(teacher);
    }
    public void deleteTeacher(String firstName, String middleName, String lastName) {
        teacherRepository.deleteByFullName(firstName, middleName, lastName);
    }
    public void showAllTeachers() {
        Teacher[] teachers = teacherRepository.findAll();
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
    }
    public Teacher findTeacherByFullName(String firstName, String middleName, String lastName) {
        return teacherRepository.findByFullName(firstName, middleName, lastName);
    }

}
