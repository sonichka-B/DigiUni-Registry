package repository;

import domain.Faculty;
import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacultyRepository extends Repository<Faculty>{
    private final List<Faculty> faculties = new ArrayList<>();

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
        for (Faculty faculty: faculties){
            if (faculty.getId().equals(id)){
                return Optional.of(faculty);
            }
        }
        return Optional.empty();
    }

    @Override
    public Faculty findByName(String name){
        for (Faculty faculty: faculties){
            if (faculty.getName().equals(name)){
                return faculty;
            }
        }
        return null;
    }
}
