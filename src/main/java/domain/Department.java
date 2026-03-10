package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
@Getter
public class Department {
    private final String id;
    private final String name;
    private final String faculty;
    @Setter
    private String head;
    private final String location;

    public Department(String id, String name, String faculty, String head, String location) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
    }

    @Override
    public String toString() {
        return String.format("Department(id=%s, name=%s, faculty=%s, head=%s, location=%s)",
                id, name, faculty, head, location);
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
