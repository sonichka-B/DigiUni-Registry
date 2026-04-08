package ui;
import security.Authentication;

public class AuthorizationMenu extends BaseMenu{
    private MainMenu mainMenu;
    public AuthorizationMenu(MainMenu mainMenu) {
        this.mainMenu = mainMenu;
    }
    @Override
    protected void printTitle() {
        System.out.println("--- АВТОРИЗАЦІЯ ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Авторизуватися");
        System.out.println("0. Вихід");
    }

    @Override
    protected int getMaxOption() {
        return 1;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                login();
                break;
        }
    }
    private void login() {
        System.out.println("Введіть логін:");
        String username = validation.readNotEmptyString("");
        System.out.println("Введіть пароль:");
        String password = validation.readNotEmptyString("");

        mainMenu.showMenu();
    }
}
