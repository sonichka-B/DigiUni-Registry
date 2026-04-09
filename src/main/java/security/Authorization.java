package security;

import domain.Role;
import domain.Users;
import lombok.Getter;

import java.lang.reflect.Method;
@Getter

public class Authorization {
    //    what the user can do
    public static boolean access(Method method) {
        if (!method.isAnnotationPresent(RoleAnotation.class)) {
            return true;
        }
        Users currentUser = Authentication.getInstance().checkCurrentUser();
        if (currentUser == null) {
            System.out.println("Будь ласка авторизуйтесь");
            return false;
        }
        RoleAnotation annotation = method.getAnnotation(RoleAnotation.class);
        Role[] rolesAllowed = annotation.requireRole();
        Role userRole = currentUser.getRole();
        for (Role role : rolesAllowed) {
            if (role == userRole) {
                return true;
            }
        }
        if (userRole == Role.ADMIN)
            return true;

        System.out.println("У вас недостатньо прав для виконання цієї операції. Ваша роль: " + userRole);
        return false;
    }
}
    /*public boolean onlyView(Role role){
        System.out.println("Доступ дозволено для ролі: " + role);
        return true;
    }

    public boolean crudAccess(Role role){
        if(role == Role.MANAGER || role == Role.ADMIN){
            System.out.println("Доступ дозволено для ролі: " + role);
            return true;
        } else {
            System.out.println("Доступ заборонено для ролі: " + role);
            return false;
        }
    }

    public boolean fullAccess(Role role){
        if(role == Role.ADMIN){
            System.out.println("Доступ дозволено для ролі: " + role);
            return true;
        } else {
            System.out.println("Доступ заборонено для ролі: " + role);
            return false;
        }
    }*/

