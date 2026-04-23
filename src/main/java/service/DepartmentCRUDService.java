package service;

import domain.Department;
import domain.Faculty;
import domain.Role;
import domain.Teacher;
import exceptions.IdAlreadyPresentException;
import exceptions.IncorrectDataException;
import exceptions.NotFoundIDException;
import exceptions.NotFoundNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DepartmentRepository;
import repository.FacultyRepository;
import repository.TeacherRepository;
import security.RoleAnotation;

import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;

@RoleAnotation(requireRole={Role.ADMIN, Role.MANAGER})
public class DepartmentCRUDService {
    private final DepartmentRepository departmentRepository = new DepartmentRepository();
    private FacultyRepository facultyRepository;
    private TeacherRepository teacherRepository;
    private static final Logger log = LoggerFactory.getLogger(DepartmentCRUDService.class);

    public DepartmentRepository getRepository() {
        return departmentRepository;
    }

    public void setFacultyAndTeacherRepository(FacultyRepository facultyRepository, TeacherRepository teacherRepository) {
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
    }

    public void addDepartment(Department department){
        if (department == null) {
            log.warn("Спроба додати кафедру: передано null");
            throw new IncorrectDataException("Кафедри не може бути null");
        }
        if(departmentRepository.findById(department.getId()).isPresent()){
            log.warn("Кафедра з ID='{}' вже існує", department.getId());
            throw new IdAlreadyPresentException("Кафедра", department.getId());
        }

        validateNotEmpty(department.getName(), "Назва кафедри");
        validateNotEmpty(department.getFaculty().getId(), "ID факультету");
        validateNotEmpty(department.getLocation(), "Розташування");
        validateNotEmpty(department.getHead().getPIB(),"Ім'я завідувача кафедри");

        Faculty real = facultyRepository.findById(department.getFaculty().getId())
                .orElseThrow(() -> new NotFoundIDException("Факультет ", department.getFaculty().getId()));
        department.setFaculty(real);

        String head = department.getHead().getPIB();
        if(head != null && !head.trim().isEmpty()) {
            Teacher realT = teacherRepository.findByName(department.getHead().getPIB())
                    .orElseThrow(() -> new NotFoundNameException("Завідувача", department.getHead().getPIB()));
            department.setHead(realT);
        }

        departmentRepository.add(department);
        log.info("Створено нову кафедру: ID={}, Назва={}", department.getId(), department.getName());
    }

    public boolean deleteDepartment(String id){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            departmentRepository.delete(department.get());
            log.info("Кафедру з ID='{}' успішно видалено", id);
            return true;
        }
        log.warn("Спроба видалення: Кафедру з ID='{}' не знайдено", id);
        throw new IncorrectDataException("кафедри з таким id немає");
    }

    public boolean editDepartment(String id, String newHeadName, String newLocation) {
        Optional<Department> oDepartment = departmentRepository.findById(id);
        if (oDepartment.isEmpty()) {
            log.warn("Спроба редагування: Кафедру з ID='{}' не знайдено", id);
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
        log.info("Дані кафедри з ID='{}' відредаговано", id);
        return true;
    }
}