package ui;

import domain.Student;
import service.DepartmentService;
import service.FacultyService;
import service.StudentService;
import service.TeacherService;

public class Main {
    //доробити
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        studentService.addStudent(new Student("1", "John", "Doe", "M", "2000-01-01",
                "email1", "11", 1, 1, 2018, "бюджет", "навчається"));
        studentService.addStudent(new Student("2", "Jane", "Smith", "A", "1999-05-15",
                "email2", "22", 2, 2, 2017, "контракт", "навчається"));

        TeacherService teacherService = new TeacherService();
        teacherService.addTeacher(new domain.Teacher("1", "Alice", "Brown", "C", "1980-03-10",
                "email3", "33", "доцент", "кандидат наук", "доцент", "2010-09-01", "1.0"));
        teacherService.addTeacher(new domain.Teacher("2", "Bob", "Johnson", "D", "1975-07-20",
                "email4", "44", "професор", "доктор наук", "професор", "2005-02-15", "1.0"));


        FacultyService facultyService = new FacultyService();
        facultyService.addFaculty(new domain.Faculty("101","Engineering", "ENG", "Dr. White", "123-456-7890"));
        facultyService.addFaculty(new domain.Faculty("102","Science", "SCI", "Dr. Green", "098-765-4321"));

        DepartmentService departmentService = new DepartmentService();
        departmentService.addDepartment(new domain.Department("201", "Computer Science", facultyService.findFacultyByName ("Engineering"),
                teacherService.findTeacherByFullName("Alice", "Brown", "C"), "Building A"));
        departmentService.addDepartment(new domain.Department("202", "Biology", facultyService.findFacultyByName("Science"),
                teacherService.findTeacherByFullName("Bob", "Johnson", "D"), "Building B"));

        StudentMenu studentMenu = new StudentMenu();
        TeacherMenu teacherMenu = new TeacherMenu();
        MainMenu mainMenu = new MainMenu(studentMenu, teacherMenu);
        mainMenu.showMenu();
    }
}
