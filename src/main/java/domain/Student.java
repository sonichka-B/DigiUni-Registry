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
    private Department department;

    public Student(String id, String firstName, String middleName, String lastName, int course, Department department, int group,
                   int yearOfAdmission, String formOfEducation, String status, LocalDate dateOfBirth, String email, String phoneNumber) {
        super(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber);
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
                "id:'" + getId() + '\'' + ", ПІБ:'" + getFullName() + '\'' + ", курс:" + course + '\'' + ", кафедра:'" + department.getName() + '\'' +
                ", група:" + group + '\'' + ", рік вступу:" + yearOfAdmission + '\'' + ", форма навчання:'" + formOfEducation + '\'' +
                ", статус:'" + status + '\'' + ", дата народження:'" + getDateOfBirth() + '\'' +
                ", email:'" + getEmail() + '\'' +", номер телефону:'" + getPhoneNumber() + '\'' +
                '}';
    }
}
