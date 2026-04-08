package repository;

import domain.Department;
import domain.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepository extends  Repository<Users>{
    private final List<Users> users = new ArrayList<>();
    @Override
    public void add(Users user) {
        super.add(user);
    }

    @Override
    public void delete(Users user) {
        super.delete(user);
    }

    //@Override
    /*public Optional<Users> findById(String id) {
        for (Users user : users) {
            if(user.getId()equals(id)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }*/

    @Override
    public Optional<Users> findByName(String username) {
        for (Users user : users) {
            if(user.getUsername().equals(username)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Users> findAll() {
        return super.findAll();
    }
}
