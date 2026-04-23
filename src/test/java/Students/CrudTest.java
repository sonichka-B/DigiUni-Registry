package Students;

import domain.DTO.DepartmentDTO;
import domain.Department;
import domain.Student;
import exceptions.IncorrectDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import repository.StudentRepository;
import service.StudentCRUDService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CrudTest {
    StudentRepository studentRepository;
    StudentCRUDService studentCRUDService;

    @BeforeEach
    public void setup() {
        studentCRUDService= new StudentCRUDService();
        studentRepository = studentCRUDService.getRepository();
    }

    @Test
    void correct_transferToNewCourse(){
        Student student = new Student("1", "ballie cupper grin", 2, null, 1, 2025,
                "learn", "cool", null, "dsds@sdf.hgj", "+380991234526");
        studentRepository.add(student);
        studentCRUDService.transferToNewCourse("1",5);

        Student update = studentRepository.findById("1").get();
        assertEquals(5,update.getCourse(), "course may be 5");
    }
    @ParameterizedTest
    @ValueSource(ints = {0, 7, -1, 20})
    void incorrect_transferToNewGroup(int group){
        Student student = new Student("1", "ballie cupper grin", 2, null, 1, 2025,
                "learn", "cool", null, "dsds@sdf.hgj", "+380991234526");
        studentRepository.add(student);
        assertThrows(IncorrectDataException.class, () -> studentCRUDService.transferToNewGroup("1", group));
    }

    @ParameterizedTest
    @CsvSource({"1, 5, 4, dep",
            "2, 1, 1, dep",
            "3, 6, 6, dep"})
    public void addStudent(String id, int course, int group,Department department){
        StudentCRUDService service = new StudentCRUDService();
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

        service.getRepository().add(new Student(id, "pib",  course,
                department, group, 4, "бюджет", "навчається",
                dateOfBirth, "email", "1234567890"));
        assertTrue(service.getRepository().findById(id).isPresent());
    }

    @ParameterizedTest
    @CsvSource({"4, 7, -1, dep",
            "5, 0, 7, dep"})
    public void addInvalidStudent(String id, int course, int group, Department department){
        StudentCRUDService service = new StudentCRUDService();
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);
        try {
            service.getRepository().add(new Student(id, "pib",  course,
                    department, group, 4, "бюджет", "навчається",
                    dateOfBirth, "email", "1234567890"));
        }
        catch (IncorrectDataException e){
            throw new IncorrectDataException("not correct data" +e.getMessage());
        }
    }

    @ParameterizedTest
    @CsvSource({"1, dep",
            "2, dep",
            "3, dep"})
    public void deleteStudentTest(String id, Department department) {
        StudentCRUDService service = new StudentCRUDService();
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);
        service.getRepository().add(new Student(id, "pib",  2,
                department, 5, 4, "бюджет", "навчається",
                dateOfBirth, "email", "1234567890"));
        service.deleteStudent(id);
        assertTrue(service.getRepository().findById(id).isEmpty());
    }
}
