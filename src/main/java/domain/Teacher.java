package domain;

public class Teacher extends Person{
//    посада, науковий ступінь, вчене звання, дата прийняття на роботу, ставка/навантаження.
    private String position;
    private String academicDegree;
    private String academicTitle;
    private final String dateOfEmployment;
    private String rate;

    public Teacher(String id, String firstName, String lastName, String middleName,
                   String dateOfBirth, String email, String phoneNumber,
                   String position, String academicDegree, String academicTitle,
                   String dateOfEmployment, String rate) {
        super(id, firstName, lastName, middleName, dateOfBirth, email, phoneNumber);
        this.position = position;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.dateOfEmployment = dateOfEmployment;
        this.rate = rate;
    }
    public String getPosition() {
        return position;
    }
    public String getAcademicDegree() {
        return academicDegree;
    }
    public String getAcademicTitle() {
        return academicTitle;
    }
    public String getDateOfEmployment() {
        return dateOfEmployment;
    }
    public String getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "position='" + position + '\'' +
                ", academicDegree='" + academicDegree + '\'' +
                ", academicTitle='" + academicTitle + '\'' +
                ", dateOfEmployment='" + dateOfEmployment + '\'' +
                ", rate='" + rate + '\'' +
                '}';
    }
}
