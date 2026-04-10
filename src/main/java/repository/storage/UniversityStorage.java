package repository.storage;

import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter@Getter
public class UniversityStorage {
    private List<Student> students;
    private List<Teacher> teachers;
    private List<Department> departments;
    private List<Faculty>faculties;

    public UniversityStorage(){}

    public UniversityStorage(List<Student> students,  List<Teacher> teachers, List<Department> departments,
                             List<Faculty>faculties){
        this.students = students;
        this.teachers = teachers;
        this.departments = departments;
        this.faculties = faculties;
    }
}
