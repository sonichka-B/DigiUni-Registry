package Faculties;

import domain.Faculty;
import domain.Teacher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.FacultyRepository;
import repository.TeacherRepository;
import service.FacultyCRUDService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacultyCRUDServiceTest {
    FacultyCRUDService facultyCRUDService;
    FacultyRepository facultyRepository;
    TeacherRepository teacherRepository;

    @BeforeEach
    public void setup(){
        facultyCRUDService = new FacultyCRUDService();
        facultyRepository = facultyCRUDService.getRepository();
        teacherRepository = new TeacherRepository();
        facultyCRUDService.setTeacherRepository(teacherRepository);
        teacherRepository.add(new Teacher("testTeacher"));
    }
    @Test
    void addFacultyTest() {
        Faculty faculty = new Faculty("1", "IT", "fi", new Teacher("testTeacher"), "12345");
        facultyRepository.add(faculty);
        assertTrue(facultyCRUDService.getRepository().findById("1").isPresent());
    }

    @Test
    void deleteFacultyTest() {

        Faculty faculty = new Faculty("2", "Biology", "bio", new Teacher("testTeacher"), "54321");
        facultyRepository.add(faculty);
        boolean deleted = facultyCRUDService.deleteFaculty("2");
        assertTrue(deleted);
        assertTrue(facultyRepository.findById("2").isEmpty());
    }

    @Test
    void editFacultyTest() {
        Faculty faculty = new Faculty("3", "Art", "art", new Teacher("testTeacher"), "11111");
        facultyRepository.add(faculty);
        facultyCRUDService.editFaculty("3", null, "99999");
        Faculty updated = facultyRepository.findById("3").get();
        assertEquals("99999", updated.getPhoneNumber());
    }
}
