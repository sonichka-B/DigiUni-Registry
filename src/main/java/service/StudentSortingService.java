package service;

import domain.Department;
import domain.Faculty;
import domain.Student;
import repository.StudentRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentSortingService {
    private static final StudentRepository studentRepository = new StudentRepository();

    Comparator<Student> byCourse = Comparator.comparing(Student -> Student.getCourse());
    public void sortStudentsByCourse() {
        System.out.println("--- Звіт: Студенти, відсортовані за курсом ---");
        List<Student> students = studentRepository.findAll();
        List<Student> result = new ArrayList<>();
        result.addAll(students);
        result.sort(byCourse);
        result.forEach(System.out::println);
    }
    public void sortStudentsByCourseInDepartment(Department department) {
        System.out.println("--- Звіт: Студенти, відсортовані за курсом в межах кафедри ---");
        List<Student> students = studentRepository.findAll();
        List<Student> result = new ArrayList<>();
        for(Student student: students){
            if(student.getDepartment() == department){
                result.add(student);
            }
        }
        result.sort(byCourse);
        result.forEach(System.out::println);
    }

    Comparator<Student>byAlphabet = Comparator.comparing(Student -> Student.getFullName());

    public void sortStudentsByAlphabetInFaculty(Faculty faculty, Department department) {
        System.out.println("--- Звіт: Студенти, відсортовані за алфавітом в межах факультету ---");
        List<Student> students = studentRepository.findAll();
        List<Student> result = new ArrayList<>();
        for(Student student: students){
            if(student.getDepartment().getFaculty() == faculty){
                result.add(student);
            }
        }
        result.sort(byAlphabet);
        result.forEach(System.out::println);
    }

    public void sortStudentsByAlphabetInDepartment(Department department) {
        System.out.println("--- Звіт: Студенти, відсортовані за алфавітом в межах кафедри ---");
        List<Student> students = studentRepository.findAll();
        List<Student> result = new ArrayList<>();
        for(Student student: students){
            if(student.getDepartment() == department){
                result.add(student);
            }
        }
        result.sort(byAlphabet);
        result.forEach(System.out::println);
    }
}
