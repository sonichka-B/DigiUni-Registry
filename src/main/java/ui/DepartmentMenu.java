package ui;

import domain.*;
import lombok.SneakyThrows;
import repository.TeacherRepository;
import security.Authentication;
import security.Authorization;
import security.RoleAnotation;
import service.*;
import repository.FacultyRepository;
import validation.UniqueData;
import validation.ValidID;
import validation.ValidName;

import java.lang.reflect.Method;

//DONE
public class DepartmentMenu extends BaseMenu {
    DepartmentService departmentService;
    FacultyService facultyService;
    TeacherRepository teacherRepository;
    FacultyRepository facultyRepository;
    ValidID validID = new ValidID();
    ValidName validName = new ValidName();
    TeacherService teacherService;

    public DepartmentMenu(DepartmentService departmentService, FacultyService facultyService, TeacherRepository teacherRepository, FacultyRepository facultyRepository,TeacherService teacherService) {
        this.departmentService = departmentService;
        this.facultyService = facultyService;
        this.teacherRepository = teacherRepository;
        this.facultyRepository = facultyRepository;
        this.teacherService = teacherService;
    }

    @Override
    protected void printTitle() {
        System.out.println("--- МЕНЮ КАФЕДР ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Показати всі кафедри");
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if(currentUser!=null&&currentUser.getRole()==Role.ADMIN||currentUser.getRole()==Role.MANAGER) {
            System.out.println("2. Додати кафедру");
            System.out.println("3. Редагувати інформацію про кафедру");
            System.out.println("4. Видалити кафедру");
        }
        System.out.println("0. Назад");
    }

    @Override
    protected int getMaxOption() {
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if(currentUser!=null&&currentUser.getRole()==Role.ADMIN||currentUser.getRole()==Role.MANAGER) {
            return 4;
        }
        else{
            return 1;
        }
    }

    @SneakyThrows
    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 2:
                departmentService.search().showAllDepartments();
                Method addMethod = this.getClass().getDeclaredMethod("addDepartment");
                if (Authorization.access(addMethod)) {
                    addDepartment();
                }
                validation.waitZeroToExit();
                break;
            case 3:
                departmentService.search().showAllDepartments();
                Method editMethod = this.getClass().getDeclaredMethod("editDepartment");
                if (Authorization.access(editMethod)) {
                    editDepartment();
                }
                validation.waitZeroToExit();
                break;
            case 4:
                departmentService.search().showAllDepartments();
                Method deleteMethod = this.getClass().getDeclaredMethod("deleteDepartment");
                if (Authorization.access(deleteMethod)) {
                    deleteDepartment();
                }
                validation.waitZeroToExit();
                break;
            case 1:
                System.out.println("--- Список кафедр ---");
                departmentService.search().showAllDepartments();
                validation.waitZeroToExit();
                break;
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
    private void addDepartment() {
        System.out.println("--- Додавання кафедри ---");
        String id = validID.idUni("Введіть ID кафедри: ", new UniqueData() {
            @Override
            public boolean dubl(String id) {
                return departmentService.search().existsById(id);
            }
        });
        String name = validation.readNotEmptyString("Назва кафедри: ");
        facultyService.search().showAllFaculties();
        String idFaculty = validID.idMustExist("Введіть ID факультету: ", new UniqueData() {
            @Override
            public boolean dubl(String idFaculty) {
                return facultyService.search().existsById(idFaculty);
            }
        });

        teacherService.search().showAllTeachers();
        String pibHead = validName.nameMustExist("ПІБ завідувача кафедри: ", new UniqueData() {
            @Override
            public boolean dubl(String pibHead) {
                return teacherService.search().existsByName(pibHead);
            }
        });
        String location = validation.readNotEmptyString("Розташування кафедри: ");
        ////String id, String name, String faculty, String head, String location
        try {
//            departmentCRUDService.addDepartment(new Department(id, name, idFaculty, idHead, location));
            Faculty fakeF = new Faculty();
            fakeF.setId(idFaculty);

            Teacher fakeT = new Teacher();
            fakeT.setPIB(pibHead);

            departmentService.crud().addDepartment(new Department(id, name, fakeF, fakeT, location));
            System.out.println(" Кафедру успішно додано.");
        } catch (Exception e) {
            System.out.println(" Помилка: " + e.getMessage());
        }
    }
    @RoleAnotation(requireRole = {Role.ADMIN, Role.MANAGER})
        private void editDepartment() {
            System.out.println("--- Редагування інформації про кафедру ---");
        String id = validID.idMustExist("Введіть ID кафедри: ", new UniqueData() {
            @Override
            public boolean dubl(String id) {
                return departmentService.search().existsById(id);
            }
        });
            String newLocation = validation.readNotEmptyString("Введіть нове розташування кафедри: ");
        teacherService.search().showAllTeachers();
        String newHeadName = validName.nameMustExist("ПІБ завідувача кафедри: ", new UniqueData() {
            @Override
            public boolean dubl(String newHeadName) {
                return teacherService.search().existsByName(newHeadName);
            }
        });
            try {
//                boolean success = departmentCRUDService.editDepartment(idDepartment, newHeadName, newLocation);
                boolean success = departmentService.crud().editDepartment(id, newHeadName, newLocation);
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
        String id = validID.idMustExist("Введіть ID кафедри: ", new UniqueData() {
            @Override
            public boolean dubl(String id) {
                return departmentService.search().existsById(id);
            }
        });
            try{
//                departmentCRUDService.deleteDepartment(nameHead);
                departmentService.crud().deleteDepartment(id);
        }catch (Exception e){
            System.out.println(" Помилка: " + e.getMessage());}
        }
    }