package ui;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;
import service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        StudentSearchService studentSearchService = new StudentSearchService();
        TeacherSearchService teacherSearchService = new TeacherSearchService();
        FacultySearchService facultySearchService = new FacultySearchService();
        DepartmentSearchService departmentSearchService = new DepartmentSearchService();
        TeacherSortingService teacherSortingService = new TeacherSortingService();
        StudentSortingService studentSortingService = new StudentSortingService();

        StudentService studentService = new StudentService();
        StudentCRUDService studentCRUDService = new StudentCRUDService();
        studentSortingService.setStudentRepository(studentCRUDService.getRepository());
        studentSearchService.setStudentRepository(studentCRUDService.getRepository());

        studentCRUDService.getRepository().add(new Student("1", "Марія Іванівна Козаченко",  2,
                "Біологія", 1, 2022, "бюджет", "навчається", LocalDate.of(2004, 2, 27), "mariya@ukr.net", "+380991234567"));
        studentCRUDService.getRepository().add(new Student("2", "Іван Сергійович Петренко", 3,
                "Комп'ютерні науки", 2, 2021, "контракт", "навчається", LocalDate.of(2003, 5, 15), "ivan@ukr.net", "+380997654321"));

        TeacherService teacherService = new TeacherService();
        TeacherCRUDService teacherCRUDService = new TeacherCRUDService();
        teacherSortingService.setTeacherRepository(teacherCRUDService.getRepository());
        teacherSearchService.setTeacherRepository(teacherCRUDService.getRepository());

        Department compSci = new Department("201", "Комп'ютерні науки", "101", "Alice Brown", "Корпус 1");
        Department biology = new Department("202", "Біологія", "102", "Bob Johnson", "Корпус 2");

        teacherCRUDService.getRepository().add(new Teacher("1", "Alice B. Brown",  compSci,
                "доцент", "кандидат наук", "доцент", "2010-09-01", "1.0", LocalDate.of(1980, 3, 10), "alice@ukma.edu.ua", "+380951112233"));
        teacherCRUDService.getRepository().add(new Teacher("2", "Bob J. Johnson",  biology,
                "професор", "доктор наук", "професор", "2005-02-15", "1.0", LocalDate.of(1975, 7, 20), "bob@ukma.edu.ua", "+380952223344"));

        FacultyService facultyService = new FacultyService();
        FacultyCRUDService facultyCRUDService = new FacultyCRUDService();
        facultySearchService.setFacultyRepository(facultyCRUDService.getRepository());
        facultyCRUDService.getRepository().add(new Faculty("101", "Інженерія", "ІНЖ", "Alice Brown", "+380441112233"));
        facultyCRUDService.getRepository().add(new Faculty("102", "Наука", "НАУК", "Bob Johnson", "+380449998877"));

        DepartmentService departmentService = new DepartmentService();
        DepartmentCRUDService departmentCRUDService = new DepartmentCRUDService();
        departmentSearchService.setDepartmentRepository(departmentCRUDService.getRepository());
        departmentCRUDService.getRepository().add(compSci);
        departmentCRUDService.getRepository().add(biology);

        SearchTeacher searchTeacher = new SearchTeacher(teacherSearchService);
        SearchStudent searchStudent = new SearchStudent(studentSearchService);

        FacultyMenu facultyMenu = new FacultyMenu(facultyCRUDService, facultySearchService);

        DepartmentMenu departmentMenu = new DepartmentMenu(departmentCRUDService, departmentSearchService, facultySearchService,
                teacherCRUDService.getRepository(), facultyCRUDService.getRepository());

        StudentMenu studentMenu = new StudentMenu(studentService, searchStudent, studentCRUDService, studentSortingService, studentSearchService);
        TeacherMenu teacherMenu = new TeacherMenu(teacherService, searchTeacher, teacherSortingService, teacherCRUDService, teacherSearchService);

        MainMenu mainMenu = new MainMenu(studentMenu, teacherMenu, departmentMenu, facultyMenu);
        AuthorizationMenu authorizationMenu=new AuthorizationMenu(mainMenu);
        authorizationMenu.showMenu();
    }
}