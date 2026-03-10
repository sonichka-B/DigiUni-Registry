package repository;

import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository extends Repository<Teacher> {
    private final List<Teacher> teachers = new ArrayList<>();

    @Override
    public void add(Teacher teacher){
        teachers.add(teacher);
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
        for(Teacher teacher:teachers){
            if(teacher.getId().equals(id)){
                return Optional.of(teacher);
            }
        }return Optional.empty();
    }

    @Override
    public Teacher findByName(String name){
        for(Teacher teacher:teachers){
            if(teacher.getFullName().equals(name)){
                return teacher;
            }
        }return null;
    }
}
