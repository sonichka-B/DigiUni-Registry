package repository;

import domain.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepository extends Repository<Department>{
    @Override
    protected String getEntityId(Department entity) {
        return entity.getId();
    }

    @Override
    public Optional<Department> findByName(String name){
        for(Department department:findAll()){
            if(department.getName().equals(name)){
                return Optional.of(department);
            }
        } return Optional.empty();
    }

    public Optional<Department> findByFaculty(String faculty){
        for(Department department:findAll()){
            if(department.getFaculty().getName().equals(faculty)){
                return Optional.of(department);
            }
        } return Optional.empty();
    }
}
