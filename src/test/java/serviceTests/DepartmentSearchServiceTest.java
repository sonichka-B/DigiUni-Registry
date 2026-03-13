package serviceTests;

import domain.Department;
import domain.Faculty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.DepartmentCRUDService;
import service.FacultyCRUDService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DepartmentSearchServiceTest {
    @ParameterizedTest
    @CsvSource({
            "4, Art",
            "5, Biology",
            "6, IT"
    })
    void findDepartmentByIdTest(String id, String name) {
        DepartmentCRUDService service = new DepartmentCRUDService();
        service.addDepartment(new Department(id, name, "faculty", "head","location"));
        Department department = service.getRepository().findById(id).orElse(null);
        assertNotNull(department);
        assertEquals(name, department.getName());
    }
}
