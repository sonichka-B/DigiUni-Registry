package ui;

import domain.Student;
import service.*;
import repository.DepartmentRepository;
import exceptions.NotFoundIDException;
import validation.*;

import java.time.LocalDate;


public class StudentMenu extends BaseMenu {
    private StudentService studentService;
    private SearchStudent searchStudent;
    private StudentCRUDService studentCRUDService;
    private StudentSortingService studentSortingService;
    private StudentSearchService studentSearchService;
    private DepartmentRepository departmentRepository;
    private ValidLocalDate validLocalDate = new ValidLocalDate();
    private ReadPhoneNumber readPhoneNumber = new ReadPhoneNumber();
    private ReadEmail readEmail = new ReadEmail();



    public StudentMenu(StudentService studentService, SearchStudent searchStudent, StudentCRUDService studentCRUDService, StudentSortingService studentSortingService, StudentSearchService studentSearchService) {
        this.studentService = studentService;
        this.searchStudent = searchStudent;
        this.studentCRUDService=studentCRUDService;
        this.studentSortingService=studentSortingService;
        this.studentSearchService=studentSearchService;
       // this.departmentRepository=departmentRepository;
    }

    @Override
    protected void printTitle() {
        System.out.println("--- Студенти ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Додати студента");
        System.out.println("2. Редагувати інформацію про студента");
        System.out.println("3. Видалити студента");
        System.out.println("4. Вивести всіх студентів, відсортованих за алфавітом в межах кафедри");
        System.out.println("5. Вивести всіх студентів, відсортованих за алфавітом в межах факультету");
        System.out.println("6. Вивести всіх студентів, відсортованих за курсами");
        System.out.println("7. Вивести всіх студентів, відсортованих за курсом в межах кафедри");
        System.out.println("8. Перевести в іншу групу студента");
        System.out.println("9. Перевести на наступний курс");
        System.out.println("10.Скористатися пошуком, щоб знайти студента");
        System.out.println("0. Повернутися назад");
    }

    @Override
    protected int getMaxOption() {
        return 10;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addStudent();
                validation.waitZeroToExit();
                break;
            case 2:
                editStudent();
                validation.waitZeroToExit();
                break;

            case 3:
                deleteStudent();
                validation.waitZeroToExit();
                break;
            case 4:
                sortStudentsByAlphabetInDepartment();
                validation.waitZeroToExit();
                break;
            case 5:
                sortStudentsByAlphabetInFaculty();
                validation.waitZeroToExit();
                break;

            case 6:
                studentSortingService.sortStudentsByCourse();
                validation.waitZeroToExit();
                break;
            case 7:
                sortStudentsByCourseInDepartment();
                validation.waitZeroToExit();
                break;
            case 8:
                transferStudentToAnotherGroup();
                validation.waitZeroToExit();
                break;
            case 9:
                transferStudentToNextCourse();
                validation.waitZeroToExit();
                break;
            case 10:
                searchStudent.showMenu();
                break;
        }
    }
