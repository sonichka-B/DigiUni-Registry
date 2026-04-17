package service;

import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;
import repository.DepartmentRepository;
import repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherService {
    private final TeacherCRUDService teacherCRUDService;
    private final TeacherSearchService teacherSearchService;
    private final TeacherSortingService teacherSortingService;

    public TeacherService() {
        this.teacherCRUDService = new TeacherCRUDService();
        this.teacherSearchService = new TeacherSearchService();
        this.teacherSortingService = new TeacherSortingService();

        TeacherRepository teacherRepository = this.teacherCRUDService.getRepository();
        this.teacherSearchService.setTeacherRepository(teacherRepository);
        this.teacherSortingService.setTeacherRepository(teacherRepository);
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

    public void setSharedDepartmentRepository(DepartmentRepository deptRepo) {
        this.teacherCRUDService.setDepartmentRepository(deptRepo);
        this.teacherSortingService.setDepartmentRepository(deptRepo);
    }
}
