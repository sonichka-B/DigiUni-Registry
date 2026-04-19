package domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
@Getter@NoArgsConstructor
public class Department {
    private  String id;
    @Setter
    private String name;
    @Setter
    private Faculty faculty;
    @Setter
    private Teacher head;
    @Setter
    private String location;

    public Department(String id, String name, Faculty faculty, Teacher head, String location) {
        this.id = id;
        this.name = name;
        this.faculty = faculty;
        this.head = head;
        this.location = location;
    }
    public Department(String name){
        this.name = name;
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
