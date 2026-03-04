package domain;

import lombok.Getter;

import java.util.Objects;
@Getter
public class Department {
    private final String id;
    private final String name;
    private final Faculty faculty;
    private final Teacher head;
    private final String location;

    public Department(String id, String name, Faculty faculty, Teacher head, String location) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
    }

//    public String getId() {
//        return id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public Faculty getFaculty() {
//        return faculty;
//    }
//
//    public Teacher getHead() {
//        return head;
//    }
//
//    public String getLocation() {
//        return location;
//    }


    @Override
    public String toString() {
        String facultyName = (faculty != null) ? faculty.getName() : "Не вказано";
        String headName = (head != null) ? head.getFullName() : "Не вказано";
        return String.format("Department(id=%s, name=%s, faculty=%s, head=%s, location=%s)",
                id, name, facultyName, headName, location);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Department that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
