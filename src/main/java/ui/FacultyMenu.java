package ui;

import domain.Faculty;
import service.*;
import validation.*;


public class FacultyMenu extends BaseMenu{
    private FacultyCRUDService facultyCRUDService;
    private FacultySearchService facultySearchService;
    private ReadPhoneNumber readPhoneNumber = new ReadPhoneNumber();

    public FacultyMenu(FacultyCRUDService facultyCRUDService, FacultySearchService facultySearchService) {
        this.facultyCRUDService = facultyCRUDService;
        this.facultySearchService = facultySearchService;
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

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                addFaculty();
                validation.waitZeroToExit();
                break;
            case 2:
                editFaculty();
                validation.waitZeroToExit();
                break;
            case 3:
                deleteFaculty();
                validation.waitZeroToExit();
                break;

            case 4:
                System.out.println("--- Список факультетів ---");
                facultySearchService.showAllFaculties();
                validation.waitZeroToExit();
                break;
        }
    }
    private void addFaculty() {
        System.out.println("--- Додавання факультету ---");
        String id = validation.readNotEmptyString("Введіть ID факультету: ");
        String name = validation.readNotEmptyString("Введіть назву факультету: ");
        String shortName = validation.readNotEmptyString("Введіть коротку назву факультету: ");
        String dean = validation.readNotEmptyString("Введіть декана: ");
        String phoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть номер телефону: ");
        try {
            facultyCRUDService.addFaculty(new Faculty(id, name, shortName, dean, phoneNumber));
            System.out.println(" Факультет успішно додано.");
        } catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
    private void editFaculty(){
        System.out.println("--- Редагування інформації про факультет ---");
        String idFaculty = validation.readNotEmptyString("Введіть ID факультету для редагування: ");
        String newDean = validation.readNotEmptyString("Введіть нового декана: ");
        String newPhoneNumber = readPhoneNumber.isValidPhoneNumber("Введіть новий номер телефону: ");
        try{
        boolean success = facultyCRUDService.editFaculty(idFaculty, newDean, newPhoneNumber);
        if (success) {
            System.out.println(" Інформацію про факультет успішно оновлено.");
        } else {
            System.out.println(" Помилка: Факультету з таким ID не знайдено.");
        }
        }catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
    private void deleteFaculty(){
        System.out.println("--- Видалення факультету ---");
        String nameDel = validation.readNotEmptyString("Введіть ID для видалення: ");
        try{
        facultyCRUDService.deleteFaculty(nameDel);
        System.out.println(" Команду видалення виконано.");
    }catch (Exception e) {
        System.out.println(" Помилка: " + e.getMessage());}
    }
}
