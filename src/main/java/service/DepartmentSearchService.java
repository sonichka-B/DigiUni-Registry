package service;

import domain.Department;
import repository.DepartmentRepository;

import java.util.Optional;

public class DepartmentSearchService {
    private  final DepartmentRepository departmentRepository = new DepartmentRepository();

    public void showAllDepartments(){
        for(Department department: departmentRepository.findAll()){
            System.out.println(department);
        }
    }

    public void findDepartmentById(String id){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            System.out.println(department);
        } else {
            System.out.println("Кафедра з таким id не знайдена");
        }
    }
}
