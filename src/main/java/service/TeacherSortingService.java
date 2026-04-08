package service;

import domain.DTO.TeacherDTO;
import domain.Department;
import domain.Faculty;
import domain.Teacher;
import repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherSortingService {
    private static TeacherRepository teacherRepository;

    Comparator<Teacher> byAlphabet = Comparator.comparing(Teacher -> Teacher.getPIB());

    public void sortTeachersByAlphabetInFaculty(String faculty, String department) {
        System.out.println("--- Звіт: Викладачі, відсортовані за алфавітом в межах факультету ---");
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDTO> result = teacherRepository.findAll().stream()
                .filter(teacher -> teacher.getDepartment().getFaculty().equals(faculty))
                .sorted(byAlphabet)
                .map(teacher -> new TeacherDTO(
                        teacher.getId(),
                        teacher.getPIB(),
                        teacher.getDepartment(),
                        teacher.getPosition(),
                        teacher.getEmail()
                ))
                .toList();
        result.forEach(System.out::println);
    }

    public void sortTeachersByAlphabetInDepartment(String department) {

    }

    public void setTeacherRepository(TeacherRepository repository) {
    }
}
