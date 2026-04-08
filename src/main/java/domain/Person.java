package domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

@Getter@Setter
public class Person {
    private  String id;
    private  String PIB;
    private  LocalDate dateOfBirth;
    private  String email;
    private  String phoneNumber;

    public Person(String id, String PIB,
                  LocalDate dateOfBirth, String email, String phoneNumber) {
        this.id = id;
        this.PIB = PIB;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id:'" + id + '\'' + ", ПІБ:'" + PIB + '\'' +", дата народження:'" + dateOfBirth + '\'' +
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
