package domain;

import domain.DTO.DepartmentDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
public class Teacher extends Person{
    private String position;
    private String academicDegree;
    private String academicTitle;
    private  String dateOfEmployment;
    private  String rate;
    private Department department;

    public Teacher(String id, String PIB,Department department, String position,
                   String academicDegree, String academicTitle, String dateOfEmployment, String rate, LocalDate dateOfBirth,
                   String email, String phoneNumber) {
        super(id, PIB, dateOfBirth, email, phoneNumber);
        this.position = position;
        this.academicDegree = academicDegree;
        this.academicTitle = academicTitle;
        this.dateOfEmployment = dateOfEmployment;
        this.rate = rate;
        this.department = department;
    }
    public Teacher(String PIB){
        super(PIB);
    }

    @Override
    public String toString() {
        return "Teacher{" + "id:'" + getId() + '\'' +
                ", ПІБ:'" + getPIB() + '\'' + ", кафедра:'" + department + '\'' +
                ", посада:'" + position + '\'' +
                ", науковий ступінь:'" + academicDegree + '\'' +
                ", вчене звання:'" + academicTitle + '\'' +
                ", дата прийняття на роботу:'" + dateOfEmployment + '\'' +
                ", ставка/навантаження:'" + rate + '\'' + ", дата народження:'" + getDateOfBirth() + '\'' +
                ", email:'" + getEmail() + '\'' +
                ", номер телефону:'" + getPhoneNumber() + '\'' +
                '}';
    }
}
