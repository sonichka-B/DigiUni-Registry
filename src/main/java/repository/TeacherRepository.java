package repository;

import domain.Teacher;

import java.util.ArrayList;
import java.util.List;

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
    public Teacher findById(String id){
        for(Teacher teacher:teachers){
            if(teacher.getId().equals(id)){
                return teacher;
            }
        }return null;
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
