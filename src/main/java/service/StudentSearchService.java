package service;

import domain.Department;
import domain.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentSearchService {
    private static final StudentRepository studentRepository = new StudentRepository();

    public void findStudentsByCourse(int course) {
        System.out.println("--- Звіт: Студенти " + course + " курсу ---");
        studentRepository.findByCourse(course).forEach(System.out::println);
    }
    public void findStudentsByGroup(int group) {
        System.out.println("--- Звіт: Студенти групи " + group + " ---");
        studentRepository.findByGroup(group).forEach(System.out::println);
    }
    public void findStudentsByFullName(String fullName) {
        System.out.println("--- Звіт: Студенти з ПІБ " + fullName + " "  + " ---");
        studentRepository.findByFullName(fullName + " " ).forEach(System.out::println);
    }
    public void findStudentById(String id) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Студента з таким id не знайдено");
        }
    }

    public void showAllStudents() {
        for (Student student: studentRepository.findAll()) {
            System.out.println(student);
        }
    }

    public void showStudentsInDepartmentAndCourse(String department, int course) {
        System.out.println("--- Звіт: Студенти " + course + " курсу в межах кафедри ---");
        List<Student> students = studentRepository.findAll();
        List<Student> result = new ArrayList<>();
        for(Student student:students){
            if(student.getDepartment().equals(department) && student.getCourse() == course){
                result.add(student);
            }
        }
        result.forEach(System.out::println);
    }
}
