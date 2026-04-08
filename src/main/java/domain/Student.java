package domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Student extends Person{
    private int course;
    private int group;
    private final int yearOfAdmission;
    private final String formOfEducation;
    private String status;
    private String department;

    public Student(String id, String PIB, int course, String department, int group,
                   int yearOfAdmission, String formOfEducation, String status, LocalDate dateOfBirth, String email, String phoneNumber) {
        super(id, PIB, dateOfBirth, email, phoneNumber);
        this.course = course;
        this.group = group;
        this.yearOfAdmission = yearOfAdmission;
        this.formOfEducation = formOfEducation;
        this.status = status;
        this.department = department;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id:'" + getId() + '\'' + ", ПІБ:'" + getPIB() + '\'' + ", курс:" + course + '\'' + ", кафедра:'" + department + '\'' +
                ", група:" + group + '\'' + ", рік вступу:" + yearOfAdmission + '\'' + ", форма навчання:'" + formOfEducation + '\'' +
                ", статус:'" + status + '\'' + ", дата народження:'" + getDateOfBirth() + '\'' +
                ", email:'" + getEmail() + '\'' +", номер телефону:'" + getPhoneNumber() + '\'' +
                '}';
    }
}
