package ui;

import domain.*;
import exceptions.IncorrectDataException;
import lombok.SneakyThrows;
import security.Authentication;
import security.Authorization;
import security.RoleAnotation;
import service.*;
import repository.DepartmentRepository;
import exceptions.NotFoundIDException;
import validation.*;

import java.lang.reflect.Method;
import java.time.LocalDate;

public class StudentMenu extends BaseMenu {
    private StudentService studentService;
    private SearchStudent searchStudent;
    private DepartmentRepository departmentRepository;
    private ValidLocalDate validLocalDate = new ValidLocalDate();
    private ReadPhoneNumber readPhoneNumber = new ReadPhoneNumber();
    private ReadEmail readEmail = new ReadEmail();
    private ValidID validID = new ValidID();
    private DepartmentService departmentService;
    private ValidName validName = new ValidName();
    private ValidPIB validPIB = new ValidPIB();
    private FacultyService facultyService;

    public StudentMenu(StudentService studentService, SearchStudent searchStudent, DepartmentService departmentService, FacultyService facultyService) {
        this.studentService = studentService;
        this.searchStudent = searchStudent;
        this.departmentService = departmentService;
        this.facultyService = facultyService;
    }

    @Override
    protected void printTitle() {
        System.out.println("--- Студенти ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Скористатися пошуком, щоб знайти студента");
        System.out.println("2. Вивести всіх студентів");
        System.out.println("3. Вивести всіх студентів, відсортованих за алфавітом в межах кафедри");
        System.out.println("4. Вивести всіх студентів, відсортованих за алфавітом в межах факультету");
        System.out.println("5. Вивести всіх студентів, відсортованих за курсами");
        System.out.println("6. Вивести всіх студентів, відсортованих за курсами в межах кафедри");
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if (currentUser != null && currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER) {
            System.out.println("7. Додати студента");
            System.out.println("8. Редагувати інформацію про студента");
            System.out.println("9. Видалити студента");
            System.out.println("10. Перевести в іншу групу студента");
            System.out.println("11. Перевести на наступний курс");
            System.out.println("12. Перевести на іншу кафедру");
        }
        System.out.println("0. Повернутися назад");
    }

    @Override
    protected int getMaxOption() {
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if (currentUser != null && currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER) {
            return 12;
        } else {
            return 6;
        }
    }

