package service;

import domain.DTO.DepartmentDTO;
import domain.DTO.TeacherDTO;
import domain.Department;
import domain.Faculty;
import domain.Teacher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DepartmentRepository;
import repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherSortingService {
    private TeacherRepository teacherRepository;

    Comparator<Teacher> byAlphabet = Comparator.comparing(Teacher -> Teacher.getPIB());
    private static final Logger log = LoggerFactory.getLogger(TeacherSortingService.class);
    public void sortTeachersByAlphabetInFaculty(String faculty) {
        System.out.println("--- Звіт: Викладачі, відсортовані за алфавітом в межах факультету ---");

        List<TeacherDTO> result = teacherRepository.findAll().stream()
                .filter(teacher -> teacher.getDepartment().getFaculty().getName().equals(faculty))
                .sorted(byAlphabet)
                .map(teacher -> new TeacherDTO(
                        teacher.getId(),
                        teacher.getPIB(),
                        new DepartmentDTO(teacher.getDepartment().getName()),
                        teacher.getPosition(),
                        teacher.getEmail()
                ))
                .toList();
        result.forEach(System.out::println);
    }

    public void sortTeachersByAlphabetInDepartment(String department) {
        System.out.println("--- Звіт: Викладачі кафедри '" + department + "' (за алфавітом) ---");

        List<TeacherDTO> result = teacherRepository.findAll().stream()
                .filter(teacher -> teacher.getDepartment() != null &&
                        teacher.getDepartment().getName().trim().equalsIgnoreCase(department.trim()))
                .sorted(byAlphabet)
                .map(teacher -> new TeacherDTO(
                        teacher.getId(),
                        teacher.getPIB(),
                        new DepartmentDTO(teacher.getDepartment().getName()),
                        teacher.getPosition(),
                        teacher.getEmail()
                ))
                .toList();

        if (result.isEmpty()) {
            log.warn("Викладачів на цій кафедрі не знайдено.");
        } else {
            result.forEach(System.out::println);
        }
    }

    public void setTeacherRepository(TeacherRepository repository) {
        this.teacherRepository = repository;
    }
    public void setDepartmentRepository(DepartmentRepository deprepository) {
        deprepository= deprepository;
    }
}
