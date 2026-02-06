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
        System.out.println("6. Перевести в іншу групу студента");
        System.out.println("7. Перевести на наступний курс");
        System.out.println("0. Повернутися назад");
    }

    @Override
    protected int getMaxOption() {
        return 7;
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

                break;
            case 7:

                break;
        }
        validation.waitZeroToExit();
    }

    private void showAddStudent() {
        System.out.println("--- ДОДАТИ СТУДЕНТА ---");
        String id = validation.readString("Введіть ID студента: ");
        String lastName = validation.readString("Введіть Прізвище: ");
        String firstName = validation.readString("Введіть Ім'я: ");
        String middleName = validation.readString("Введіть По-батькові: ");
        int course = validation.readInt("Введіть курс (1-6): ", 1, 6);
        int group = validation.readInt("Введіть групу (1-6): ", 1, 6);
        int yearOfAdmission = validation.readInt("Введіть рік вступу (2000-2024): ", 2000, 2024);
        //НЕМАЄ ЗАХИСТУ ВІД ДУРНЯ!!!!!!!!"№;%:?:%;№""№;%
        String formOfEducation = validation.readString("Введіть форму навчання (денна/заочна): ");
        String status = validation.readString("Введіть статус (навчається/академвідпустка/відрахований): ");
        String dateOfBirth = "-";
        String email = "-";
        String phoneNumber = "-";
        try {
            Student newStudent = new Student(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber, course, group, yearOfAdmission, formOfEducation, status);
            studentService.addStudent(newStudent);
            System.out.println("Студента додано");

        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        System.out.println("--- ВИДАЛЕННЯ СТУДЕНТА ---");
        String lastName = validation.readString("Введіть прізвище студента: ");
        String firstName = validation.readString("Введіть ім'я студента: ");
        String middleName = validation.readString("Введіть по-батькові студента: ");
        studentService.deleteStudent(firstName, middleName, lastName);
        System.out.println("Команду виконано");
    }

}

