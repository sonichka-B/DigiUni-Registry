package service;

import domain.Users;
import repository.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class UsersService {
    private final UsersRepository usersRepository = new UsersRepository();

    public void addUser(Users user){
        if(user!=null && user.getUsername() !=null && user.getPassword() !=null && user.getRole() !=null){
            usersRepository.add(user);
        }else{
            System.out.println("Помилка: Користувач не може бути null і має мати ім'я, пароль і роль");
        }
    }

    public void deleteUser(String username){
        Users user = usersRepository.findByName(username);
        if (user != null) {
            usersRepository.delete(user);
        } else {
            System.out.println("Користувач з таким ім'ям не знайдений");
        }
    }

    public Users findUserByUsername(String username){
        Users user = usersRepository.findByName(username);
        if( user != null){
            return usersRepository.findByName(username);
        } else {
            System.out.println("Користувач з таким ім'ям не знайдений");
            return null;
        }
    }

    public List<Users> findAllUsers(){
        return usersRepository.findAll();
    }
}
