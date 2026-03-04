package domain;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Person {
    private final String id;
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final String dateOfBirth;
    private final String email;
    private final String phoneNumber;

    public Person(String id, String firstName, String middleName, String lastName,
                  String dateOfBirth, String email, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFullName() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return "Person{" +
                "id:'" + id + '\'' + ", ПІБ:'" + getFullName() + '\'' +", дата народження:'" + dateOfBirth + '\'' +
                ", email:'" + email + '\'' +", номер телефону:'" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Person person)) return false;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
