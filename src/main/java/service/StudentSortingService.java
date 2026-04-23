package service;

import domain.DTO.DepartmentDTO;
import domain.DTO.StudentDTO;
import domain.Department;
import domain.Faculty;
import domain.Student;
import exceptions.IncorrectDataException;
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
    private DepartmentRepository departmentRepository;
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    Comparator<Student> byCourse = Comparator.comparing(Student -> Student.getCourse());
    public void sortStudentsByCourse() {
        System.out.println("--- Звіт: Студенти, відсортовані за курсом ---");
        List<StudentDTO> result = studentRepository.findAll().stream()
                .sorted(byCourse)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  new DepartmentDTO(student.getDepartment().getName()), student.getGroup(), student.getEmail()))
                .toList();
        if (result.isEmpty()) {
//            System.out.println("Студентів не знайдено.");
            throw new IncorrectDataException("Студентів не знайдено.");
        } else {
            result.forEach(System.out::println);
        }
    }
    public void sortStudentsByCourseInDepartment(String department) {
        System.out.println("--- Звіт: Студенти, відсортовані за курсом в межах кафедри ---");
        List<StudentDTO> result = studentRepository.findAll().stream()
                .filter(student -> student.getDepartment().getName().equals(department))
                .sorted(byCourse)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  new DepartmentDTO(student.getDepartment().getName()), student.getGroup(), student.getEmail()))
                .toList();
        if (result.isEmpty()) {
            throw new IncorrectDataException("Студентів не знайдено.");
        } else {
            result.forEach(System.out::println);
        }
    }

    Comparator<Student>byAlphabet = Comparator.comparing(Student -> Student.getPIB());

    public void sortStudentsByAlphabetInFaculty(String faculty) {
        System.out.println("--- Звіт: Студенти, відсортовані за алфавітом в межах факультету ---");
        List<String> validDepartments = departmentRepository.findAll().stream()
                .filter(dep -> dep.getFaculty().getName().trim().equalsIgnoreCase(faculty.trim()))
                .map(Department::getName)
                .toList();
        List<StudentDTO> result = studentRepository.findAll().stream()
                .filter(student -> validDepartments.contains(student.getDepartment().getName()))
                .sorted(byAlphabet)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  new DepartmentDTO(student.getDepartment().getName()), student.getGroup(), student.getEmail()))
                .toList();
        if (result.isEmpty()) {
            throw new IncorrectDataException("Студентів не знайдено.");
        } else {
            result.forEach(System.out::println);
        }
    }

    public void sortStudentsByAlphabetInDepartment(String department) {
        System.out.println("--- Звіт: Студенти, відсортовані за алфавітом в межах кафедри ---");
        List<StudentDTO> result = studentRepository.findAll().stream()
                .filter(student -> student.getDepartment().getName().equals(department))
                .sorted(Comparator.comparing(Student::getPIB))
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                        student.getCourse(),  new DepartmentDTO(student.getDepartment().getName()), student.getGroup(), student.getEmail()))
                .toList();
        if (result.isEmpty()) {
            throw new IncorrectDataException("Студентів не знайдено.");
        } else {
            result.forEach(System.out::println);
        }
    }
}
