package domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public class Faculty {
    private final String id;
    private final String name;
    private final String shortName;
    @Setter
    private  String dean;
    @Setter
    private String phoneNumber;

    public Faculty(String id, String name, String shortName, String dean, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.shortName = shortName;
        this.dean = dean;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return String.format("Faculty(id=%s, name=%s, shortName=%s, dean=%s, phoneNumber=%s)",
                id, name, shortName, dean, phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Faculty faculty)) return false;
        return Objects.equals(id, faculty.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
