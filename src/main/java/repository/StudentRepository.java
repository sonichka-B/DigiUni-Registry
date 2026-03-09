package repository;

import domain.Student;
import domain.Teacher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository extends Repository<Student> {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void add(Student student) {
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

        public List<Student> findByFullName(String FullName){
            List<Student> result = new ArrayList<>();
            for (Student student: students){
                if (student.getFullName().equals(FullName)){
                    result.add(student);
                }
            }
            return result;
        }

        public List<Student> findByCourse(int course){
            List<Student> result = new ArrayList<>();
            for (Student student: students){
                if (student.getCourse() == course){
                    result.add(student);
                }
            }
            return result;
        }

        public List<Student> findByGroup(int group){
            List<Student> result = new ArrayList<>();
            for (Student student: students){
                if (student.getGroup() == group){
                    result.add(student);
                }
            }
            return result;
        }
        @Override
        public Optional<Student> findById(String id){
            for (Student student: students){
                if (student.getId().equals(id)){
                    return Optional.of(student);
                }
            }
            return Optional.empty();
        }

}
