package domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Student extends Person{
    private int course;
    private int group;
    private final int yearOfAdmission;
    private String formOfEducation;
    private String status;

    public Student(String id, String firstName, String middleName, String lastName,
                   String dateOfBirth, String email, String phoneNumber,
                   int course, int group, int yearOfAdmission,
                   String formOfEducation, String status) {
        super(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber);
        this.course = course;
        this.group = group;
        this.yearOfAdmission = yearOfAdmission;
        this.formOfEducation = formOfEducation;
        this.status = status;
    }
//    public int getCourse() {
//        return course;
//    }
//    public void setCourse(int course) {
//        this.course = course;
//    }
//    public int getGroup() {
//        return group;
//    }
//    public void setGroup(int group) {
//        this.group = group;
//    }
//    public int getYearOfAdmission() {
//        return yearOfAdmission;
//    }
//    public String getFormOfEducation() {
//        return formOfEducation;
//    }
//    public void setFormOfEducation(String formOfEducation) {
//        this.formOfEducation = formOfEducation;
//    }
//    public String getStatus() {
//        return status;
//    }
//    public void setStatus(String status) {
//        this.status = status;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "id:'" + getId() + '\'' + ", ПІБ:'" + getFullName() + '\'' +", дата народження:'" + getDateOfBirth() + '\'' +
                ", email:'" + getEmail() + '\'' +", номер телефону:'" + getPhoneNumber() + '\'' +
                ", курс:" + course +
                ", група:" + group +
                ", рік вступу:" + yearOfAdmission +
                ", форма навчання:'" + formOfEducation + '\'' +
                ", статус:'" + status + '\'' +
                '}';
    }
}
