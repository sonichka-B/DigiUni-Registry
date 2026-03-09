package service;

import domain.Department;
import domain.Teacher;
import repository.DepartmentRepository;
import repository.TeacherRepository;

public class DepartmentCRUDService {
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

    public boolean editDepartment(String id, String head){
        Department department = departmentRepository.findById(id);
        TeacherRepository teacherRepository = new TeacherRepository();
        Teacher headTeacher = teacherRepository.findByName(head);
        if (department != null && headTeacher != null) {
            department.setHead(head);
            return true;
        }
        return false;
    }
}
