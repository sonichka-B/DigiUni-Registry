package ui;

import domain.Faculty;
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

public class FacultyMenu extends BaseMenu {
    private FacultyService facultyService;
    private TeacherService teacherService;
    private ReadPhoneNumber readPhoneNumber = new ReadPhoneNumber();
    private ValidID validID = new ValidID();
    private ValidName validName = new ValidName();

    public FacultyMenu(FacultyService facultyService, TeacherService teacherService) {
        this.facultyService = facultyService;
        this.teacherService = teacherService;
    }

    @Override
    protected void printTitle() {
        System.out.println("--- МЕНЮ ФАКУЛЬТЕТІВ ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Показати всі факультети");
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if (currentUser != null && currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER) {
            System.out.println("2. Додати факультет");
            System.out.println("3. Редагувати інформацію про факультет");
            System.out.println("4. Видалити факультет");
        }
        System.out.println("0. Назад");
    }

    @Override
    protected int getMaxOption() {
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if (currentUser != null && currentUser.getRole() == Role.ADMIN || currentUser.getRole() == Role.MANAGER) {
            return 4;
        } else {
            return 1;
        }
    }

    @SneakyThrows
    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 2:
                facultyService.search().showAllFaculties();
                Method addMethod = this.getClass().getDeclaredMethod("addFaculty");
                if (Authorization.access(addMethod)) {
                    addFaculty();
                }
                validation.waitZeroToExit();
                break;
            case 3:
                facultyService.search().showAllFaculties();
                Method editMethod = this.getClass().getDeclaredMethod("editFaculty");
                if (Authorization.access(editMethod)) {
                    editFaculty();
                }
                validation.waitZeroToExit();
                break;
            case 4:
                facultyService.search().showAllFaculties();
                Method deleteMethod = this.getClass().getDeclaredMethod("deleteFaculty");
                if (Authorization.access(deleteMethod)) {
                    deleteFaculty();
                }
                validation.waitZeroToExit();
                break;

            case 1:
                System.out.println("--- Список факультетів ---");
                facultyService.search().showAllFaculties();
                validation.waitZeroToExit();
                break;
        }
    }

    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void addFaculty() {
        System.out.println("--- Додавання факультету ---");

        String id = validID.idUni("Введіть ID факультету: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return facultyService.search().existsById(input);
            }
        });

        String name = validation.readNotEmptyString("Введіть назву факультету: ");
        String shortName = validation.readNotEmptyString("Введіть коротку назву факультету: ");

        teacherService.search().showAllTeachers();
        System.out.println("Виберіть декана факультету зі списку вчителів (введіть ПІБ): ");
        String dean = validName.nameMustExist("Введіть ПІБ декана: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return teacherService.search().existsByName(input);
            }
        });

        String phoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть номер телефону: ");

        try {
            Teacher fakeT = new Teacher();
            fakeT.setPIB(dean);
            facultyService.crud().addFaculty(new Faculty(id, name, shortName, fakeT, phoneNumber));
            System.out.println(" Факультет успішно додано.");
        } catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }

    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void editFaculty() {
        System.out.println("--- Редагування інформації про факультет ---");

        String idFaculty = validID.idMustExist("Введіть ID факультету для редагування: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return facultyService.search().existsById(input);
            }
        });

        teacherService.search().showAllTeachers();
        System.out.println("Виберіть нового декана факультету зі списку вчителів (введіть ПІБ): ");
        String newDean = validName.nameMustExist("Введіть ПІБ декана: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return teacherService.search().existsByName(input);
            }
        });

        String newPhoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть новий номер телефону: ");

        try {
            boolean success = facultyService.crud().editFaculty(idFaculty, newDean, newPhoneNumber);
            if (success) {
                System.out.println(" Інформацію про факультет успішно оновлено.");
            } else {
                System.out.println(" Помилка: Факультету з таким ID не знайдено.");
            }
        } catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }

    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void deleteFaculty() {
        System.out.println("--- Видалення факультету ---");

        String nameDel = validID.idMustExist("Введіть ID для видалення: ", new UniqueData() {
            @Override
            public boolean dubl(String input) {
                return facultyService.search().existsById(input);
            }
        });

        try {
            facultyService.crud().deleteFaculty(nameDel);
            System.out.println(" Команду видалення виконано.");
        } catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
}