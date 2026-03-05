package service;

import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;
import repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherService {
    private static final TeacherRepository teacherRepository = new TeacherRepository();
    private final TeacherCRUDService teacherCRUDService;
    private final TeacherSearchService teacherSearchService;
    private final TeacherSortingService teacherSortingService;

    public TeacherService() {
        this.teacherCRUDService = new TeacherCRUDService();
        this.teacherSearchService = new TeacherSearchService();
        this.teacherSortingService = new TeacherSortingService();
    }

    public TeacherCRUDService crud() {
        return teacherCRUDService;
    }

    public TeacherSearchService search() {
        return teacherSearchService;
    }

    public TeacherSortingService sorting() {
        return teacherSortingService;
    }
}
