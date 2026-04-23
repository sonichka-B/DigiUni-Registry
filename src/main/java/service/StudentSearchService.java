package service;

import domain.DTO.DepartmentDTO;
import domain.DTO.StudentDTO;
import domain.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentSearchService {
    private  StudentRepository studentRepository;

    public boolean existsById(String id) {
        return studentRepository.findById(id).isPresent();
    }
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
            Student st = student.get();
            StudentDTO ready = new StudentDTO(
                    st.getId(),
                    st.getPIB(),
                    st.getCourse(),
                    st.getDepartment() != null ? new DepartmentDTO(st.getDepartment().getName()) : null,
                    st.getGroup(),
                    st.getEmail()
            );
            System.out.println(ready);
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
                .filter(student -> student.getDepartment().getName().equals(department))
                .filter(student -> student.getCourse() == course)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(), new DepartmentDTO(student.getDepartment().getName()), student.getGroup(), student.getEmail()))
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

