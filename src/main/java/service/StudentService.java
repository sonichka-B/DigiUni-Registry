package service;

import domain.Department;
import domain.Faculty;
import domain.Student;
import repository.CRUDRepository;
import repository.DepartmentRepository;
import repository.StudentRepository;

import java.util.*;
import java.util.function.Function;

public class StudentService  {
    private static final StudentRepository studentRepository = new StudentRepository();
    private final StudentCRUDService studentCRUDService;
    private final StudentSearchService studentSearchService;
    private final StudentSortingService studentSortingService;

    public StudentService() {
        this.studentCRUDService = new StudentCRUDService();
        this.studentSearchService = new StudentSearchService();
        this.studentSortingService = new StudentSortingService();
    }

    public StudentCRUDService crud(){
        return studentCRUDService;
    }

    public StudentSearchService search(){
        return studentSearchService;
    }

    public StudentSortingService sort(){
        return studentSortingService;
    }

}
