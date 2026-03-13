package serviceTests;

import domain.Faculty;
import org.junit.jupiter.api.Test;
import service.FacultyCRUDService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FacultyCRUDServiceTest {
    @Test
    void addFacultyTest() {
        FacultyCRUDService service = new FacultyCRUDService();
        Faculty faculty = new Faculty("1", "IT", "fi", "Dean", "12345");
        service.addFaculty(faculty);
        assertTrue(service.getRepository().findById("1").isPresent());
    }

    @Test
    void deleteFacultyTest() {
        FacultyCRUDService service = new FacultyCRUDService();
        Faculty faculty = new Faculty("2", "Biology", "bio", "Dean", "54321");
        service.addFaculty(faculty);
        boolean deleted = service.deleteFaculty("2");
        assertTrue(deleted);
        assertTrue(service.getRepository().findById("2").isEmpty());
    }

    @Test
    void editFacultyTest() {
        FacultyCRUDService service = new FacultyCRUDService();
        Faculty faculty = new Faculty("3", "Art", "art", "Dean", "11111");
        service.addFaculty(faculty);
        service.editFaculty("3", null, "99999");
        Faculty updated = service.getRepository().findById("3").get();
        assertEquals("99999", updated.getPhoneNumber());
    }
}
