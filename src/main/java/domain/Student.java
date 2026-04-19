package domain;

import domain.DTO.DepartmentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter@NoArgsConstructor
public class Student extends Person{
    private int course;
    private int group;
    private  int yearOfAdmission;
    private  String formOfEducation;
    private String status;
    private Department department;

    public Student(String id, String PIB, int course, Department department, int group,
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
