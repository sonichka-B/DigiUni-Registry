package ui;

import domain.Faculty;
import domain.Role;
import domain.Teacher;
import lombok.SneakyThrows;
import security.Authorization;
import security.RoleAnotation;
import service.*;
import validation.*;

import java.lang.reflect.Method;


public class FacultyMenu extends BaseMenu{
    private FacultyService facultyService;

//    private FacultyCRUDService facultyCRUDService;
//    private FacultySearchService facultySearchService;
    private ReadPhoneNumber readPhoneNumber = new ReadPhoneNumber();

    public FacultyMenu(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @Override
    protected void printTitle() {
        System.out.println("--- МЕНЮ ФАКУЛЬТЕТІВ ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Додати факультет");
        System.out.println("2. Редагувати інформацію про факультет");
        System.out.println("3. Видалити факультет");
        System.out.println("4. Показати всі факультети");
        System.out.println("0. Назад");
    }

    @Override
    protected int getMaxOption() {
        return 4;
    }

    @SneakyThrows
    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                Method addMethod = this.getClass().getDeclaredMethod("addFaculty");
                if (Authorization.access(addMethod)) {
                    addFaculty();
                }
                validation.waitZeroToExit();
                break;
            case 2:
                Method editMethod = this.getClass().getDeclaredMethod("editFaculty");
                if (Authorization.access(editMethod)) {
                    editFaculty();
                }
                validation.waitZeroToExit();
                break;
            case 3:
                Method deleteMethod = this.getClass().getDeclaredMethod("deleteFaculty");
                if (Authorization.access(deleteMethod)) {
                    deleteFaculty();
                }
                validation.waitZeroToExit();
                break;

            case 4:
                System.out.println("--- Список факультетів ---");
//                facultySearchService.showAllFaculties();
                facultyService.search().showAllFaculties();
                validation.waitZeroToExit();
                break;
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void addFaculty() {
        System.out.println("--- Додавання факультету ---");
        String id = validation.readNotEmptyString("Введіть ID факультету: ");
        String name = validation.readNotEmptyString("Введіть назву факультету: ");
        String shortName = validation.readNotEmptyString("Введіть коротку назву факультету: ");
        String dean = validation.readNotEmptyString("Введіть ПІБ декана: ");
        String phoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть номер телефону: ");
        try {
//            facultyCRUDService.addFaculty(new Faculty(id, name, shortName, dean, phoneNumber));
            Teacher fakeT = new Teacher();
            fakeT.setPIB(dean);
            facultyService.crud().addFaculty(new Faculty(id, name, shortName, fakeT, phoneNumber));
            System.out.println(" Факультет успішно додано.");
        } catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void editFaculty(){
        System.out.println("--- Редагування інформації про факультет ---");
        String idFaculty = validation.readNotEmptyString("Введіть ID факультету для редагування: ");
        String newDean = validation.readNotEmptyString("Введіть ПІБ декана: ");
        String newPhoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть новий номер телефону: ");
        try{
//        boolean success = facultyCRUDService.editFaculty(idFaculty, newDean, newPhoneNumber);
            boolean success = facultyService.crud().editFaculty(idFaculty, newDean, newPhoneNumber);
        if (success) {
            System.out.println(" Інформацію про факультет успішно оновлено.");
        } else {
            System.out.println(" Помилка: Факультету з таким ID не знайдено.");
        }
        }catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void deleteFaculty(){
        System.out.println("--- Видалення факультету ---");
        String nameDel = validation.readNotEmptyString("Введіть ID для видалення: ");
        try{
//        facultyCRUDService.deleteFaculty(nameDel);
            facultyService.crud().deleteFaculty(nameDel);
        System.out.println(" Команду видалення виконано.");
    }catch (Exception e) {
        System.out.println(" Помилка: " + e.getMessage());}
    }
}
