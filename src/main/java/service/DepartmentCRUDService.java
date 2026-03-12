package service;

import domain.Department;
import domain.Teacher;
import exceptions.IdAlreadyPresentException;
import exceptions.IncorrectDataException;
import exceptions.NotFoundIDException;
import repository.DepartmentRepository;
import repository.FacultyRepository;
import repository.TeacherRepository;

import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;

public class DepartmentCRUDService {
    private  final DepartmentRepository departmentRepository = new DepartmentRepository();
    private  final FacultyRepository facultyRepository = new FacultyRepository();
    private  final TeacherRepository teacherRepository = new TeacherRepository();

    public void addDepartment(Department department){
        if (department == null) {
            throw new IncorrectDataException("Кафедри не може бути null");
        }
        validateNotEmpty(department.getId(), "ID кафедри");
        validateNotEmpty(department.getName(), "Назва кафедри");
        validateNotEmpty(department.getFaculty(), "ID факультету");
        validateNotEmpty(department.getHead(), "Завідувач кафедри");
        validateNotEmpty(department.getLocation(), "Розташування");
        if (departmentRepository.findById(department.getId()).isPresent()) {
            throw new IdAlreadyPresentException("Кафедра", department.getId());
        }
            facultyRepository.findById(department.getFaculty())
                    .orElseThrow(() -> new NotFoundIDException("Факультет ", department.getFaculty()));
            teacherRepository.findById(department.getHead())
                .orElseThrow(() -> new NotFoundIDException("Вчитель", department.getHead()));

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

    public boolean editDepartment(String id, String newHeadId,String newLocation) {
        Optional<Department> oDepartment = departmentRepository.findById(id);
        if (oDepartment.isEmpty()) {
            return false;
        }
        Department department = oDepartment.get();
        if (newLocation != null && !newLocation.trim().isEmpty()) {
            department.setLocation(newLocation);
        }
        if (newHeadId != null && !newHeadId.trim().isEmpty()) {
            teacherRepository.findById(newHeadId)
                    .orElseThrow(() -> new NotFoundIDException("Завідувач", newHeadId));
            department.setHead(newHeadId);
        }
        return true;
    }

}
