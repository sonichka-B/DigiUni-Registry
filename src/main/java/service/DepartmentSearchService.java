package service;

import domain.Department;
import repository.DepartmentRepository;

import java.util.Optional;

public class DepartmentSearchService {
    private  DepartmentRepository departmentRepository ;
    public void showAllDepartments(){
        if (departmentRepository.findAll().isEmpty()) {
            System.out.println("Кафедр поки немає.");
            return;
        }
        for(Department department: departmentRepository.findAll()){
            System.out.println(department);
        }
    }

    public void findDepartmentById(String id){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            System.out.println(department.get());
        } else {
            System.out.println("Кафедра з таким id не знайдена");
        }
    }

    public void setDepartmentRepository(DepartmentRepository repository) {
        this.departmentRepository = repository;
    }
}
