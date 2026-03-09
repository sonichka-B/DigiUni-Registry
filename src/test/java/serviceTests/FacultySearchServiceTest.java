package serviceTests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import service.FacultySearchService;

public class FacultySearchServiceTest {

    @Test
    @ParameterizedTest
    @CsvSource ({"1", "2", "3", "4"})
    public void findFacultyById(String id){
        FacultySearchService facultySearchService = new FacultySearchService();
        facultySearchService.findFacultyById(id);
    }
}
