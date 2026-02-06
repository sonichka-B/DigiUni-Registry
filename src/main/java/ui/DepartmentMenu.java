package ui;

import domain.Department;
import service.DepartmentService;

public class DepartmentMenu extends BaseMenu {
    private DepartmentService departmentService;

    public DepartmentMenu(DepartmentService departmentService) {
        this.departmentService = departmentService;
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
                departmentService.listAllDepartments();
                validation.waitZeroToExit();
                break;
            case 2:
                System.out.println("--- Додавання кафедри ---");
                String id = validation.readString("ID кафедри: ");
                String name = validation.readString("Назва кафедри: ");
               // String faculty = validation.readString("Факультет: ");
                //String head = validation.readString("Завідувач кафедри: ");
                String location = validation.readString("Розташування кафедри: ");
                try {
                    //Сонічка тут напевно потрібно щось змінити в domain.Не хоче додаватися факультет та голова кафедри тому поки null
                    departmentService.addDepartment(new Department(id, name, null, null, location));
                    System.out.println(" Кафедру успішно додано.");
                } catch (Exception e) {
                    System.out.println(" Помилка: " + e.getMessage());
                }
                validation.waitZeroToExit();
                break;
            case 3:
                //змінити щоб писалося якщо немає такої кафедри.Потрібно замінити void на boolean в serviсе
                System.out.println("--- Видалення кафедри ---");
                String nameDel = validation.readString("Введіть назву для видалення: ");
                departmentService.deleteDepartment(nameDel);
                System.out.println(" Команду видалення виконано.");
                validation.waitZeroToExit();
                break;
        }
    }
}