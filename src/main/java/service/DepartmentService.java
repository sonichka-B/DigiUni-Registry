package service;

import domain.Department;
import domain.Teacher;
import repository.DepartmentRepository;
import repository.FacultyRepository;
import repository.TeacherRepository;

public class DepartmentService {
    private final DepartmentCRUDService departmentCRUDService;
    private final DepartmentSearchService departmentSearchService;

    public DepartmentService() {
        this.departmentCRUDService = new DepartmentCRUDService();
        this.departmentSearchService = new DepartmentSearchService();

        DepartmentRepository departmentRepository = this.departmentCRUDService.getRepository();
        this.departmentSearchService.setDepartmentRepository(departmentRepository);
    }

    public DepartmentCRUDService crud() {
        return departmentCRUDService;
    }

    public DepartmentSearchService search() {
        return departmentSearchService;
    }

    public void setSharedFacAndTeachRep(FacultyRepository facultyRepository, TeacherRepository teacherRepository){
        this.departmentCRUDService.setFacultyAndTeacherRepository(facultyRepository,teacherRepository);
    }
}
