package Students;

import domain.Department;
import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import repository.StudentRepository;
import service.StudentCRUDService;
import service.StudentSearchService;

import static org.junit.jupiter.api.Assertions.*;

public class SearchTest {
    StudentRepository studentRepository;
    StudentCRUDService studentCRUDService;
    StudentSearchService studentSearchService;

    @BeforeEach
    public void setup() {
        studentCRUDService= new StudentCRUDService();
        studentRepository = studentCRUDService.getRepository();
        studentSearchService = new StudentSearchService();
        studentSearchService.setStudentRepository(studentRepository);
    }

    @ParameterizedTest
    @CsvSource({"1, 1","2, 2", "3, 0"})
    void findByCourse_correct(int course, int expected){
        Student student = new Student("1", "ballie cupper grin", 2, new Department("d"), 1, 2025,
                "learn", "cool", null, "dsds@sdf.hgj", "+380991234526");
        studentRepository.add(student);
        Student student1 = new Student("3", "abc", 2, new Department("d"), 1, 2025,
                "learn", "cool", null, "dsds@sdf.hgj", "+380991234526");
        studentRepository.add(student1);
        Student student2 = new Student("6", "dd", 1, new Department("d"), 1, 2025,
                "learn", "cool", null, "dsds@sdf.hgj", "+380991234526");
        studentRepository.add(student2);
        var res =  studentRepository.findByCourse(course);
        assertEquals(expected, res.size());
    }
    @Test
    void findByName(){
        Student student = new Student("1", "ballie cupper grin", 2, new Department("d"), 1, 2025,
                "learn", "cool", null, "dsds@sdf.hgj", "+380991234526");
        studentRepository.add(student);
        assertTrue(studentRepository.findByName("ballie cupper grin").isPresent());
    }
    @Test
    void incorrect_findByName(){
        Student student = new Student("1", "ballie cupper grin", 2, new Department("d"), 1, 2025,
                "learn", "cool", null, "dsds@sdf.hgj", "+380991234526");
        studentRepository.add(student);
        assertFalse(studentRepository.findByName("dfg").isPresent());
    }
}
