package repository;

import domain.DTO.DepartmentDTO;
import domain.DTO.StudentDTO;
import domain.Department;
import domain.Student;

import java.util.*;

public class StudentRepository extends Repository<Student> {

    @Override
    protected String getEntityId(Student entity) {
        return entity.getId();
    }

    @Override
    public Optional<Student> findByName(String name){
        for (Student student: findAll()){
            if (student.getPIB().equals(name)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
    }

    public List<StudentDTO> findByCourse(int course){
        return findAll().stream()
                .filter(student -> student.getCourse() == course)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                            student.getCourse(),  new DepartmentDTO(student.getDepartment().getName()), student.getGroup(), student.getEmail()))
                .toList();
    }

    public List<StudentDTO> findByGroup(int group){
        return findAll().stream()
                .filter(student -> student.getGroup() == group)
                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                            student.getCourse(),  new DepartmentDTO(student.getDepartment().getName()), student.getGroup(), student.getEmail()))
                .toList();
    }
}
