package service;

import domain.Department;
import domain.Teacher;
import repository.DepartmentRepository;

public class DepartmentService {
    private final DepartmentCRUDService departmentCRUDService;
    private final DepartmentSearchService departmentSearchService;

    public DepartmentService() {
        this.departmentCRUDService = new DepartmentCRUDService();
        this.departmentSearchService = new DepartmentSearchService();

        DepartmentRepository departmentRepository = new DepartmentRepository();
        this.departmentSearchService.setDepartmentRepository(departmentRepository);
    }

    public DepartmentCRUDService crud() {
        return departmentCRUDService;
    }

    public DepartmentSearchService search() {
        return departmentSearchService;
    }
}
