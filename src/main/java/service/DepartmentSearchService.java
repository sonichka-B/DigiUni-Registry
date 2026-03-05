package service;

import domain.Department;
import repository.DepartmentRepository;

public class DepartmentSearchService {
    private  final DepartmentRepository departmentRepository = new DepartmentRepository();

    public void showAllDepartments(){
        for(Department department: departmentRepository.findAll()){
            System.out.println(department);
        }
    }

    public void findDepartmentById(String id){
        Department department = departmentRepository.findById(id);
        if (department != null) {
            System.out.println(department);
        } else {
            System.out.println("Кафедра з таким id не знайдена");
        }
    }
}
