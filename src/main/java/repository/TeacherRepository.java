package repository;

import domain.Department;
import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository extends Repository<Teacher> {
    @Override
    protected String getEntityId(Teacher entity) {
        return entity.getId();
    }

    @Override
    public Optional<Teacher> findByName(String name){
        for(Teacher teacher:findAll()){
            if(teacher.getPIB().equals(name)){
                return Optional.of(teacher);
            }
        }return Optional.empty();
    }

}
