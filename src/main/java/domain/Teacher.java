package domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Teacher extends Person{
    private String position;
    private String academicDegree;
    private String academicTitle;
    private final String dateOfEmployment;
    private final String rate;

    public Teacher(String id, String firstName, String middleName, String lastName,
                   String dateOfBirth, String email, String phoneNumber,
                   String position, String academicDegree, String academicTitle,
                   String dateOfEmployment, String rate) {
        super(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber);
        this.position = position;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.dateOfEmployment = dateOfEmployment;
        this.rate = rate;
    }
//    public String getPosition() {
//        return position;
//    }
//    public void setPosition(String position) {
//        this.position = position;
//    }
//    public String getAcademicDegree() {
//        return academicDegree;
//    }
//    public void setAcademicDegree(String academicDegree) {
//        this.academicDegree = academicDegree;
//    }
//    public String getAcademicTitle() {
//        return academicTitle;
//    }
//    public void setAcademicTitle(String academicTitle) {
//        this.academicTitle = academicTitle;
//    }
//    public String getDateOfEmployment() {
//        return dateOfEmployment;
//    }
//    public String getRate() {
//        return rate;
//    }

    @Override
    public String toString() {
        return "Teacher{" + "id:'" + getId() + '\'' +
                ", ПІБ:'" + getFullName() + '\'' +", дата народження:'" + getDateOfBirth() + '\'' +
                ", email:'" + getEmail() + '\'' +
                ", номер телефону:'" + getPhoneNumber() + '\'' +
                ", посада:'" + position + '\'' +
                ", науковий ступінь:'" + academicDegree + '\'' +
                ", вчене звання:'" + academicTitle + '\'' +
                ", дата прийняття на роботу:'" + dateOfEmployment + '\'' +
                ", ставка/навантаження:'" + rate + '\'' +
                '}';
    }
}
