package serviceTests;

import domain.Student;
import exceptions.IncorrectDataException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.StudentCRUDService;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @ParameterizedTest
    @CsvSource({"1, 5, 4, dep",
                "2, 1, 1, dep",
                "3, 6, 6, dep"})
    public void addStudent(String id, int course, int group,String department){
        StudentCRUDService service = new StudentCRUDService();
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);

        service.getRepository().add(new Student(id, "pib",  course,
                department, group, 4, "бюджет", "навчається",
                dateOfBirth, "email", "1234567890"));
        assertTrue(service.getRepository().findById(id).isPresent());
    }

    @ParameterizedTest
    @CsvSource({"4, 7, 8, dep",
                "5, 0, 0, dep"})
    public void addInvalidStudent(String id, int course, int group, String department){
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
        public void deleteStudentTest(String id, String department) {
        StudentCRUDService service = new StudentCRUDService();
        LocalDate dateOfBirth = LocalDate.of(2000, 1, 1);
        service.getRepository().add(new Student(id, "pib",  2,
                department, 5, 4, "бюджет", "навчається",
                dateOfBirth, "email", "1234567890"));
        service.deleteStudent(id);
        assertTrue(service.getRepository().findById(id).isEmpty());
        }



}
