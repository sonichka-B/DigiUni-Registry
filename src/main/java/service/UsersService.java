package service;

import domain.Role;
import domain.Users;
import repository.UsersRepository;

import java.util.List;
import java.util.Optional;

public class UsersService {
    private static UsersRepository usersRepository = new UsersRepository();

   public void addUser(Users user){
        if(user!=null && user.getUsername() !=null && user.getPassword() !=null && user.getRole() !=null){
            usersRepository.add(user);
        }else{
            System.out.println("Помилка: Користувач не може бути null і має мати ім'я, пароль і роль");
        }
    }

    public void deleteUser(String username){
        Optional<Users> user = usersRepository.findByName(username);
        if (user.isPresent()) {
            usersRepository.delete(user.get());
        } else {
            System.out.println("Користувач з таким ім'ям не знайдений");
        }
    }
    public Users findUserByUsername(String username){
        Optional<Users> user = usersRepository.findByName(username);
        if(user.isPresent()){
            return user.get();
        } else {
            System.out.println("Користувач з таким ім'ям не знайдений");
            return null;
        }
    }

    public List<Users> findAllUsers(){
        return usersRepository.findAll();
    }
    public boolean changeRole(String username, Role newRole){
       Users user = findUserByUsername(username);
       if(user!=null){
           user.setRole(newRole);
           return true;
       }
       else return false;
    }
}
