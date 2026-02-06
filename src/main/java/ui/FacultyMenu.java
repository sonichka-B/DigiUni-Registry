package ui;

import domain.Faculty;
import service.FacultyService;

public class FacultyMenu extends BaseMenu{
    private FacultyService facultyService;
    public FacultyMenu(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @Override
    protected void printTitle() {
        System.out.println("--- МЕНЮ ФАКУЛЬТЕТІВ ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Показати всі факультети");
        System.out.println("2. Додати факультет");
        System.out.println("3. Видалити факультет");
        System.out.println("4. Редагувати інформацію про факультет");
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
                System.out.println("--- Список факультетів ---");
                facultyService.showAllFaculties();
                validation.waitZeroToExit();
                break;
            case 2:
                System.out.println("--- Додавання факультету ---");
                String id = validation.readNotEmptyString("ID факультету: ");
                String name = validation.readNotEmptyString("Назва факультету: ");
                String shortName = validation.readNotEmptyString("Коротка назва факультету: ");
                String dean = validation.readNotEmptyString("Декан: ");
                String phoneNumber = validation.readNotEmptyString("Номер телефону: ");
                try {
                    //Сонічка тут напевно потрібно щось змінити в domain.Не хоче додаватися факультет та голова кафедри тому поки null
                    facultyService.addFaculty(new Faculty(id, name, shortName, dean, phoneNumber));
                    System.out.println(" Факультет успішно додано.");
                } catch (Exception e) {
                    System.out.println(" Помилка: " + e.getMessage());
                }
                validation.waitZeroToExit();
                break;
            case 3:
                System.out.println("--- Видалення факультету ---");
                String nameDel = validation.readNotEmptyString("Введіть назву для видалення: ");
                facultyService.deleteFaculty(nameDel);
                System.out.println(" Команду видалення виконано.");
                validation.waitZeroToExit();
                break;

            case 4:
                System.out.println("--- Редагування інформації про факультет ---");
                String idFaculty = validation.readNotEmptyString("Введіть ID факультету для редагування: ");
                String newDean = validation.readNotEmptyString("Введіть нового декана: ");
                boolean success = facultyService.editFaculty(idFaculty, newDean);
                if (success) {
                    System.out.println(" Інформацію про факультет успішно оновлено.");
                } else {
                    System.out.println(" Помилка: Факультету з таким ID не знайдено.");
                }
                validation.waitZeroToExit();
                break;
        }
    }
}
