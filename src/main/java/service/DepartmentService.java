package service;

import domain.Department;
import repository.DepartmentRepository;

public class DepartmentService {
    private  final DepartmentRepository departmentRepository = new DepartmentRepository();

    public void addDepartment(Department department){
        departmentRepository.save(department);
    }

    public void findDepartmentByName(String name){
        Department department = departmentRepository.findByName(name);
        if (department != null) {
            System.out.println(department);
        } else {
            System.out.println("Department not found.");
        }
    }
    public void listAllDepartments(){
        Department[] departments = departmentRepository.findAll();
        for (Department department : departments) {
            System.out.println(department);
        }
    }
    public void deleteDepartment(String name){
        departmentRepository.deleteByName(name);
    }
}
