package repository;

import domain.Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentRepository extends Repository<Student> {
    private final List<Student> students = new ArrayList<>();

        @Override
        public void add(Student student) {
            students.add(student);
        }

        @Override
        public void delete(Student student) {
            students.remove(student);
        }

        @Override
        public List<Student> findAll() {
            return new ArrayList<>(students);
        }
        Comparator<Student> findByFullName = Comparator.comparing(Student::getFullName);
        Comparator<Student> findByCourse = Comparator.comparingInt(Student::getCourse);
        Comparator<Student> findByGroup = Comparator.comparingInt(Student::getGroup);

}
