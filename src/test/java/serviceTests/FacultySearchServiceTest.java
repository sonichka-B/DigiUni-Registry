package serviceTests;

import domain.Faculty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.FacultyCRUDService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FacultySearchServiceTest {
    @ParameterizedTest
    @CsvSource({
            "4, Art",
            "5, Biology",
            "6, IT"
    })
    void findFacultyByIdTest(String id, String name) {
        FacultyCRUDService service = new FacultyCRUDService();
        service.addFaculty(new Faculty(id,name,"art","Dean","111"));
        Faculty faculty = service.getRepository().findById(id).orElse(null);
        assertNotNull(faculty);
        assertEquals(name, faculty.getName());
    }
}
