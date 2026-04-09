package ui;

import domain.Department;
import domain.Role;
import lombok.SneakyThrows;
import repository.TeacherRepository;
import security.Authorization;
import security.RoleAnotation;
import service.*;
import repository.FacultyRepository;

import java.lang.reflect.Method;

//DONE
public class DepartmentMenu extends BaseMenu {
    private DepartmentCRUDService departmentCRUDService;
    private DepartmentSearchService departmentSearchService;
    private FacultySearchService facultySearchService;
    private TeacherRepository teacherRepository;
    private FacultyRepository facultyRepository;

    public DepartmentMenu(DepartmentCRUDService departmentCRUDService, DepartmentSearchService departmentSearchService, FacultySearchService facultySearchService, TeacherRepository teacherRepository, FacultyRepository facultyRepository) {
        this.departmentCRUDService = departmentCRUDService;
        this.departmentSearchService = departmentSearchService;
        this.facultySearchService = facultySearchService;
    }

    @Override
    protected void printTitle() {
        System.out.println("--- МЕНЮ КАФЕДР ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Додати кафедру");
        System.out.println("2. Редагувати інформацію про кафедру");
        System.out.println("3. Видалити кафедру");
        System.out.println("4. Показати всі кафедри");
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
                Method addMethod = this.getClass().getDeclaredMethod("addDepartment");
                if (Authorization.access(addMethod)) {
                    addDepartment();
                }
                validation.waitZeroToExit();
                break;
            case 2:
                Method editMethod = this.getClass().getDeclaredMethod("editDepartment");
                if (Authorization.access(editMethod)) {
                    editDepartment();
                }
                validation.waitZeroToExit();
                break;
            case 3:
                Method deleteMethod = this.getClass().getDeclaredMethod("deleteDepartment");
                if (Authorization.access(deleteMethod)) {
                    deleteDepartment();
                }
                validation.waitZeroToExit();
                break;
            case 4:
                System.out.println("--- Список кафедр ---");
                departmentSearchService.showAllDepartments();
                validation.waitZeroToExit();
                break;
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void addDepartment() {
        System.out.println("--- Додавання кафедри ---");
        String id = validation.readNotEmptyString("ID кафедри: ");
        String name = validation.readNotEmptyString("Назва кафедри: ");
        String idFaculty = validation.readNotEmptyString("ID факультету: ");
        String idHead = validation.readNotEmptyString("ID завідувача кафедри: ");
        String location = validation.readNotEmptyString("Розташування кафедри: ");
        ////String id, String name, String faculty, String head, String location
        try {
            departmentCRUDService.addDepartment(new Department(id, name, idFaculty, idHead, location));
            System.out.println(" Кафедру успішно додано.");
        } catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
        private void editDepartment() {
            System.out.println("--- Редагування інформації про кафедру ---");
            String idDepartment = validation.readNotEmptyString("Введіть ID кафедри для редагування: ");
            String newLocation = validation.readNotEmptyString("Введіть нове розташування кафедри: ");
            String newHeadName = validation.readNotEmptyString("Введіть імя нового завідувача кафедри: ");
            try {
                boolean success = departmentCRUDService.editDepartment(idDepartment, newHeadName, newLocation);
                if (success) {
                    System.out.println(" Інформацію про кафедру успішно оновлено.");
                } else { /*можливо потрібно переробити*/
                    System.out.println(" Помилка: Кафедру з таким ID не знайдено.");
                }
            } catch (Exception e) {
                System.out.println(" Помилка: " + e.getMessage());
            }
        }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
        private void deleteDepartment() {
            System.out.println("--- Видалення кафедри ---");
            String nameHead = validation.readNotEmptyString("Введіть ID для видалення: ");
            try{
                departmentCRUDService.deleteDepartment(nameHead);
                System.out.println(" Команду видалення виконано.");
        }catch (Exception e){
            System.out.println(" Помилка: " + e.getMessage());}
        }
    }