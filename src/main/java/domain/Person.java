package domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter@Setter
public class Person {
    private  String id;
    private  String firstName;
    private  String lastName;
    private  String middleName;
    private  LocalDate dateOfBirth;
    private  String email;
    private  String phoneNumber;

    public Person(String id, String firstName, String middleName, String lastName,
                  LocalDate dateOfBirth, String email, String phoneNumber) {
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
