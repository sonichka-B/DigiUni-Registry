package service;

import domain.Student;
import domain.Teacher;
import repository.TeacherRepository;

public class TeacherService {
    private static final TeacherRepository teacherRepository = new TeacherRepository();

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
    public static Teacher findTeacherByFullName(String firstName, String middleName, String lastName) {
       return teacherRepository.findByFullName(firstName, middleName, lastName);
    }

    public static Teacher findTeacherById(String id) {
       return teacherRepository.findById(id);
    }

    public boolean editTeacher(String id, String position, String academicDegree, String academicTitle) {
        Teacher teacher = teacherRepository.findById(id);
        if (teacher != null) {
            teacher.setPosition(position);
            teacher.setAcademicDegree(academicDegree);
            teacher.setAcademicTitle(academicTitle);
            return true;
        }
        return false;
    }
}
