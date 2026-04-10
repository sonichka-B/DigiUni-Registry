package security;

import domain.Role;
import domain.Users;
import service.UsersService;

public class Authentication {
//    who is the user
    private static Authentication instance;
    private final UsersService usersService = new UsersService();
    private Users currentUser;
    private Authentication() {
    }
    public static Authentication getInstance() {
        if (instance == null) {
            instance = new Authentication();
        }
        return instance;
    }
   public boolean login(String username, String password) {
        Users user = usersService.findUserByUsername(username);
        if (user==null){
            Users newUser = new Users(username, password, Role.USER);
            usersService.addUser(newUser);
            System.out.println("Новий користувач створений: " + username);
            currentUser = newUser;
            return true;
        }
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Успішний вхід для користувача: " + username);
            currentUser = user;
            return true;
        } else if (user == null) {
            System.out.println("Користувач з таким ім'ям не знайдений: " + username);
            return false;
        } else if (!user.getPassword().equals(password)) {
            System.out.println("Невірний пароль для користувача: " + username);
            return false;
        }
        return false;
    }

    public void logout(String username) {
        currentUser = null;
        System.out.println("Користувач " + username + " вийшов з системи.");
    }

    public Users checkCurrentUser(){
        return currentUser;
    }
}
