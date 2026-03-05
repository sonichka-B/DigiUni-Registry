package service;

import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;
import repository.TeacherRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TeacherService {
    private static final TeacherRepository teacherRepository = new TeacherRepository();

    public void addTeacher(Teacher teacher) {
        if(teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        if(teacher.getFullName() == null ) {
            throw new IllegalArgumentException("Teacher's name fields cannot be null");
        }
        if(teacher.getPosition() == null) {
            throw new IllegalArgumentException("Teacher's position cannot be null");
        }
        if(teacher.getAcademicDegree() == null) {
            throw new IllegalArgumentException("Teacher's academic degree cannot be null");
        }
        if(teacher.getAcademicTitle() == null) {
            throw new IllegalArgumentException("Teacher's academic title cannot be null");
        }
        if(teacherRepository.findByFullName(teacher.getFullName()) != null) {
            throw new IllegalArgumentException("A teacher with the same full name already exists");
        }
        if(teacher.getDepartment() == null || teacher.getDepartment().getName() == null) {
            throw new IllegalArgumentException("Teacher must be associated with a department");
        }
        teacherRepository.add(teacher);
    }
    public void deleteTeacher(String id) {
        Teacher teacher = teacherRepository.findById(id);
        if(teacher != null) {
            teacherRepository.delete(teacher);
        } else {
            System.out.println("Викладача з таким id не знайдено");
        }
    }

    public boolean editTeacher(String id, String position, String academicDegree, String academicTitle, Department department) {
        Teacher teacher = teacherRepository.findById(id);
        if (teacher != null && department.getName() != null) {
            teacher.setPosition(position);
            teacher.setAcademicDegree(academicDegree);
            teacher.setAcademicTitle(academicTitle);
            teacher.setDepartment(department);
            return true;
        }
        return false;
    }

    public void showAllTeachers() {
        for (Teacher teacher : teacherRepository.findAll()) {
            System.out.println(teacher);
        }
    }

    public void findTeacherByFullName(String fullName) {
        System.out.println("--- Звіт: Викладач з ПІБ " + fullName + " " + " ---");
        Teacher teacher = teacherRepository.findByFullName(fullName);
        if (teacher != null) {
            System.out.println(teacher);
        } else {
            System.out.println("Викладача з таким ПІБ не знайдено");
        }
    }

    public void findTeacherById(String id) {
        System.out.println("--- Звіт: Викладач з id " + id + " " + " ---");
        Teacher teacher = teacherRepository.findById(id);
        if (teacher != null) {
            System.out.println(teacher);
        } else {
            System.out.println("Викладача з таким id не знайдено");
        }
    }

    Comparator<Teacher> byAlphabet = Comparator.comparing(Teacher -> Teacher.getFullName());

    public void sortTeachersByAlphabetInFaculty(Faculty faculty, Department department) {
        System.out.println("--- Звіт: Викладачі, відсортовані за алфавітом в межах факультету ---");
        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> result = new ArrayList<>();
        for(Teacher teacher: teachers){
            if(teacher.getDepartment().getFaculty() == faculty){
                result.add(teacher);
            }
        }
        result.sort(byAlphabet);
        result.forEach(System.out::println);
    }

    public void sortTeachersByAlphabetInDepartment(Department department) {
        System.out.println("--- Звіт: Викладачі, відсортовані за алфавітом в межах кафедри ---");
        List<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> result = new ArrayList<>();
        for(Teacher teacher: teachers){
            if(teacher.getDepartment() == department){
                result.add(teacher);
            }
        }
        result.sort(byAlphabet);
        result.forEach(System.out::println);
    }
}
