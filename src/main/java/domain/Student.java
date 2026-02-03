package domain;

public class Student extends Person{
//     ідентифікатор студента/залікова, курс (1-6), група, рік вступу,
//     форма навчання (бюджет/контракт), статус (навчається/академвідпустка/відрахований).
    private final String id;
    private int course;
    private int group;
    private final int yearOfAdmission;
    private final String formOfEducation;
    private String status;

    public Student(String id, String firstName, String lastName, String middleName,
                   String dateOfBirth, String email, String phoneNumber,
                   int course, int group, int yearOfAdmission,
                   String formOfEducation, String status) {
        super(id, firstName, lastName, middleName, dateOfBirth, email, phoneNumber);
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
        if(course < 1 || course > 6) {
            throw new IllegalArgumentException("Course must be between 1 and 6");
        }
        this.course = course;
    }
    public int getGroup() {
        return group;
    }
    public void setGroup(int group) {
        if(group<1 || group>6) {
            throw new IllegalArgumentException("Group must be between 1 and 6");
        }
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
        if(!status.equals("навчається") && !status.equals("академвідпустка") && !status.equals("відрахований")) {
            throw new IllegalArgumentException("Status must be 'навчається', 'академвідпустка' or 'відрахований'");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", course=" + course +
                ", group=" + group +
                ", yearOfAdmission=" + yearOfAdmission +
                ", formOfEducation='" + formOfEducation + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
