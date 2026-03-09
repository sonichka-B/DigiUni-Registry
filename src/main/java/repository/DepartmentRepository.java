package repository;

import domain.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository extends Repository<Department>{
    private final List<Department> departments = new ArrayList<>();

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
    public Department findById(String id){
        for(Department department:departments){
            if(department.getId().equals(id)){
                return department;
            }
        } return null;
    }

    @Override
    public Department findByName(String name){
        for(Department department:departments){
            if(department.getName().equals(name)){
                return department;
            }
        } return null;
    }

    public Department findByFaculty(String faculty){
        for(Department department:departments){
            if(department.getFaculty().equals(faculty)){
                return department;
            }
        } return null;
    }
}
