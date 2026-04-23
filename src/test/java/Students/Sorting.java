package Students;


import domain.Department;
import exceptions.IncorrectDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.DepartmentRepository;
import repository.StudentRepository;
import service.StudentCRUDService;
import service.StudentSortingService;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class Sorting {
    StudentRepository studentRepository;
    StudentCRUDService studentCRUDService;
    StudentSortingService sortingService;

    @BeforeEach
    public void setup() {
        studentCRUDService= new StudentCRUDService();
        studentRepository = studentCRUDService.getRepository();
        sortingService = new StudentSortingService();
        sortingService.setStudentRepository(studentRepository);
        DepartmentRepository deprep = new DepartmentRepository();
        deprep.add(new Department("Development"));
        sortingService.setDepartmentRepository(deprep);
    }

    @Test
    void sortAlphabetDep_exception(){
       assertThrows(IncorrectDataException.class, ()-> sortingService.sortStudentsByAlphabetInDepartment("Development"));
    }
    @Test
    void sortCourse_exception(){
        assertThrows(IncorrectDataException.class, ()-> sortingService.sortStudentsByCourse());
    }
}
