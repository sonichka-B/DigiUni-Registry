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
        System.out.println("--- МЕНЮ КАФЕДР ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Показати всі кафедри");
        System.out.println("2. Додати кафедру");
        System.out.println("3. Видалити кафедру");
        System.out.println("0. Назад");
    }

    @Override
    protected int getMaxOption() {
        return 3;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                System.out.println("--- Список кафедр ---");
                facultyService.showAllFaculties();
                validation.waitZeroToExit();
                break;
            case 2:
                System.out.println("--- Додавання факультету ---");
                String id = validation.readString("ID факультету: ");
                String name = validation.readString("Назва факультету: ");
                String shortName = validation.readString("Факультет: ");
                String dean = validation.readString("Декан: ");
                String phoneNumber = validation.readString("Номер телефону: ");
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
                String nameDel = validation.readString("Введіть назву для видалення: ");
                facultyService.deleteFaculty(nameDel);
                System.out.println(" Команду видалення виконано.");
                validation.waitZeroToExit();
                break;
        }
    }
}
