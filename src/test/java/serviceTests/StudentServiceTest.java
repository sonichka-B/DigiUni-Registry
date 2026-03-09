package serviceTests;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import repository.DepartmentRepository;
import service.StudentService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentServiceTest {


    @Test
    public void addValidStudent(){
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);
        StudentService studentService = new StudentService();
        studentService.crud().addStudent(new Student("1", "firstname", "middlename", "lastname", 3,
                "fsd", 3, 4, "бюджет", "навчається",
                dateOfBirth, "email", "1234567890"));
    }

    @Test
    public void addInvalidStudent(){
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);
        StudentService studentService = new StudentService();
        studentService.crud().addStudent(new Student("2", "firstname", "middlename", "lastname", 3,
                "fsd", 0, 4, "invalidType", "навчається",
                dateOfBirth, "email", "1234567890"));

        assertThrows(IllegalArgumentException.class, () -> {
            studentService.crud().addStudent(new Student("3", "firstname", "middlename", "lastname", 3,
                    "fsd", 3, 4, "бюджет", "invalidStatus",
                    dateOfBirth, "email", "1234567890"));
        });
    }

    @ParameterizedTest
        @ValueSource(strings = {"1", "2", "3"})
        public void deleteStudent(String id) {
            StudentService studentService = new StudentService();
            studentService.crud().deleteStudent(id);
        }


}
