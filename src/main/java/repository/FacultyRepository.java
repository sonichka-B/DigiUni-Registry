package repository;

import domain.Faculty;

import java.util.ArrayList;
import java.util.List;

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
    public Faculty findById(String id){
        for (Faculty faculty: faculties){
            if (faculty.getId().equals(id)){
                return faculty;
            }
        }
        return null;
    }
}
