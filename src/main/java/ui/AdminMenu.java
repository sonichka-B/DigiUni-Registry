package ui;

import domain.Role;
import domain.Users;
import security.Authentication;
import security.RoleAnotation;
import service.UsersService;
@RoleAnotation(requireRole = {Role.ADMIN})
public class AdminMenu extends BaseMenu {
    private UsersService usersService;

    public AdminMenu(UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    protected void printTitle() {
        System.out.println("---Додаткові функції для адміна---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Змінити роль користувача");
        System.out.println("2. Видалити користувача");
        System.out.println("3. Вивести всіх користувачів");
        System.out.println("0. Вихід");
    }

    @Override
    protected int getMaxOption() {
        return 3;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                showAllUsers();
                changeUserRole();
                validation.waitZeroToExit();
                break;
            case 2:
                showAllUsers();
                deleteUser();
                validation.waitZeroToExit();
                break;
            case 3:
                showAllUsers();
                validation.waitZeroToExit();
                break;
        }
    }

    private void showAllUsers() {
        System.out.println("Список користувачів у системі:");
        for (Users user : usersService.findAllUsers()) {
            System.out.println("Логін: " + user.getUsername() + " | Роль: " + user.getRole());
        }
    }

    private void changeUserRole() {
        System.out.println("Введіть логін користувача, роль якого хочете змінити:");
        String username = validation.readNotEmptyString("");
        Users user = usersService.findUserByUsername(username);
        if (user == null) {
            return;
        }
        System.out.println("Поточна роль користувача: " + user.getRole());
        System.out.println("Виберіть нову роль:");
        System.out.println("1. ADMIN");
        System.out.println("2. MANAGER");
        System.out.println("3. USER");
        int roleChoice = validation.readInt("Введіть номер ролі: ", 1, 3);
        Role newRole = null;
        switch (roleChoice) {
            case 1:
                newRole = Role.ADMIN;
                break;
            case 2:
                newRole = Role.MANAGER;
                break;
            case 3:
                newRole = Role.USER;
                break;
        }
        usersService.changeRole(username, newRole);
        System.out.println("Роль користувача успішно змінена на " + newRole);
    }
    private void deleteUser(){
        System.out.println("Введіть логін користувача, якого хочете видалити:");
        String username = validation.readNotEmptyString("");
        Users user = usersService.findUserByUsername(username);
        if (user == null) {
            return;
        }
        if (user.getUsername().equals(Authentication.getInstance().checkCurrentUser().getUsername())) {
            System.out.println("Ви не можете видалити свій власний акаунт.");
            return;
        }
        usersService.deleteUser(username);
        System.out.println("Користувач успішно видалений.");

    }
}
