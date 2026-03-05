package service;

import domain.Department;
import domain.Student;
import repository.StudentRepository;

public class StudentCRUDService {
    private static final StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student student) {
        if(student != null) {
            if(student.getCourse() < 1 || student.getCourse() > 6){
                System.out.println("Помилка: Курс повинен бути в межах від 1 до 6");
                return;
            }
            if(student.getGroup() < 1){
                System.out.println("Помилка: Номер групи повинен бути позитивним числом");
                return;
            }
            if(student.getStatus() == null || (!student.getStatus().equals("навчається") && !student.getStatus().equals("відрахований") && !student.getStatus().equals("академічна відпустка"))){
                System.out.println("Помилка: Статус повинен бути 'навчається', 'відрахований' або 'академічна відпустка'");
                return;
            }
            if(student.getDepartment() == null || student.getDepartment().getName() == null){
                System.out.println("Помилка: Студент повинен бути прив'язаний до кафедри");
                return;
            }
        }
        studentRepository.add(student);
    }


    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id);
        if(student != null) {
            studentRepository.delete(student);
        } else {
            System.out.println("Студента з таким id не знайдено");
        }
    }

    public boolean editStudent(String id, int course, int group, String status, Department department) {
        Student student = studentRepository.findById(id);
        if (student!= null && department.getName() !=null) {
            student.setCourse(course);
            student.setGroup(group);
            student.setStatus(status);
            student.setDepartment(department);
            return true;
        }
        return false;
    }

    public void transferToNewDepartment(String id, Department department) {
        Student student = studentRepository.findById(id);
        if (student != null && department.getName() != null) {
            student.setDepartment(department);
        }
    }

    public void transferToNewCourse(String id, int newCourse) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setCourse(newCourse);
        }
    }

    public void transferToNewGroup(String id, int newGroup) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            student.setGroup(newGroup);
        }
    }
}
