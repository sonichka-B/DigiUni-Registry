package service;

import domain.Department;
import domain.Teacher;
import repository.DepartmentRepository;

public class DepartmentService {
    private  final DepartmentRepository departmentRepository = new DepartmentRepository();

    public void addDepartment(Department department){
        if(department != null && department.getName() != null){
            departmentRepository.add(department);
        } else {
            System.out.println("Помилка: Кафедра не може бути null і має мати ім'я");
        }
    }

    public void deleteDepartment(String id){
        Department department = departmentRepository.findById(id);
        if (department != null) {
            departmentRepository.delete(department);
        } else {
            System.out.println("Кафедра з таким id не знайдена");
        }
    }

    public boolean editDepartment(String id, Teacher head){
        Department department = departmentRepository.findById(id);
        if (department != null && head != null && head.getFullName() != null) {
            department.setHead(head);
            return true;
        }
        return false;
    }

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
