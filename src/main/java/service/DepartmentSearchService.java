package service;

import domain.DTO.DepartmentFullDTO;
import domain.DTO.FacultyDTO;
import domain.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DepartmentRepository;

import java.util.Optional;

public class DepartmentSearchService {
    private  DepartmentRepository departmentRepository ;
    private static final Logger log = LoggerFactory.getLogger(DepartmentSearchService.class);
    public boolean existsById(String id) {
        return departmentRepository.findById(id).isPresent();
    }
    public boolean existsByName(String name) {
        return departmentRepository.findByName(name).isPresent();
    }

    public void showAllDepartments(){
        if (departmentRepository.findAll().isEmpty()) {
            log.warn("Кафедр немає");
            System.out.println("Кафедр поки немає.");
            return;
        }
        departmentRepository.findAll().stream()
                .map(department -> new DepartmentFullDTO(
                        department.getId(),
                        department.getName(),
                        department.getFaculty() != null ? department.getFaculty().getShortName() : "Немає",
                        department.getHead() != null ? department.getHead().getPIB() : "не призначено",
                        department.getLocation()
                ))
                .forEach(System.out::println);
    }

    public void findDepartmentById(String id){
        Optional<Department> department = departmentRepository.findById(id);
        if (department.isPresent()) {
            System.out.println(department.get());
        } else {
            System.out.println("Кафедра з таким id не знайдена");
            log.warn("Пошук: Кафедру з ID '{}' не знайдено", id);
        }
    }

    public void setDepartmentRepository(DepartmentRepository repository) {
        this.departmentRepository = repository;
    }
}
