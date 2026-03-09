package ui;

import domain.Department;
import domain.Student;
import service.StudentService;
import service.StudentCRUDService;
import service.StudentSortingService;
import service.StudentSearchService;
import repository.DepartmentRepository;
import exceptions.NotFoundException;

import java.time.LocalDate;


public class StudentMenu extends BaseMenu {
    private StudentService studentService;
    private SearchStudent searchStudent;
    private StudentCRUDService studentCRUDService;
    private StudentSortingService studentSortingService;
    private StudentSearchService studentSearchService;
    private DepartmentRepository departmentRepository;
    private ValidLocalDate validLocalDate = new ValidLocalDate();



    public StudentMenu(StudentService studentService, SearchStudent searchStudent, StudentCRUDService studentCRUDService, StudentSortingService studentSortingService, StudentSearchService studentSearchService, DepartmentRepository departmentRepository) {
        this.studentService = studentService;
        this.searchStudent = searchStudent;
        this.studentCRUDService=studentCRUDService;
        this.studentSortingService=studentSortingService;
        this.studentSearchService=studentSearchService;
        this.departmentRepository=departmentRepository;
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
        System.out.println("8. Перевести в іншу групу студента(ще не працює)");
        System.out.println("9. Перевести на наступний курс(ще не працює)");
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
                break;
            case 2:
                editStudent();
                break;

            case 3:
                deleteStudent();
                break;
            case 4:

                break;
            case 5:

                break;

            case 6:
                studentSortingService.sortStudentsByCourse();
                break;
            case 7:

                break;
            case 8:

                break;
            case 9:

                break;
            case 10:
                searchStudent.showStudentSearchMenu();
                break;
        }
        validation.waitZeroToExit();
    }
private void editStudent() {
        System.out.println("--- Редагування інформації про студента ---");
    String id= validation.readNotEmptyString("Введіть ID студента для редагування: ");
    int сourse = validation.readInt("Введіть новий курс студента: " , 1, 6);
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

    String departmentId = validation.readNotEmptyString("Введіть ID кафедри: ");
    try {
        domain.Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Кафедра з ID ", departmentId));

        boolean editStudent=studentCRUDService.editStudent(id, сourse, group, status, department);
        System.out.println("Інформацію про студента успішно оновлено.");

    } catch (Exception e) {
        System.out.println("Помилка при створенні: " + e.getMessage());
    }
}
    private void addStudent() {
        System.out.println("--- ДОДАТИ СТУДЕНТА ---");
        String id = validation.readNotEmptyString("Введіть ID студента: ");
        String lastName = validation.readNotEmptyString("Введіть Прізвище: ");
        String firstName = validation.readNotEmptyString("Введіть Ім'я: ");
        String middleName = validation.readNotEmptyString("Введіть По-батькові: ");
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
        String email = validation.readNotEmptyString("Введіть email: ");
        String phoneNumber = validation.readNotEmptyString("Введіть номер телефону: ");
        String departmentId = validation.readNotEmptyString("Введіть ID кафедри: ");
        try {
            domain.Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new NotFoundException("Кафедра з ID ", departmentId));

            Student newStudent = new Student(id, firstName, middleName, lastName, course, departmentId, group,
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
        studentCRUDService.deleteStudent(id);
        System.out.println("Команду виконано (студента видалено, якщо він існував)");
    }
    private void sortStudentsByAlphabetInFaculty(){
        String facultyName = validation.readNotEmptyString("Введіть назву факультету для сортування: ");

    }
    private void sortStudentsByAlphabetInDepartment(){
        String departmentName = validation.readNotEmptyString("Введіть назву кафедри для сортування: ");
    }

    private void sortStudentsByCourseInDepartment(){
        String departmentName = validation.readNotEmptyString("Введіть назву кафедри для сортування: ");
        int course=validation.readInt("Введіть курс: ", 1, 6);
    }
}

