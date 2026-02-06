package domain;

public class Department {
    private final String id;
    private final String name;
    private final Faculty faculty;
    private final Teacher head;
    private  String location;

    public Department(String id, String name, Faculty faculty, Teacher head, String location) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Teacher getHead() {
        return head;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("Department(id=%s, name=%s, faculty=%s, head=%s, location=%s)",
                id, name, faculty.getName(), head.getFullName(), location);
    }
}
