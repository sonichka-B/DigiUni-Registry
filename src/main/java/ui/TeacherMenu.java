package ui;

import domain.Department;
import domain.Teacher;
import service.TeacherService;
import service.TeacherCRUDService;
import service.TeacherSortingService;
import service.TeacherSearchService;

public class TeacherMenu extends BaseMenu {
    private TeacherService teacherService;
    private SearchTeacher searchTeacher;
    private TeacherSortingService teacherSortingService;
    private TeacherCRUDService teacherCRUDService;
    private TeacherSearchService teacherSearchService;


    public TeacherMenu(TeacherService teacherService, SearchTeacher searchTeacher, TeacherSortingService teacherSortingService, TeacherCRUDService teacherCRUDService, TeacherSearchService teacherSearchService) {
        this.teacherService = teacherService;
        this.searchTeacher=searchTeacher;
        this.teacherSortingService=teacherSortingService;
        this.teacherCRUDService=teacherCRUDService;
        this.teacherSearchService=teacherSearchService;
    }
    @Override
    protected void printTitle() {
        System.out.println("--- Викладачі ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Додати викладача");
        System.out.println("2. Редагувати інформацію про викладача");
        System.out.println("3. Видалити викладача");
        System.out.println("4. Вивести всіх викладачів");
        System.out.println("5. Скористатися пошуком, щоб знайти викладача за ПІБ");
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
                addTeacher();
                break;
            case 2:
                editTeacher();
                break;
            case 3:
                deleteTeacher();
                break;
            case 4:
                teacherSearchService.showAllTeachers();
                break;
            case 5:
                //Пошук викладача за ПІБ
                searchTeacher.showMenu();
                break;

        }
        validation.waitZeroToExit();
    }
    private void editTeacher() {
        System.out.println("--- Редагування інформації про викладача ---");
        String id = validation.readNotEmptyString("Введіть ID викладача для редагування: ");
        System.out.println("Оберіть нову посаду:");
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
        System.out.println("Оберіть новий науковий ступінь:");
        System.out.println("1-кандидат наук");
        System.out.println("2-доктор наук");
        System.out.println("3-доктор філософії");
        int degreeChoice = validation.readInt("Ваш вибір: ", 1, 3);
        String academicDegree = "";
        if (degreeChoice == 1) {
            academicDegree = "кандидат наук";
        } else if (degreeChoice == 2) {
            academicDegree = "доктор наук";
        } else if (degreeChoice == 3) {
            academicDegree = "доктор філософії";
        }
        System.out.println("Оберіть нове звання:");
        System.out.println("1-доцент");
        System.out.println("2-професор");
        System.out.println("3-старший дослідник");
        int titleChoice = validation.readInt("Ваш вибір: ", 1, 3);
        String academicTitle = "";
        if (titleChoice == 1) {
            academicTitle = "доцент";
        } else if (titleChoice == 2) {
            academicTitle = "професор";
        } else if (titleChoice == 3) {
            academicTitle = "старший дослідник";
        }
        String departmentName = validation.readNotEmptyString("Введіть нову кафедру викладача: ");
        Department department = teacherCRUDService.findDepartmentByName(departmentName);
        while (department == null) {
            System.out.println(" Помилка: Кафедру з такою назвою не знайдено!");
            departmentName = validation.readNotEmptyString("Введіть нову кафедру викладача: ");
            department = teacherCRUDService.findDepartmentByName(departmentName);
        }
        //===================Запитати чи можна дописати пошук за назвою
        boolean success = teacherCRUDService.editTeacher(id, position, academicDegree, academicTitle, department);
        if (success) {
            System.out.println(" Інформацію про студента успішно оновлено.");
        } else {
            System.out.println(" Помилка: Студента з таким ID не знайдено.");
        }
    }
    private void addTeacher() {
        System.out.println("--- ДОДАТИ ВИКЛАДАЧА ---");
        String id = validation.readNotEmptyString("ID викладача: ");
        String lastName = validation.readNotEmptyString("Прізвище: ");
        String firstName = validation.readNotEmptyString("Ім'я: ");
        String middleName = validation.readNotEmptyString("По-батькові: ");
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
        System.out.println("Оберіть науковий ступінь:");
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
        String dateOfEmployment = validation.readNotEmptyString("Дата прийняття на роботу: ");
        //Сонічка я не дуже розумію що таке rate тому залишила як є
        String rate = validation.readNotEmptyString("Ставка: ");
        try {
            Teacher newTeacher = new Teacher(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber, position, degree, title, dateOfEmployment, rate);
            teacherCRUDService.addTeacher(newTeacher);
            System.out.println("Викладача додано");

        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    private void deleteTeacher() {
        System.out.println("--- ВИДАЛЕННЯ ВИКЛАДАЧА ---");
        String id= validation.readNotEmptyString("Введіть ID викладача: ");
            teacherCRUDService.deleteTeacher(id);
        System.out.println("Команду виконано (викладача видалено, якщо він існував)");
    }
    //
    private void sortTeachersByAlphabetInFaculty(){
        String facultyName = validation.readNotEmptyString("Введіть назву факультету для сортування: ");
        teacherSortingService.sortTeachersByAlphabetInFaculty(facultyName);
    }
}


