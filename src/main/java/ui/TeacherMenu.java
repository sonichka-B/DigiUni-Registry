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
               //редагувати інформацію про викладача
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
        System.out.println("Оберіть посаду:");
        System.out.println("1-лаборант");
        System.out.println("2-асистент");
        System.out.println("3-викладач");
        System.out.println("4-старший викладач");
        System.out.println("5-доцент");
        System.out.println("6-професор");
        System.out.println("7-декан");
        int positionChoice = validation.readInt("Ваш вибір: ", 1, 7);
        String position = "";
        if (positionChoice == 1) {
            position = "лаборант";
        } else if (positionChoice == 2) {
            position = "асистент";
        } else if (positionChoice == 3) {
            position = "викладач";
        } else if (positionChoice == 4) {
            position = "старший викладач";
        } else if (positionChoice == 5) {
            position = "доцент";
        } else if (positionChoice == 6) {
            position = "професор";
        } else if (positionChoice == 7) {
            position = "декан";
        }
        System.out.println("Оберіть посаду:");
        System.out.println("1-кандидат наук");
        System.out.println("2-доктор наук");
        System.out.println("3-доктор філософії");
        int degreeChoice = validation.readInt("Ваш вибір: ", 1, 3);
        String degree = "";
        if (degreeChoice == 1) {
            degree = "кандидат наук";
        } else if (degreeChoice == 2) {
            degree = "доктор наук";
        } else if (degreeChoice == 3) {
            degree = "доктор філософії";
        }
        System.out.println("Оберіть звання:");
        System.out.println("1-доцент");
        System.out.println("2-професор");
        System.out.println("3-старший дослідник");
        int titleChoice = validation.readInt("Ваш вибір: ", 1, 3);
        String title = "";
        if (titleChoice == 1) {
            title = "доцент";
        } else if (titleChoice == 2) {
            title = "професор";
        } else if (titleChoice == 3) {
            title = "старший дослідник";
        }
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
        System.out.println("Команду виконано (викладача видалено, якщо він існував)");
    }
}


