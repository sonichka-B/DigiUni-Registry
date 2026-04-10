package service;

import repository.DepartmentRepository;
import repository.StudentRepository;

public class StudentService  {
    private final StudentCRUDService studentCRUDService;
    private final StudentSearchService studentSearchService;
    private final StudentSortingService studentSortingService;

    public StudentService() {
        this.studentCRUDService = new StudentCRUDService();
        this.studentSearchService = new StudentSearchService();
        this.studentSortingService = new StudentSortingService();

        StudentRepository sharedRepo = this.studentCRUDService.getRepository();
        this.studentSearchService.setStudentRepository(sharedRepo);
        this.studentSortingService.setStudentRepository(sharedRepo);
    }

    public StudentCRUDService crud() { return studentCRUDService; }
    public StudentSearchService search() { return studentSearchService; }
    public StudentSortingService sort() { return studentSortingService; }

    public void setSharedDepartmentRepository(DepartmentRepository deptRepo) {
        this.studentCRUDService.setDepartmentRepository(deptRepo);
        this.studentSortingService.setDepartmentRepository(deptRepo);
    }
}
