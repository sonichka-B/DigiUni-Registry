package ui;

import domain.Department;
import domain.Faculty;
import domain.Student;
import domain.Teacher;
import service.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        // ==========================================
        // СПІЛЬНІ СЕРВІСИ ПОШУКУ ТА СОРТУВАННЯ
        // ==========================================
        StudentSearchService studentSearchService = new StudentSearchService();
        TeacherSearchService teacherSearchService = new TeacherSearchService();
        FacultySearchService facultySearchService = new FacultySearchService();
        DepartmentSearchService departmentSearchService = new DepartmentSearchService();
        TeacherSortingService teacherSortingService = new TeacherSortingService();
        StudentSortingService studentSortingService = new StudentSortingService();


        // ==========================================
        // БЛОК СТУДЕНТІВ
        // ==========================================
        StudentService studentService = new StudentService();
        StudentCRUDService studentCRUDService = new StudentCRUDService();
        studentSortingService.setStudentRepository(studentCRUDService.getRepository());

        studentCRUDService.getRepository().add(new Student("1", "Марія", "Іванівна", "Козаченко", 2,
                "Біологія", 1, 2022, "бюджет", "навчається", LocalDate.of(2004, 2, 27), "mariya@ukr.net", "+380991234567"));
        studentCRUDService.getRepository().add(new Student("2", "Іван", "Сергійович", "Петренко", 3,
                "Комп'ютерні науки", 2, 2021, "контракт", "навчається", LocalDate.of(2003, 5, 15), "ivan@ukr.net", "+380997654321"));

        // ==========================================
        // БЛОК ВИКЛАДАЧІВ ТА КАФЕДР
        // ==========================================
        TeacherService teacherService = new TeacherService();
        TeacherCRUDService teacherCRUDService = new TeacherCRUDService();
        teacherSortingService.setTeacherRepository(teacherCRUDService.getRepository());
        // Створюємо кафедри заздалегідь, щоб передати їх викладачам
        Department compSci = new Department("201", "Комп'ютерні науки", "101", "Alice Brown", "Корпус 1");
        Department biology = new Department("202", "Біологія", "102", "Bob Johnson", "Корпус 2");

        teacherCRUDService.getRepository().add(new Teacher("1", "Alice", "B.", "Brown", compSci,
                "доцент", "кандидат наук", "доцент", "2010-09-01", "1.0", LocalDate.of(1980, 3, 10), "alice@ukma.edu.ua", "+380951112233"));
        teacherCRUDService.getRepository().add(new Teacher("2", "Bob", "J.", "Johnson", biology,
                "професор", "доктор наук", "професор", "2005-02-15", "1.0", LocalDate.of(1975, 7, 20), "bob@ukma.edu.ua", "+380952223344"));

        // ==========================================
        // БЛОК ФАКУЛЬТЕТІВ
        // ==========================================
        FacultyService facultyService = new FacultyService();
        FacultyCRUDService facultyCRUDService = new FacultyCRUDService();

        facultyCRUDService.getRepository().add(new Faculty("101", "Інженерія", "ІНЖ", "Alice Brown", "+380441112233"));
        facultyCRUDService.getRepository().add(new Faculty("102", "Наука", "НАУК", "Bob Johnson", "+380449998877"));

        // ==========================================
        // БЛОК ДОДАВАННЯ КАФЕДР В БАЗУ
        // ==========================================
        DepartmentService departmentService = new DepartmentService();
        DepartmentCRUDService departmentCRUDService = new DepartmentCRUDService();

        // Кладемо наші змінні кафедр у сховище
        departmentCRUDService.getRepository().add(compSci);
        departmentCRUDService.getRepository().add(biology);

        // ==========================================
        // БЛОК МЕНЮ (ЗБІРКА ТА ЗАПУСК)
        // ==========================================
        SearchTeacher searchTeacher = new SearchTeacher(teacherSearchService);
        SearchStudent searchStudent = new SearchStudent(studentSearchService);

        FacultyMenu facultyMenu = new FacultyMenu(facultyCRUDService, facultySearchService);

        // Меню кафедр отримує бази викладачів і факультетів через наші getRepository()
        DepartmentMenu departmentMenu = new DepartmentMenu(departmentCRUDService, departmentSearchService, facultySearchService,
                teacherCRUDService.getRepository(), facultyCRUDService.getRepository());

        StudentMenu studentMenu = new StudentMenu(studentService, searchStudent, studentCRUDService, studentSortingService, studentSearchService);
        TeacherMenu teacherMenu = new TeacherMenu(teacherService, searchTeacher, teacherSortingService, teacherCRUDService, teacherSearchService);

        MainMenu mainMenu = new MainMenu(studentMenu, teacherMenu, departmentMenu, facultyMenu);

        System.out.println("=== Дані успішно завантажено! ===");
        mainMenu.showMenu();
    }
}