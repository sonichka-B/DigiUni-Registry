package repository;

import domain.Department;
import domain.Faculty;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyRepository extends Repository<Faculty>{
    @Override
    protected String getEntityId(Faculty entity) {
        return entity.getId();
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
}
