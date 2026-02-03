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
    public int getGroup() {
        return group;
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