private void editStudent() {
        System.out.println("--- Редагування інформації про студента ---");
    String id= validation.readNotEmptyString("Введіть ID студента для редагування: ");
    String pib = validation.readNotEmptyString("Введіть нове ПІБ студента: ");
//    String middleName = validation.readNotEmptyString("Введіть нове по-батькові студента: ");
//    String lastName = validation.readNotEmptyString("Введіть нове прізвище студента: ");
    int course = validation.readInt("Введіть новий курс студента: " , 1, 6);
    int group = validation.readInt("Введіть нову групу студента: ", 1, 6);
    System.out.println("Оберіть новий статус студента:");
    System.out.println("1-навчається");
    System.out.println("2-академвідпустка");
    System.out.println("3-відрахований");
    int statusChoice = validation.readInt("Ваш вибір: ", 1, 3);
    String status = "";
    if (statusChoice == 1) {
        status = "навчається";
    } else if (statusChoice == 2) {
        status = "академвідпустка";
    } else {
        status = "відрахований";
    }
    String phoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть новий номер телефону студента: ");
    String email = readEmail.isValidEmail("Введіть новий email студента: ");
    String departmentName = validation.readNotEmptyString("Введіть назву кафедри: ");
    try {
        //МОЖНА ЗМІНИТИ І ШУКАТИ НЕ ЗА ІД
        boolean editStudent=studentCRUDService.editStudent(id, pib, course, departmentName,group, status, email, phoneNumber);
        System.out.println("Інформацію про студента успішно оновлено.");

    } catch (Exception e) {
        System.out.println("Помилка при створенні: " + e.getMessage());
    }
}
    private void addStudent() {
        System.out.println("--- ДОДАТИ СТУДЕНТА ---");
        String id = validation.readNotEmptyString("Введіть ID студента: ");
        String pib = validation.readNotEmptyString("Введіть ПІБ: ");
//        String firstName = validation.readNotEmptyString("Введіть Ім'я: ");
//        String middleName = validation.readNotEmptyString("Введіть По-батькові: ");
        int course = validation.readInt("Введіть курс (1-6): ", 1, 6);
        int group = validation.readInt("Введіть групу (1-6): ", 1, 6);
        int yearOfAdmission = validation.readInt("Введіть рік вступу : ", 2015, 2025);
        //є захист від дурня
        System.out.println("Оберіть форму навчання:");
        System.out.println("1-бюджет");
        System.out.println("2-контракт");
        int formChoice = validation.readInt("Ваш вибір: ", 1, 2);
        String formOfEducation = "";
        if (formChoice == 1) {
            formOfEducation = "бюджет";
        } else {
            formOfEducation = "контракт";
        }
        System.out.println("Оберіть статус студента:");
        System.out.println("1-навчається");
        System.out.println("2-академвідпустка");
        System.out.println("3-відрахований");
        int statusChoice = validation.readInt("Ваш вибір: ", 1, 3);
        String status = "";
        if (statusChoice == 1) {
            status = "навчається";
        } else if (statusChoice == 2) {
            status = "академвідпустка";
        } else {
            status = "відрахований";
        }
        LocalDate dateOfBirth = validLocalDate.readLocalDate("Введіть дату народження (формат: ДД.ММ.РРРР): ");
        String email = readEmail.isValidEmail("Введіть email: ");
        String phoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть номер телефону: ");
        String departmentName = validation.readNotEmptyString("Введіть назву кафедри: ");
        try {
            Student newStudent = new Student(id, pib, course, departmentName, group,
             yearOfAdmission, formOfEducation, status, dateOfBirth,email,phoneNumber);
            studentCRUDService.addStudent(newStudent);
            System.out.println("Студента додано");

        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        System.out.println("--- ВИДАЛЕННЯ СТУДЕНТА ---");
        String id= validation.readNotEmptyString("Введіть ID студента для видалення: ");
        try{
        studentCRUDService.deleteStudent(id);
        System.out.println("Команду виконано (студента видалено, якщо він існував)");
        } catch (NotFoundIDException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    private void sortStudentsByAlphabetInFaculty(){
        String facultyName = validation.readNotEmptyString("Введіть назву факультету для сортування: ");
        studentSortingService.sortStudentsByAlphabetInFaculty(facultyName, null);
    }
    private void sortStudentsByAlphabetInDepartment(){
        String departmentName = validation.readNotEmptyString("Введіть назву кафедри для сортування: ");
        studentSortingService.sortStudentsByAlphabetInDepartment(departmentName);
    }

    private void sortStudentsByCourseInDepartment(){
        String departmentName = validation.readNotEmptyString("Введіть назву кафедри для сортування: ");
        studentSortingService.sortStudentsByCourseInDepartment(departmentName);
    }
    private void transferStudentToAnotherGroup() {
        String studentId = validation.readNotEmptyString("Введіть ID студента для переведення: ");
        int newGroup = validation.readInt("Введіть нову групу для студента: ", 1, 6);
        try {
            studentCRUDService.transferToNewGroup(studentId, newGroup);
            System.out.println("Студента успішно переведено до нової групи.");
        } catch (NotFoundIDException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    private void transferStudentToNextCourse() {
        String studentId = validation.readNotEmptyString("Введіть ID студента для переведення на наступний курс: ");
        int currentCourse = validation.readInt("Введіть поточний курс студента: ", 1, 5);
        int nextCourse = currentCourse + 1;
        try {
            studentCRUDService.transferToNewCourse(studentId, nextCourse);
            System.out.println("Студента успішно переведено на наступний курс.");
        } catch (NotFoundIDException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}

