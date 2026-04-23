package ui;

import domain.Department;
import domain.Role;
import domain.Teacher;
import domain.Users;
import lombok.SneakyThrows;
import security.Authentication;
import security.Authorization;
import security.RoleAnotation;
import service.*;
import validation.*;

import java.lang.reflect.Method;
import java.time.LocalDate;

public class TeacherMenu extends BaseMenu {
    private TeacherService teacherService;
    private SearchTeacher searchTeacher;
//    private TeacherSortingService teacherSortingService;
//    private TeacherCRUDService teacherCRUDService;
//    private TeacherSearchService teacherSearchService;
    private ReadPhoneNumber readPhoneNumber = new ReadPhoneNumber();
    private ReadEmail readEmail = new ReadEmail();
    private ValidLocalDate validLocalDate = new ValidLocalDate();
    private DepartmentService departmentService;
    private ValidID validID = new ValidID();
    private ValidName validName = new ValidName();

    public TeacherMenu(TeacherService teacherService, SearchTeacher searchTeacher, DepartmentService departmentService) {
        this.teacherService = teacherService;
        this.searchTeacher=searchTeacher;
        this.departmentService = departmentService;
    }
    @Override
    protected void printTitle() {
        System.out.println("--- Викладачі ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Скористатися пошуком, щоб знайти викладача за ПІБ");
        System.out.println("2. Вивести всіх викладачів");
        System.out.println("3. Вивести всіх викладачів, відсортованих за алфавітом в межах факультету");
        System.out.println("4. Вивести всіх викладачів, відсортованих за алфавітом в межах кафедри");
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if(currentUser!=null&&currentUser.getRole()==Role.ADMIN||currentUser.getRole()==Role.MANAGER) {
            System.out.println("5. Додати викладача");
            System.out.println("6. Редагувати інформацію про викладача");
            System.out.println("7. Видалити викладача");
        }
        System.out.println("0. Повернутися назад");
    }
//доробити
    @Override
    protected int getMaxOption() {
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if(currentUser!=null&&currentUser.getRole()==Role.ADMIN||currentUser.getRole()==Role.MANAGER) {
            return 7;
        }
        else{
            return 4;
        }
    }

    @SneakyThrows
    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 5:
                teacherService.search().showAllTeachers();
                Method addMethod = this.getClass().getDeclaredMethod("addTeacher");
                if (Authorization.access(addMethod)) {
                    addTeacher();
                }
                validation.waitZeroToExit();
                break;
            case 6:
                teacherService.search().showAllTeachers();
                Method editMethod = this.getClass().getDeclaredMethod("editTeacher");
                if (Authorization.access(editMethod)) {
                    editTeacher();
                }
                validation.waitZeroToExit();
                break;
            case 7:
                teacherService.search().showAllTeachers();
                Method deleteMethod = this.getClass().getDeclaredMethod("deleteTeacher");
                if (Authorization.access(deleteMethod)) {
                    deleteTeacher();
                }
                validation.waitZeroToExit();
                break;
            case 2:
                teacherService.search().showAllTeachers();
                validation.waitZeroToExit();
                break;
            case 3:
                sortTeachersByAlphabetInFaculty();
                validation.waitZeroToExit();
                break;
            case 4:
                sortTeachersByAlphabetInDepartment();
                validation.waitZeroToExit();
                break;
            case 1:
                searchTeacher.showMenu();
                break;

        }
    }
                @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void editTeacher() {
        System.out.println("--- Редагування інформації про викладача ---");
        String id = validID.idMustExist("Введіть ID викладача: ", new UniqueData() {
            @Override
            public boolean dubl(String id) {
                return teacherService.search().existsById(id);
            }
        });
        String pib = validation.readNotEmptyString("Введіть нове ПІБ викладача: ");
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
        departmentService.search().showAllDepartments();

                    String departmentName = validName.nameMustExist("Введіть назву кафедри до якої підв'язаний викладач: ", new UniqueData() {
                        @Override
                        public boolean dubl(String departmentName) {
                            return departmentService.search().existsByName(departmentName);
                        }
                    });
        try{
        //===================Запитати чи можна дописати пошук за назвою
//        teacherCRUDService.editTeacher(id, position, academicDegree,  academicTitle,pib, fakeDepartment);
            teacherService.crud().editTeacher(id, position, academicDegree,  academicTitle,pib, departmentName);
            System.out.println(" Інформацію про студента успішно оновлено.");
        } catch (Exception e) {
            System.out.println("Помилка при створенні: " + e.getMessage());
        }
    }
            @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void addTeacher() {
                System.out.println("--- ДОДАТИ ВИКЛАДАЧА ---");
                String id = validID.idUni("Введіть ID викладача: ", new UniqueData() {
                    @Override
                    public boolean dubl(String id) {
                        return teacherService.search().existsById(id);
                    }
                });
                String pib = validation.readNotEmptyString("ПІБ: ");

                LocalDate dateOfBirth = validLocalDate.readLocalDate("Введіть дату народження: ");
                String email = readEmail.isValidEmail("Введіть email викладача: ");
                String phoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть номер телефону викладача: ");
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
                departmentService.search().showAllDepartments();
                String departmentName = validName.nameMustExist("Введіть назву кафедри до якої підв'язаний викладач: ", new UniqueData() {
                    @Override
                    public boolean dubl(String departmentName) {
                        return departmentService.search().existsByName(departmentName);
                    }
                });
                    String dateOfEmployment = validation.readNotEmptyString("Дата прийняття на роботу: ");
                    //Сонічка я не дуже розумію що таке rate тому залишила як є
                    String rate = validation.readNotEmptyString("Ставка: ");
                    try {
                        Department fakeDepartment = new Department();
                        fakeDepartment.setName(departmentName);
                        Teacher newTeacher = new Teacher(id, pib, fakeDepartment, position,
                                degree, title, dateOfEmployment, rate, dateOfBirth,
                                email, phoneNumber);
//            teacherCRUDService.addTeacher(newTeacher);
                        teacherService.crud().addTeacher(newTeacher);
                        System.out.println("Викладача додано");

                    } catch (Exception e) {
                        System.out.println("Помилка при створенні: " + e.getMessage());
                    }
                }
            @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void deleteTeacher() {
        System.out.println("--- ВИДАЛЕННЯ ВИКЛАДАЧА ---");
                String id = validID.idMustExist("Введіть ID викладача: ", new UniqueData() {
                    @Override
                    public boolean dubl(String id) {
                        return teacherService.search().existsById(id);
                    }
                });
        try{
            teacherService.crud().deleteTeacher(id);
        System.out.println("Команду виконано (викладача видалено, якщо він існував)");
         }catch (Exception e){
        System.out.println("Помилка: " + e.getMessage());}
    }

    private void sortTeachersByAlphabetInFaculty(){
        String faculty = validation.readNotEmptyString("Введіть назву факультету для сортування: ");
        //String department = validation.readNotEmptyString("Введіть назву кафедри для сортування: ");
//        teacherSortingService.sortTeachersByAlphabetInFaculty(faculty, department);
        teacherService.sorting().sortTeachersByAlphabetInFaculty(faculty);
    }
    private void sortTeachersByAlphabetInDepartment(){
        String department = validation.readNotEmptyString("Введіть назву кафедри для сортування: ");
//        teacherSortingService.sortTeachersByAlphabetInDepartment(department);
        teacherService.sorting().sortTeachersByAlphabetInDepartment(department);
    }
}


