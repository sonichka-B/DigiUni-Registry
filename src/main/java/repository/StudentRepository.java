package repository;

import domain.DTO.StudentDTO;
import domain.Department;
import domain.Student;

import java.util.*;

public class StudentRepository extends Repository<Student> {
   // private final List<Student> students = new ArrayList<>();
//    private HashSet<String> ids = new HashSet<>();

    @Override
    public void add(Student student) {
//        ids.add(student.getId());
        super.add(student);
    }

    @Override
    public void delete(Student student) {
        super.delete(student);
    }

    @Override
    public List<Student> findAll() {
            return super.findAll();
        }

    @Override
    public Optional<Student> findById(String id){
        for (Student student: findAll()){
            if (student.getId().equals(id)){
                return Optional.of(student);
            }
        }
        return Optional.empty();
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

//    public List<StudentDTO> findByFullName(String pib){
//        return findAll().stream()
//                .filter(student -> student.getPIB().equals(pib))
//                .map(student -> new StudentDTO(student.getId(), student.getPIB(),
//                        student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
//                .toList();
//    }

        public List<StudentDTO> findByCourse(int course){
            return findAll().stream()
                    .filter(student -> student.getCourse() == course)
                    .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                            student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
                    .toList();
        }

        public List<StudentDTO> findByGroup(int group){
            return findAll().stream()
                    .filter(student -> student.getGroup() == group)
                    .map(student -> new StudentDTO(student.getId(), student.getPIB(),
                            student.getCourse(),  student.getDepartment(), student.getGroup(), student.getEmail()))
                    .toList();
        }

//        варіант через map
//        @Override
//        public Optional< Student> findById(String id){
//            if(forIds.containsKey(id)){
//                return Optional.of(forIds.get(id));
//            }
//            return Optional.empty();
//        }



}
