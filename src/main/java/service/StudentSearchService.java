package service;

import domain.DTO.StudentDTO;
import domain.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentSearchService {
    private  StudentRepository studentRepository;

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
        Optional<Student> student = studentRepository.findByName(fullName);
        if (student.isPresent()) {
            System.out.println(student.get());
        } else {
            System.out.println("Студента з таким ПІБ не знайдено");
        }
    }
    public void findStudentById(String id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
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

        List<StudentDTO> result = studentRepository.findAll().stream()
                .filter(student -> student.getDepartment().equals(department))
                .filter(student -> student.getCourse() == course)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
                .toList();

        if (result.isEmpty()) {
            System.out.println("Студентів не знайдено.");
        } else {
            result.forEach(System.out::println);
        }
        }


    public void setStudentRepository(StudentRepository repository) {
        this.studentRepository = repository;
    }
}
