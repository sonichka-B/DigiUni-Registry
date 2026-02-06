package ui;

import domain.Student;
import domain.Teacher;
import service.StudentService;
import service.TeacherService;

import java.awt.*;

public class TeacherMenu extends BaseMenu {
    private TeacherService teacherService;
    private SearchPerson searchTeacher;


    public TeacherMenu(TeacherService teacherService, SearchPerson searchTeacher) {
        this.teacherService = teacherService;
        this.searchTeacher=searchTeacher;
    }
    @Override
    protected void printTitle() {
        System.out.println("--- Викладачі ---");
    }

    @Override
    protected void printOptions() {

        System.out.println("1. Скористатися пошуком, щоб знайти викладача за ПІБ");
        //Сонічка
        System.out.println("2. Додати викладача");
        System.out.println("3. Редагувати інформацію про викладача");
        System.out.println("4. Вивести всіх викладачів");
        System.out.println("5. Видалити викладача");
        System.out.println("0. Повернутися назад");
    }
//доробити
    @Override
    protected int getMaxOption() {
        return 5;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                //Пошук викладача за ПІБ
                searchTeacher.searchTeacherByName();
                break;
            case 2:
                addTeacher();
                break;
            case 3:
               //
                break;
            case 4:
                teacherService.showAllTeachers();
                break;
            case 5:
                deleteTeacher();
                break;
        }
        validation.waitZeroToExit();
    }
    private void addTeacher() {
        System.out.println("--- ДОДАТИ СТУДЕНТА ---");
        String id = validation.readString("ID викладача: ");
        String lastName = validation.readString("Прізвище: ");
        String firstName = validation.readString("Ім'я: ");
        String middleName = validation.readString("По-батькові: ");
        String dateOfBirth = "-";
        String email = "-";
        String phoneNumber = "-";
        System.out.println("Варіанти посад: лаборант, асистент, викладач, старший викладач, доцент, професор, декан");
        String position = validation.readString("Введіть посаду: ");

        System.out.println("Варіанти ступенів: кандидат наук, доктор наук, доктор філософії");
        String degree = validation.readString("Введіть науковий ступінь: ");

        System.out.println("Варіанти звань: доцент, професор, старший дослідник");
        String title = validation.readString("Введіть вчене звання: ");
        String dateOfEmployment = validation.readString("Дата прийняття на роботу: ");
        //Сонічка я не дуже розумію що таке rate тому залишила як є
        String rate = validation.readString("Ставка: ");
        try {
            Teacher newTeacher = new Teacher(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber, position, degree, title, dateOfEmployment, rate);
            teacherService.addTeacher(newTeacher);
            System.out.println("Викладача додано");

        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    private void deleteTeacher() {
        System.out.println("--- ВИДАЛЕННЯ ВИКЛАДАЧА ---");
        String lastName = validation.readString("Введіть прізвище викладача: ");
        String firstName = validation.readString("Введіть ім'я викладача: ");
        String middleName = validation.readString("Введіть по-батькові викладача: ");
            teacherService.deleteTeacher(firstName, middleName, lastName);
        System.out.println("Команду виконано");
    }
}


