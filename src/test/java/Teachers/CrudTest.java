package Teachers;

import domain.Teacher;
import exceptions.IdAlreadyPresentException;
import exceptions.NotFoundIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.TeacherRepository;
import service.TeacherCRUDService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CrudTest {
    TeacherCRUDService teacherCRUDService;
    TeacherRepository teacherRepository;

    @BeforeEach
    public void setup(){
        teacherCRUDService = new TeacherCRUDService();
        teacherRepository = teacherCRUDService.getRepository();
    }

    @Test
    void testIdException(){
        Teacher teacher = new Teacher("1", "Козюбра Микола Іванович", null,
                "професор", "доктор наук", "професор", "1992-09-01",
                "1.0", null, "koziubra@ukma.edu.ua", "+380671112215");
        teacherRepository.add(teacher);
        Teacher invalid = new Teacher("1", "dfgg df df", null,"професор",
                "доктор наук", "професор", "1992-09-01", "1.0",
                null, "koziubra@ukma.edu.ua", "+380671112215");

        assertThrows(IdAlreadyPresentException.class, ()-> teacherCRUDService.addTeacher(invalid));
    }
    @Test
    void deleteNotExistingTeacher(){
        Teacher teacher = new Teacher("1", "Козюбра Микола Іванович", null,
                "професор", "доктор наук", "професор", "1992-09-01",
                "1.0", null, "koziubra@ukma.edu.ua", "+380671112215");
        teacherRepository.add(teacher);
        assertEquals(false,teacherCRUDService.deleteTeacher("fake"), "teaher with id:'fake' doesn't exis");
    }
}
