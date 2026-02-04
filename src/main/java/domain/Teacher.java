package domain;

public class Teacher extends Person{
//    посада, науковий ступінь, вчене звання, дата прийняття на роботу, ставка/навантаження.
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
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        if(!position.equals("лаборант") && !position.equals("асистент") && !position.equals("викладач") && !position.equals("старший викладач") && !position.equals("доцент") && !position.equals("професор") && !position.equals("декан") && !position.equals("ректор/президент")) {
            throw new IllegalArgumentException("Position must be 'лаборант', 'асистент', 'викладач', 'старший викладач', 'доцент', 'професор', 'декан' or 'ректор/президент'");
        }
        this.position = position;
    }
    public String getAcademicDegree() {
        return academicDegree;
    }
    public void setAcademicDegree(String academicDegree) {
        if(!academicDegree.equals("кандидат наук") && !academicDegree.equals("доктор наук") && !academicDegree.equals("доктор філософії") ) {
            throw new IllegalArgumentException("Academic degree must be 'кандидат наук', 'доктор наук' or 'доктор філософії'");
        }
        this.academicDegree = academicDegree;
    }
    public String getAcademicTitle() {
        return academicTitle;
    }
    public void setAcademicTitle(String academicTitle) {
        if (academicTitle != null && !academicTitle.equals("професор") && !academicTitle.equals("доцент") && !academicTitle.equals("старший дослідник")) {
            throw new IllegalArgumentException("Academic title must be 'професор', 'доцент' or 'старший дослідник'");
        }
        this.academicTitle = academicTitle;
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
