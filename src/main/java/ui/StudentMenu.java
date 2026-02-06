package ui;

import domain.Student;
import repository.StudentRepository;
import service.StudentService;
public class StudentMenu extends BaseMenu {
    private StudentService studentService;
    private SearchPerson searchStudent;
    //  private StudentRepository studentRepository;


    public StudentMenu(StudentService studentService, SearchPerson searchStudent/*, StudentRepository studentRepository*/) {
        this.studentService = studentService;
        this.searchStudent = searchStudent;
        // this.studentRepository = studentRepository;
    }

    @Override
    protected void printTitle() {
        System.out.println("--- Студенти ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Скористатися пошуком, щоб знайти студента");
        System.out.println("2. Вивести всіх студентів певного курсу");
        //Сонічка
        System.out.println("3. Додати студента");
        System.out.println("4. Вивести всіх студентів");
        System.out.println("5. Видалити студента");
        System.out.println("6. Редагувати інформацію про студента");
        System.out.println("7. Перевести в іншу групу студента(ще не працює)");
        System.out.println("8. Перевести на наступний курс(ще не працює)");
        System.out.println("0. Повернутися назад");
    }

    @Override
    protected int getMaxOption() {
        return 8;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                searchStudent.showStudentSearchMenu();
                break;
            case 2:
                int course = validation.readInt("Введіть курс : ", 1, 6);
                studentService.showStudentsByCourse(course);
                break;
            case 3:
                showAddStudent();
                break;
            case 4:
                studentService.showAllStudents();
                break;
            case 5:
                deleteStudent();
                break;
            case 6:
                editStudent();
                break;
            case 7:

                break;
            case 8:
                break;
        }
        validation.waitZeroToExit();
    }
private void editStudent() {
        System.out.println("--- Редагування інформації про студента ---");
    String idStudent = validation.readNotEmptyString("Введіть ID студента для редагування: ");
    int newCourse = validation.readInt("Введіть новий курс студента: " , 1, 6);
    int newGroup = validation.readInt("Введіть нову групу студента: ", 1, 6);
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
    boolean success = studentService.editStudent(idStudent, newCourse, newGroup, status);
    if (success) {
        System.out.println(" Інформацію про студента успішно оновлено.");
    } else {
        System.out.println(" Помилка: Студента з таким ID не знайдено.");
    }
}
    private void showAddStudent() {
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
        String dateOfBirth = validation.readNotEmptyString("Введіть дату народження (формат: ДД.ММ.РРРР): ");
        String email = validation.readNotEmptyString("Введіть email: ");
        String phoneNumber = validation.readNotEmptyString("Введіть номер телефону: ");
        try {
            Student newStudent = new Student(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber, course, group, yearOfAdmission, formOfEducation, status);
            studentService.addStudent(newStudent);
            System.out.println("Студента додано");

        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }
//неправильний порядок 1по-батькові, 2імя, 3прізвище
    private void deleteStudent() {
        System.out.println("--- ВИДАЛЕННЯ СТУДЕНТА ---");
        String lastName = validation.readNotEmptyString("Введіть прізвище студента: ");
        String firstName = validation.readNotEmptyString("Введіть ім'я студента: ");
        String middleName = validation.readNotEmptyString("Введіть по-батькові студента: ");
        studentService.deleteStudent(firstName, middleName, lastName);
        System.out.println("Команду виконано (студента видалено, якщо він існував)");
    }

}

