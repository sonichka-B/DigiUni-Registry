package repository;

import domain.Department;

public class DepartmentRepository {

    private Department[] departments;
    private int count;
    private final int capacity = 5;

    public DepartmentRepository() {
        this.departments = new Department[capacity];
        this.count = 0;
    }

    public void save(Department department){
        if (count < capacity) {
            departments[count] = department;
            count++;
        } else {
            System.out.println("Repository is full. Cannot add more departments.");
        }
    }

    public Department findByName(String name){
        for (int i = 0; i < count; i++) {
            if (departments[i].getName().equals(name)) {
                return departments[i];
            }
        }
        return null;
    }

    public Department[] findAll(){
        Department[] result = new Department[count];
        System.arraycopy(departments, 0, result, 0, count);
        return result;
    }

    public void deleteByName(String name){
        for (int i = 0; i < count; i++) {
            if (departments[i].getName().equals(name)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < count - 1; j++) {
                    departments[j] = departments[j + 1];
                }
                departments[count - 1] = null; // Clear the last element
                count--;
                return;
            }
        }
    }
}
