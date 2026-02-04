package repository;

import domain.Faculty;

public class FacultyRepository {
    private Faculty[] faculties;
    private int count;
    private final int capacity = 5;

    public FacultyRepository() {
        this.faculties = new Faculty[capacity];
        this.count = 0;
    }
    public void save(Faculty faculty) {
        if (count < capacity) {
            faculties[count] = faculty;
            count++;
        } else {
            System.out.println("Repository is full. Cannot add more faculties.");
        }
    }

    public Faculty[] findAll() {
        Faculty[] result = new Faculty[count];
        System.arraycopy(faculties, 0, result, 0, count);
        return result;
    }
    public Faculty findByName(String name) {
        for (int i = 0; i < count; i++) {
            if (faculties[i].getName().equals(name)) {
                return null;
            }
        }
        return null;
    }
    public void deleteByName(String name) {
        for (int i = 0; i < count; i++) {
            if (faculties[i].getName().equals(name)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    faculties[j] = faculties[j + 1];
                }
                faculties[count - 1] = null; // Clear the last element
                count--;
                return;
            }
        }
    }

}
