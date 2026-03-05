package service;

import domain.Department;
import domain.Faculty;
import domain.Student;
import repository.DepartmentRepository;
import repository.StudentRepository;

import java.util.*;
import java.util.function.Function;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student student) {
        if(student != null){ //додай ще якісь перевірки
            studentRepository.add(student);
        }
    }

    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id);
        if(student != null) {
            studentRepository.delete(student);
        } else {
            System.out.println("Студента з таким id не знайдено");
        }
    }

    public boolean editStudent(String id, int course, int group, String status, Department department) {
        Student student = studentRepository.findById(id);
        if (student!= null) {
            student.setCourse(course);
            student.setGroup(group);
            student.setStatus(status);
            student.setDepartment(department);
            return true;
        }
        return false;
    }

    public void transferToNewDepartment(String id, Department department) {
        Student student = studentRepository.findById(id);
        if (student != null && department.getName() != null) {
            student.setDepartment(department);
        }
    }

    public void transferToNewCourse(String id, int newCourse) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setCourse(newCourse);
        }
    }

    public void transferToNewGroup(String id, int newGroup) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setGroup(newGroup);
        }
    }

    public void showAllStudents() {
        studentRepository.findAll().forEach(System.out::println);
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
    Comparator<Student>byCourse = Comparator.comparing(Student -> Student.getCourse());
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

    public void showStudentsInDepartmentAndCourse(Department department, int course) {
        System.out.println("--- Звіт: Студенти " + course + " курсу в межах кафедри ---");
        List<Student> students = studentRepository.findAll();
        List<Student> result = new ArrayList<>();
        for(Student student:students){
            if(student.getDepartment() == department && student.getCourse() == course){
                result.add(student);
            }
        }
        result.forEach(System.out::println);
    }
}
