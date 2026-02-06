package repository;

import domain.Teacher;

public class TeacherRepository {
    private Teacher[] teachers;
    private int count;
    private final int capacity = 5;

    public TeacherRepository() {
        this.teachers = new Teacher[capacity];
        this.count = 0;
    }

    public void save(Teacher teacher){
        if (count < capacity) {
            teachers[count] = teacher;
            count++;
        } else {
            System.out.println("Repository is full. Cannot add more teachers.");
        }
    }

    public Teacher findByFullName(String firstName, String middleName, String lastName) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getFirstName().equals(firstName) &&
                    teachers[i].getMiddleName().equals(middleName) &&
                    teachers[i].getLastName().equals(lastName)) {
                return teachers[i];
            }
        }
        return null;
    }

    public void deleteByFullName(String firstName, String middleName, String lastName) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getFirstName().equals(firstName) &&
                    teachers[i].getMiddleName().equals(middleName) &&
                    teachers[i].getLastName().equals(lastName)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    teachers[j] = teachers[j + 1];
                }
                teachers[count - 1] = null; // Clear the last element
                count--;
                return;
            }
        }
    }

    public Teacher[] findAll(){
        Teacher[] result = new Teacher[count];
        System.arraycopy(teachers, 0, result, 0, count);
        return result;
    }

    public Teacher findById(String id) {
        for (int i = 0; i < count; i++) {
            if (teachers[i].getId().equals(id)) {
                return teachers[i];
            }
        }
        return null;
    }
}
