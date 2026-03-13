package repository;

import domain.Department;
import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository extends Repository<Teacher> {
   // private final List<Teacher> teachers = new ArrayList<>();

    @Override
    public void add(Teacher teacher){
        super.add(teacher);
    }

    @Override
    public void delete(Teacher entity) {
        super.delete(entity);
    }

    @Override
    public List<Teacher> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Teacher> findById(String id){
        for(Teacher teacher:findAll()){
            if(teacher.getId().equals(id)){
                return Optional.of(teacher);
            }
        }return Optional.empty();
    }

    @Override
    public Optional<Teacher> findByName(String name){
        for(Teacher teacher:findAll()){
            if(teacher.getFullName().equals(name)){
                return Optional.of(teacher);
            }
        }return Optional.empty();
    }
}
