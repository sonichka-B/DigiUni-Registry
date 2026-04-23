package service;

import domain.Role;
import domain.Users;

import repository.UsersRepository;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsersService {
    private static UsersRepository usersRepository = new UsersRepository();
    private static final Logger log = LoggerFactory.getLogger(UsersService.class);
   public void addUser(Users user){
        if(user!=null && user.getUsername() !=null && user.getPassword() !=null && user.getRole() !=null){
            usersRepository.add(user);
            log.info("Створено нового користувача: логін={}, роль={}", user.getUsername(), user.getRole());
        }else{
            log.warn("Користувач не може бути null і має мати ім'я, пароль і роль");
        }
    }

    public void deleteUser(String username){
        Optional<Users> user = usersRepository.findByName(username);
        if (user.isPresent()) {
            log.info("Користувача '{}' успішно видалено", username);
            usersRepository.delete(user.get());
        } else {
            log.warn("Користувач з таким ім'ям не знайдений");
        }
    }
    public Users findUserByUsername(String username){
        Optional<Users> user = usersRepository.findByName(username);
        if(user.isPresent()){
            return user.get();
        } else {
            log.warn("Користувач з таким ім'ям не знайдений");
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
