package repository;

import domain.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentRepository extends Repository<Department>{
    //private final List<Department> departments = new ArrayList<>();

    @Override
    public void add(Department department) {
        super.add(department);
    }

    @Override
    public void delete(Department department) {
        super.delete(department);
    }

    @Override
    public List<Department> findAll() {
        return super.findAll();
    }

    @Override
    public Optional<Department> findById(String id){
        for(Department department:findAll()){
            if(department.getId().equals(id)){
                return Optional.of(department);
            }
        } return Optional.empty();
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
            if(department.getFaculty().equals(faculty)){
                return Optional.of(department);
            }
        } return Optional.empty();
    }
}
