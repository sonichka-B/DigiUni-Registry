package Departments;

import domain.Department;
import domain.Faculty;
import domain.Teacher;
import exceptions.IncorrectDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DepartmentRepository;
import service.DepartmentCRUDService;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class CrudTests {
    DepartmentCRUDService departmentCRUDService;
    DepartmentRepository departmentRepository;

    @BeforeEach
    public void setup(){
        departmentCRUDService = new DepartmentCRUDService();
        departmentRepository = departmentCRUDService.getRepository();
    }

    @Test
    void addDepWithInvalidHead(){
        Department dep = new Department("1", "math", new Faculty("23"), new Teacher(null), "location");
        assertThrows(IncorrectDataException.class, ()-> departmentCRUDService.addDepartment(dep));
    }
    @Test
    void addEmptyDep(){
        Department d = null;
        assertThrows(IncorrectDataException.class, ()-> departmentCRUDService.addDepartment(d));
    }
}
