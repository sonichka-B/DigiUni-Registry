package ui;


import domain.*;
import repository.DepartmentRepository;
import repository.FacultyRepository;
import repository.TeacherRepository;
import repository.storage.JsonStorageManager;
import repository.storage.UniversityStorage;
import service.*;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        StudentService studentService = new StudentService();
        TeacherService teacherService = new TeacherService();
        FacultyService facultyService = new FacultyService();
        DepartmentService departmentService = new DepartmentService();

        TeacherRepository sharedTeacherRepo = teacherService.crud().getRepository();
        FacultyRepository sharedFacultyRepo = facultyService.crud().getRepository();
        DepartmentRepository sharedDeptRepo = departmentService.crud().getRepository();

        studentService.setSharedDepartmentRepository(sharedDeptRepo);
        teacherService.setSharedDepartmentRepository(sharedDeptRepo);
        departmentService.setSharedFacAndTeachRep(sharedFacultyRepo, sharedTeacherRepo);
        facultyService.setSharedTeacherRepository(sharedTeacherRepo);
        UsersService usersService = new UsersService();

        usersService.addUser(new Users("admin", "admin123", Role.ADMIN));
        usersService.addUser(new Users("manDasha", "manager123", Role.MANAGER));

        JsonStorageManager storageManager = new JsonStorageManager();
        UniversityStorage data =  storageManager.loadData();
        if(data != null){
            facultyService.crud().getRepository().addAll(data.getFaculties());
            departmentService.crud().getRepository().addAll(data.getDepartments());
            teacherService.crud().getRepository().addAll(data.getTeachers());
            studentService.crud().getRepository().addAll(data.getStudents());
        }else{

            facultyService.crud().addFaculty(new Faculty("101", "Інженерія", "ІНЖ", "Alice Brown", "+380441112233"));
            facultyService.crud().addFaculty(new Faculty("102", "Наука", "НАУК", "Bob Johnson", "+380449998877"));

            Department compSci = new Department("201", "Комп'ютерні науки", "101", "Alice Brown", "Корпус 1");
            Department biology = new Department("202", "Біологія", "102", "Bob Johnson", "Корпус 2");
            departmentService.crud().addDepartment(compSci);
            departmentService.crud().addDepartment(biology);

            teacherService.crud().addTeacher(new Teacher("1", "Alice B. Brown", compSci,
                    "доцент", "кандидат наук", "доцент", "2010-09-01", "1.0",
                    LocalDate.of(1980, 3, 10), "alice@ukma.edu.ua", "+380951112233"));
            teacherService.crud().addTeacher(new Teacher("2", "Bob J. Johnson", biology,
                    "професор", "доктор наук", "професор", "2005-02-15", "1.0",
                    LocalDate.of(1975, 7, 20), "bob@ukma.edu.ua", "+380952223344"));

            studentService.crud().addStudent(new Student("1", "Марія Іванівна Козаченко", 2,
                    "Біологія", 1, 2022, "бюджет", "навчається",
                    LocalDate.of(2004, 2, 27), "mariya@ukr.net", "+380991234567"));
            studentService.crud().addStudent(new Student("2", "Іван Сергійович Петренко", 3,
                    "Комп'ютерні науки", 2, 2021, "контракт", "навчається",
                    LocalDate.of(2003, 5, 15), "ivan@ukr.net", "+380997654321"));

            UniversityStorage storage = new UniversityStorage(
                    studentService.crud().getRepository().findAll(),
                teacherService.crud().getRepository().findAll(),
                departmentService.crud().getRepository().findAll(),
                facultyService.crud().getRepository().findAll());
            storageManager.saveData(storage);
        }



        SearchTeacher searchTeacher = new SearchTeacher(teacherService);
        SearchStudent searchStudent = new SearchStudent(studentService);

        FacultyMenu facultyMenu = new FacultyMenu(facultyService);

        DepartmentMenu departmentMenu = new DepartmentMenu(
                departmentService,
                facultyService,
                teacherService.crud().getRepository(),
                facultyService.crud().getRepository());

        StudentMenu studentMenu = new StudentMenu(
                studentService,
                searchStudent);

        TeacherMenu teacherMenu = new TeacherMenu(
                teacherService,
                searchTeacher);

        AdminMenu adminMenu=new AdminMenu(usersService);
        MainMenu mainMenu = new MainMenu(studentMenu, teacherMenu, departmentMenu, facultyMenu, adminMenu);
        AuthorizationMenu authorizationMenu=new AuthorizationMenu(mainMenu);
        authorizationMenu.showMenu();

//        save final data
        UniversityStorage finalStorage = new UniversityStorage(
                studentService.crud().getRepository().findAll(),
                teacherService.crud().getRepository().findAll(),
                departmentService.crud().getRepository().findAll(),
                facultyService.crud().getRepository().findAll()
        );
        storageManager.saveData(finalStorage);
    }
}