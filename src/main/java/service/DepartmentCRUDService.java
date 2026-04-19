package service;

import domain.Department;
import domain.Faculty;
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

    public void setFacultyAndTeacherRepository(FacultyRepository facultyRepository, TeacherRepository teacherRepository) {
        this.facultyRepository = facultyRepository;
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
        validateNotEmpty(department.getFaculty().getId(), "ID факультету");
        validateNotEmpty(department.getLocation(), "Розташування");
        validateNotEmpty(department.getHead().getPIB(),"Ім'я завідувача кафедри");

        Faculty real = facultyRepository.findById(department.getFaculty().getId())
                    .orElseThrow(() -> new NotFoundIDException("Факультет ", department.getFaculty().getId()));
        department.setFaculty(real);
        String head=department.getHead().getPIB();
        if(head!=null && !head.trim().isEmpty()) {
            Teacher realT = teacherRepository.findByName(department.getHead().getPIB())
                    .orElseThrow(() -> new NotFoundNameException("Завідувача", department.getHead().getPIB()));
            department.setHead(realT);
        }
        departmentRepository.add(department);
    }
    public boolean deleteDepartment(String id){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            System.out.println("Кафедру видалено.");
            return true;
        }
        if (department.isEmpty()||department.get()==null){
            System.out.println("Кафедру не видалено.");
            throw new IncorrectDataException("кафедри з таким id немає");
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
            Teacher found = teacherRepository.findByName(newHeadName)
                    .orElseThrow(() -> new NotFoundNameException("Завідувача", newHeadName));
            department.setHead(found);
        }
        return true;
    }

}
