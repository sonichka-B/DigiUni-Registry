package ui;


import domain.*;
import repository.DepartmentRepository;
import repository.FacultyRepository;
import repository.TeacherRepository;
import repository.storage.Data;
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
            Data entity = new Data(facultyService, departmentService, teacherService, studentService);
            entity.initializeData();
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