package domain;

public class Student extends Person{
    private final String id;
    private int course;
    private int group;
    private final int yearOfAdmission;
    private final String formOfEducation;
    private String status;

    public Student(String id, String firstName, String middleName, String lastName,
                   String dateOfBirth, String email, String phoneNumber,
                   int course, int group, int yearOfAdmission,
                   String formOfEducation, String status) {
        super(id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber);
        this.id = id;
        this.course = course;
        this.group = group;
        this.yearOfAdmission = yearOfAdmission;
        this.formOfEducation = formOfEducation;
        this.status = status;
    }
    public String getId() {
        return id;
    }
    public int getCourse() {
        return course;
    }
    public void setCourse(int course) {
        this.course = course;
    }
    public int getGroup() {
        return group;
    }
    public void setGroup(int group) {
        this.group = group;
    }
    public int getYearOfAdmission() {
        return yearOfAdmission;
    }
    public String getFormOfEducation() {
        return formOfEducation;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getFullName() {
        return getFirstName() + " " + getMiddleName() + " " + getLastName();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' + ", fullName='" + getFullName() + '\'' +", dateOfBirth='" + getDateOfBirth() + '\'' +
                ", email='" + getEmail() + '\'' +", phoneNumber='" + getPhoneNumber() + '\'' +
                ", course=" + course +
                ", group=" + group +
                ", yearOfAdmission=" + yearOfAdmission +
                ", formOfEducation='" + formOfEducation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
