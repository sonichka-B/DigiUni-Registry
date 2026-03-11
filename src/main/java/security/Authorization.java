package security;

import domain.Role;

public class Authorization {
//    what the user can do

    public boolean onlyView(Role role){
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
    }
}
