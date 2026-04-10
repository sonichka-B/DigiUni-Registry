package repository;

import domain.Department;
import domain.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyRepository extends Repository<Faculty>{
   // private final List<Faculty> faculties = new ArrayList<>();

    @Override
    public void add(Faculty faculty) {
        super.add(faculty);
    }

    @Override
    public void delete(Faculty entity) {
        super.delete(entity);
    }

    @Override
    public List<Faculty> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Faculty> findById(String id){
        for (Faculty faculty: findAll()){
            if (faculty.getId().equals(id)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Faculty> findByName(String name){
        for (Faculty faculty: findAll()){
            if (faculty.getName().equals(name)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addAll(List<Faculty> entities) {
        super.addAll(entities);
    }
}
