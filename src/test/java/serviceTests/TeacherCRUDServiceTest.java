package serviceTests;

import domain.Department;
import domain.Teacher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import service.TeacherCRUDService;

public class TeacherCRUDServiceTest {

    @Test
    @ParameterizedTest
    @CsvSource ({"1, Professor, PhD, Dr., Computer Science",
                "2, Associate Professor, PhD, Dr., Mathematics",
                "3, Assistant Professor, PhD, Dr., null"})
    public boolean editTeacherTest(String id, String position, String academicDegree, String academicTitle,String firstName,String middleName,String lastName, Department department) {
        TeacherCRUDService teacherCRUDService = new TeacherCRUDService();
        teacherCRUDService.editTeacher(id, position, academicDegree, academicTitle, firstName, middleName, lastName, department);
        return true;
    }


}
