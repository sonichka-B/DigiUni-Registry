package service;

import domain.Student;
import repository.StudentRepository;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student student) {
        if(student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if(student.getFirstName() == null || student.getMiddleName() == null || student.getLastName() == null) {
            throw new IllegalArgumentException("Student's name fields cannot be null");
        }
        if(student.getCourse() == 0 ) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        if(student.getGroup() == 0 ) {
            throw new IllegalArgumentException("Group cannot be null");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(String firstName, String middleName, String lastName) {
    studentRepository.deleteByFullName(firstName, middleName, lastName);
}

public void showAllStudents() {
    Student[] students = studentRepository.findAll();
    for (Student student : students) {
        System.out.println(student);
    }
}

public Student findStudentByFullName(String firstName, String middleName, String lastName) {
    return studentRepository.findByFullName(firstName, middleName, lastName);
}

public Student findStudentByCourse(int course) {
    return studentRepository.findByCourse(course);
}
public Student findStudentByGroup(int group) {
    return studentRepository.findByGroup(group);
}


}
