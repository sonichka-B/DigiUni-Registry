package service;

import domain.DTO.StudentDTO;
import domain.Department;
import domain.Faculty;
import domain.Student;
import exceptions.NotFoundIDException;
import repository.DepartmentRepository;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentSortingService {
    private static StudentRepository studentRepository;
    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    Comparator<Student> byCourse = Comparator.comparing(Student -> Student.getCourse());
    public void sortStudentsByCourse() {
        System.out.println("--- Звіт: Студенти, відсортовані за курсом ---");
        List<StudentDTO> result = studentRepository.findAll().stream()
                .sorted(byCourse)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
                .toList();
        result.forEach(System.out::println);
    }
    public void sortStudentsByCourseInDepartment(String department) {
        System.out.println("--- Звіт: Студенти, відсортовані за курсом в межах кафедри ---");
        List<StudentDTO> result = studentRepository.findAll().stream()
                .filter(student -> student.getDepartment().equals(department))
                .sorted(byCourse)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
                .toList();
        result.forEach(System.out::println);
    }

    Comparator<Student>byAlphabet = Comparator.comparing(Student -> Student.getPIB());

    public void sortStudentsByAlphabetInFaculty(String faculty, String department) {
        System.out.println("--- Звіт: Студенти, відсортовані за алфавітом в межах факультету ---");
        Department departments = new DepartmentRepository().findByFaculty(faculty)
                .orElseThrow (()-> new NotFoundIDException("Кафедру",department));
        List<StudentDTO> result = studentRepository.findAll().stream()
                .filter(student -> departments.equals(department))
                .sorted(byAlphabet)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
                .toList();
        result.forEach(System.out::println);
    }

    public void sortStudentsByAlphabetInDepartment(String department) {
        System.out.println("--- Звіт: Студенти, відсортовані за алфавітом в межах кафедри ---");
        List<StudentDTO> result = studentRepository.findAll().stream()
                .filter(student -> student.getDepartment().equals(department))
                .sorted(byAlphabet)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
                .toList();
        result.forEach(System.out::println);
    }
}
