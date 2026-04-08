package domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Teacher extends Person{
    private String position;
    private String academicDegree;
    private String academicTitle;
    private final String dateOfEmployment;
    private final String rate;
    private Department department;

    public Teacher(String id, String PIB,Department department, String position,
                   String academicDegree, String academicTitle, String dateOfEmployment, String rate, LocalDate dateOfBirth,
                   String email, String phoneNumber) {
        super(id, PIB, dateOfBirth, email, phoneNumber);
        this.position = position;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.dateOfEmployment = dateOfEmployment;
        this.rate = rate;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id:'" + getId() + '\'' +
                ", ПІБ:'" + getPIB() + '\'' + ", кафедра:'" + department.getName() + '\'' +
                ", посада:'" + position + '\'' +
                ", науковий ступінь:'" + academicDegree + '\'' +
                ", вчене звання:'" + academicTitle + '\'' +
                ", дата прийняття на роботу:'" + dateOfEmployment + '\'' +
                ", ставка/навантаження:'" + rate + '\'' + ", дата народження:'" + getDateOfBirth() + '\'' +
                ", email:'" + getEmail() + '\'' +
                ", номер телефону:'" + getPhoneNumber() + '\'' +
                '}';
    }
}