    @SneakyThrows
    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                searchStudent.showMenu();
                break;
            case 2:
                studentService.search().showAllStudents();
                validation.waitZeroToExit();
                break;
            case 3:
                sortStudentsByAlphabetInDepartment();
                validation.waitZeroToExit();
                break;
            case 4:
                sortStudentsByAlphabetInFaculty();
                validation.waitZeroToExit();
                break;
            case 5:
                studentService.sort().sortStudentsByCourse();
                validation.waitZeroToExit();
                break;
            case 6:
                sortStudentsByCourseInDepartment();
                validation.waitZeroToExit();
                break;
            case 7:
                studentService.sort().sortStudentsByCourse();
                Method addMethod = this.getClass().getDeclaredMethod("addStudent");
                if (Authorization.access(addMethod)) {
                    addStudent();
                }
                validation.waitZeroToExit();
                break;
            case 8:
                studentService.sort().sortStudentsByCourse();
                Method editMethod = this.getClass().getDeclaredMethod("editStudent");
                if (Authorization.access(editMethod)) {
                    editStudent();
                }
                validation.waitZeroToExit();
                break;
            case 9:
                studentService.sort().sortStudentsByCourse();
                Method deleteMethod = this.getClass().getDeclaredMethod("deleteStudent");
                if (Authorization.access(deleteMethod)) {
                    deleteStudent();
                }
                validation.waitZeroToExit();
                break;
            case 10:
                studentService.sort().sortStudentsByCourse();
                Method transferToAnotherGroupMethod = this.getClass().getDeclaredMethod("transferStudentToAnotherGroup");
                if (Authorization.access(transferToAnotherGroupMethod)) {
                    transferStudentToAnotherGroup();
                }
                validation.waitZeroToExit();
                break;
            case 11:
                studentService.sort().sortStudentsByCourse();
                Method transferToNextCourseMethod = this.getClass().getDeclaredMethod("transferStudentToNextCourse");
                if (Authorization.access(transferToNextCourseMethod)) {
                    transferStudentToNextCourse();
                }
                validation.waitZeroToExit();
                break;
            case 12:
                studentService.sort().sortStudentsByCourse();
                Method transferToAnotherDepartmentMethod = this.getClass().getDeclaredMethod("transferStudentToAnotherDepartment");
                if (Authorization.access(transferToAnotherDepartmentMethod)) {
                    transferStudentToAnotherDepartment();
                }
                validation.waitZeroToExit();
                break;
        }
    }

    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void editStudent() {
        System.out.println("--- РЕДАГУВАННЯ ІНФОРМАЦІЇ ПРО СТУДЕНТА ---");
        String id = validID.idMustExist("Введіть ID студента: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return studentService.search().existsById(input);
            }
        });
        String pib = validPIB.validPIB("Введіть нове ПІБ студента: ");
        int course = validation.readInt("Введіть новий курс студента: ", 1, 6);
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

        departmentService.search().showAllDepartments();
        String departmentName = validName.nameMustExist("Введіть назву кафедри: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return departmentService.search().existsByName(input);
            }
        });

        try {
            boolean editStudent = studentService.crud().editStudent(id, pib, course, departmentName, group, status, email, phoneNumber);
            System.out.println("Інформацію про студента успішно оновлено.");
        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void addStudent() {
        System.out.println("--- ДОДАТИ СТУДЕНТА ---");
        String id = validID.idUni("Введіть ID студента: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return studentService.search().existsById(input);
            }
        });
        String pib = validPIB.validPIB("Введіть ПІБ: ");
        int course = validation.readInt("Введіть курс (1-6): ", 1, 6);
        int group = validation.readInt("Введіть групу (1-6): ", 1, 6);
        int yearOfAdmission = validation.readInt("Введіть рік вступу : ", 2015, 2025);

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

        LocalDate dateOfBirth = validLocalDate.readLocalDate("Введіть дату народження: ");
        String email = readEmail.isValidEmail("Введіть email: ");
        String phoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть номер телефону: ");

        departmentService.search().showAllDepartments();
        String departmentName = validName.nameMustExist("Введіть назву кафедри: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return departmentService.search().existsByName(input);
            }
        });

        try {
            Department fakeD = new Department();
            fakeD.setName(departmentName);
            Student newStudent = new Student(id, pib, course, fakeD, group,
                    yearOfAdmission, formOfEducation, status, dateOfBirth, email, phoneNumber);
            studentService.crud().addStudent(newStudent);
            System.out.println("Студента додано");
        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }

    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void deleteStudent() {
        System.out.println("--- ВИДАЛЕННЯ СТУДЕНТА ---");
        String id = validID.idMustExist("Введіть ID студента: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return studentService.search().existsById(input);
            }
        });
        try {
            studentService.crud().deleteStudent(id);
            System.out.println("Студента видалено.");
        } catch (NotFoundIDException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private void sortStudentsByAlphabetInFaculty() {
        facultyService.search().showAllFaculties();
        System.out.println("---СТУДЕНТИ, ВІДСОРТОВАНІ ЗА АЛФАВІТОМ В МЕЖАХ ФАКУЛЬТЕТУ---");
        String facultyName = validName.nameMustExist("Введіть назву факультету для сортування: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return facultyService.search().existsByName(input);
            }
        });
        studentService.sort().sortStudentsByAlphabetInFaculty(facultyName);
    }

    private void sortStudentsByAlphabetInDepartment() {
        departmentService.search().showAllDepartments();
        System.out.println("---СТУДЕНТИ, ВІДСОРТОВАНІ ЗА АЛФАВІТОМ В МЕЖАХ КАФЕДРИ---");
        String departmentName = validName.nameMustExist("Введіть назву кафедри для сортування: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return departmentService.search().existsByName(input);
            }
        });
        studentService.sort().sortStudentsByAlphabetInDepartment(departmentName);
    }

    private void sortStudentsByCourseInDepartment() {
        departmentService.search().showAllDepartments();
        System.out.println("---СТУДЕНТИ, ВІДСОРТОВАНІ ЗА КУРСАМИ В МЕЖАХ КАФЕДРИ---");
        String departmentName = validName.nameMustExist("Введіть назву кафедри для сортування: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return departmentService.search().existsByName(input);
            }
        });
        studentService.sort().sortStudentsByCourseInDepartment(departmentName);
    }

    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void transferStudentToAnotherGroup() {
        System.out.println("--- ПЕРЕВЕДЕННЯ СТУДЕНТА В ІНШУ ГРУПУ ---");
        String studentId = validID.idMustExist("Введіть ID студента для переведення: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return studentService.search().existsById(input);
            }
        });
        int newGroup = validation.readInt("Введіть нову групу для студента: ", 1, 6);
        try {
            studentService.crud().transferToNewGroup(studentId, newGroup);
            System.out.println("Студента успішно переведено до нової групи.");
        } catch (NotFoundIDException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    @RoleAnotation(requireRole={Role.ADMIN, Role.MANAGER})
    private void transferStudentToAnotherDepartment() {
        System.out.println("--- ПЕРЕВЕДЕННЯ СТУДЕНТА НА ІНШУ КАФЕДРУ ---");
        String studentId = validID.idMustExist("Введіть ID студента для переведення: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return studentService.search().existsById(input);
            }
        });
        departmentService.search().showAllDepartments();
        String newDepartmentName = validName.nameMustExist("Введіть назву нової кафедри для студента: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return departmentService.search().existsByName(input);
            }
        });
        try {
            studentService.crud().transferToNewDepartment(studentId, newDepartmentName);
            System.out.println("Студента успішно переведено до нової кафедри.");
        } catch (NotFoundIDException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void transferStudentToNextCourse() {
        System.out.println("--- ПЕРЕВЕДЕННЯ СТУДЕНТА НА НАСТУПНИЙ КУРС ---");
        String studentId = validID.idMustExist("Введіть ID студента для переведення на наступний курс: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return studentService.search().existsById(input);
            }
        });
        int currentCourse = validation.readInt("Введіть новий курс студента: ", 1, 5);
        try {
            studentService.crud().transferToNewCourse(studentId, currentCourse);
            System.out.println("Студента успішно переведено на інший курс.");
        } catch (NotFoundIDException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}