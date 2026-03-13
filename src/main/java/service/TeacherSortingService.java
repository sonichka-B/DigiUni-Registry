package service;

import domain.Department;
import domain.Faculty;
import domain.Teacher;
import repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherSortingService {
    private static TeacherRepository teacherRepository;

    Comparator<Teacher> byAlphabet = Comparator.comparing(Teacher -> Teacher.getFullName());

    public void sortTeachersByAlphabetInFaculty(String faculty, String department) {
        System.out.println("--- Звіт: Викладачі, відсортовані за алфавітом в межах факультету ---");
        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> result = new ArrayList<>();
        for(Teacher teacher: teachers){
            if(teacher.getDepartment().getFaculty().equals(faculty)){
                result.add(teacher);
            }
        }
        result.sort(byAlphabet);
        result.forEach(System.out::println);
    }

    public void sortTeachersByAlphabetInDepartment(String department) {

    }

    public void setTeacherRepository(TeacherRepository repository) {
    }
}
