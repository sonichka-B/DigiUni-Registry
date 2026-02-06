package ui;

import domain.Department;
import domain.Faculty;
import domain.Teacher;
import repository.TeacherRepository;
import service.DepartmentService;
import service.FacultyService;
import service.TeacherService;

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
        System.out.println("4. Редагувати інформацію про кафедру");
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
                System.out.println("--- Список кафедр ---");
                departmentService.listAllDepartments();
                validation.waitZeroToExit();
                break;
            case 2:
                System.out.println("--- Додавання кафедри ---");
                String id = validation.readString("ID кафедри: ");
                String name = validation.readString("Назва кафедри: ");
                String facultyName = validation.readString("Факультет: ");
                Faculty faculty = FacultyService.findFacultyByName(facultyName);
                while(true){
                    if (faculty == null) {
                        System.out.println(" Помилка: Факультет з такою назвою не знайдено!");
                        facultyName = validation.readString("Факультет: ");
                        faculty = FacultyService.findFacultyByName(facultyName);
                    }
                    if (faculty != null) {
                        break;
                }
                }
                String headFirstName = validation.readString("Ім'я завідувача кафедри: ");
                String headMiddleName = validation.readString("По батькові завідувача: ");
                String headLastName = validation.readString("Прізвище завідувача: ");
                Teacher head = TeacherService.findTeacherByFullName(headFirstName, headMiddleName, headLastName);

                while (true){
                    if (head == null) {
                        System.out.println(" Помилка: Викладач з таким ім'ям не знайдено!");
                        headFirstName = validation.readString("Ім'я завідувача кафедри: ");
                        headMiddleName = validation.readString("По батькові завідувача: ");
                        headLastName = validation.readString("Прізвище завідувача: ");
                        head = TeacherService.findTeacherByFullName(headFirstName, headMiddleName, headLastName);
                    }
                    if (head != null) {
                        break;
                    }
                }

                String location = validation.readString("Розташування кафедри: ");
                try {
                    departmentService.addDepartment(new Department(id, name, faculty, head, location));
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
            case 4:
                System.out.println("--- Редагування інформації про кафедру ---");
               /* String deptId = validation.readString("Введіть ID кафедри для редагування: ");
                Department dept = departmentService.findDepartmentById(deptId);
                if (dept != null) {
                    String newName = validation.readString("Нова назва кафедри (залиште порожнім, щоб не змінювати): ");
                    String newLocation = validation.readString("Нове розташування кафедри (залиште порожнім, щоб не змін*/
                validation.waitZeroToExit();
                break;
        }
    }
}