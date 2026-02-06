package repository;

import domain.Student;

public class StudentRepository {
    private Student [] students;
    private int count;
    private final int capacity = 5;

    public StudentRepository() {
        this.students = new Student[capacity];
        this.count = 0;
    }
    public void save(Student student){
        if (count < capacity) {
            students[count] = student;
            count++;
        } else {
            System.out.println("Repository is full. Cannot add more students.");
        }
    }

    public Student findByFullName(String firstName, String middleName, String lastName){
        for (int i = 0; i < count; i++) {
            if (students[i].getFirstName().equals(firstName) &&
                students[i].getMiddleName().equals(middleName) &&
                students[i].getLastName().equals(lastName)) {
                return students[i];
            }
        }
        return null;
    }

    public Student findByCourse(int course){
        for (int i = 0; i < count; i++) {
            if (students[i].getCourse() == course) {
                return students[i];
            }
        }
        return null;
    }

    public Student findByGroup(int group){
        for (int i = 0; i < count; i++) {
            if (students[i].getGroup() == group) {
                return students[i];
            }
        }
        return null;
    }

    public Student findById(String id){
        for (int i = 0; i < count; i++) {
            if (students[i].getId().equals(id)) {
                return students[i];
            }
        }
        return null;
    }

    public Student[] findAll(){
        Student[] result = new Student[count];
        System.arraycopy(students, 0, result, 0, count);
        return result;
    }

    public void deleteByFullName(String firstName, String middleName, String lastName){
        for (int i = 0; i < count; i++) {
            if (students[i].getFirstName().equals(firstName) &&
                students[i].getMiddleName().equals(middleName) &&
                students[i].getLastName().equals(lastName)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null; // Clear the last element
                count--;
                return;
            }
        }
    }

}
