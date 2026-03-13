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

    @Override
    public Optional<Users> findById(String id) {
        return super.findById(id);
    }

    @Override
    public Optional<Users> findByName(String username) {
        return super.findByName(username);
    }

    @Override
    public List<Users> findAll() {
        return super.findAll();
    }
}
