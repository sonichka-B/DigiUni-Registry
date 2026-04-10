package service;

import domain.Department;
import domain.Role;
import domain.Teacher;
import exceptions.IdAlreadyPresentException;
import exceptions.IncorrectDataException;
import exceptions.NotFoundIDException;
import exceptions.NotFoundNameException;
import repository.DepartmentRepository;
import repository.FacultyRepository;
import repository.TeacherRepository;
import security.RoleAnotation;

import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;
@RoleAnotation(requireRole={Role.ADMIN, Role.MANAGER})

public class DepartmentCRUDService {
    private  final DepartmentRepository departmentRepository = new DepartmentRepository();
    private  FacultyRepository facultyRepository ;
    private  TeacherRepository teacherRepository ;

    public DepartmentRepository getRepository() {
        return departmentRepository;
    }
    public void setFacultyRepository(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public void addDepartment(Department department){
        if (department == null) {
            throw new IncorrectDataException("Кафедри не може бути null");
        }
        if(departmentRepository.findById(department.getId()).isPresent()){
            throw new IdAlreadyPresentException("Кафедра", department.getId());
        }

        validateNotEmpty(department.getName(), "Назва кафедри");
        validateNotEmpty(department.getFaculty(), "ID факультету");
        validateNotEmpty(department.getLocation(), "Розташування");
          /*  facultyRepository.findById(department.getFaculty())
                    .orElseThrow(() -> new NotFoundIDException("Факультет ", department.getFaculty()));
        String head=department.getHead();
        if(head!=null && !head.trim().isEmpty()) {
            teacherRepository.findByName(department.getHead())
                    .orElseThrow(() -> new NotFoundNameException("Завідувача", department.getHead()));
        }*/
        departmentRepository.add(department);
    }
    public boolean deleteDepartment(String id){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            return true;
        }
        return false;
    }
    public boolean editDepartment(String id, String newHeadName,String newLocation) {
        Optional<Department> oDepartment = departmentRepository.findById(id);
        if (oDepartment.isEmpty()) {
            return false;
        }
        Department department = oDepartment.get();
        if (newLocation != null && !newLocation.trim().isEmpty()) {
            department.setLocation(newLocation);
        }
        if (newHeadName != null && !newHeadName.trim().isEmpty()) {
            teacherRepository.findByName(newHeadName)
                    .orElseThrow(() -> new NotFoundNameException("Завідувача", newHeadName));
            department.setHead(newHeadName);
        }
        return true;
    }

}
