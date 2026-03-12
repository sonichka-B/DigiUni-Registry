package service;

import domain.Department;
import domain.Student;
import repository.DepartmentRepository;
import repository.StudentRepository;
import exceptions.IncorrectDataException;
import exceptions.IdAlreadyPresentException;
import exceptions.NotFoundIDException;

public class StudentCRUDService {
    private static final StudentRepository studentRepository = new StudentRepository();

    public void addStudent(Student student) {
        if(student != null) {
            if(student.getCourse() < 1 || student.getCourse() > 6){
                throw new IncorrectDataException("Помилка: Курс повинен бути в межах від 1 до 6");

            }
            if(student.getGroup() < 1){
                throw new IncorrectDataException("Помилка: Номер групи повинен бути позитивним числом");

            }
            if(student.getStatus() == null || (!student.getStatus().equals("навчається") && !student.getStatus().equals("відрахований") && !student.getStatus().equals("академічна відпустка"))){
                throw new IncorrectDataException("Помилка: Статус повинен бути 'навчається', 'відрахований' або 'академічна відпустка'");

            }
            if(student.getDepartment() == null || student.getDepartment().trim().isEmpty()){
                throw new IncorrectDataException("Помилка: Студент повинен бути прив'язаний до кафедри");

            }
            if (student.getEmail() == null || !student.getEmail().contains("@")) {
                throw new IncorrectDataException("Email має містити символ '@' та не може бути порожнім");
            }
            if(student.getPhoneNumber() == 0 || !student.getPhoneNumber().matches("\\+?\\d{10,15}")) {
                throw new IncorrectDataException("Невірний формат номера телефону (номер має містити від 10 до 15 цифр, може починатися з '+')");
            }
            if (student.getDateOfBirth() == null) {
                throw new IncorrectDataException("Дата народження не може бути порожньою");
            }
            if (student.getFirstName() == null) {
                throw new IncorrectDataException("Це поле не може бути порожнім");
            }
            if (student.getMiddleName() == null) {
                throw new IncorrectDataException("Це поле не може бути порожнім");
            }
            if (student.getLastName() == null) {
                throw new IncorrectDataException("Це поле не може бути порожнім");
            }
            if (student.getYearOfAdmission() < 2015 || student.getYearOfAdmission() > 2025) {
                throw new IncorrectDataException("Рік вступу повинен бути в межах від 2015 до 2025");
            }
            if(studentRepository.findById(student.getId()).isPresent()){
                throw new IdAlreadyPresentException("Студент", student.getId());
            }
        }
        studentRepository.add(student);
        //id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber, course, group, yearOfAdmission, formOfEducation, status
    }


    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundIDException("Студента ", id));
        studentRepository.delete(student);
    }

    public boolean editStudent(String id, int course, int group, String status, Department department) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new NotFoundIDException("Студента", id));

            if (department == null || department.getName() == null) {
                throw new IllegalArgumentException("Кафедра не може бути порожньою");
            }
            student.setCourse(course);
            student.setGroup(group);
            student.setStatus(status);
            /*student.get().setFormOfEducation();
            student.get().setDateOfBirth();
            student.get().setFirstName();
            student.get().getMiddleName();
            student.get().getLastName();
            student.get().setPhoneNumber();*/
            student.setDepartment(department.getName());
            return true;
        }


    public void transferToNewDepartment(String id, String department) {
        Student student = studentRepository.findById(id).orElse(null);
        Department departments = new DepartmentRepository().findByName(department);
        if (student != null && departments != null) {
            student.setDepartment(department);
        }
    }

    public void transferToNewCourse(String id, int newCourse) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setCourse(newCourse);
        }
    }

    public void transferToNewGroup(String id, int newGroup) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setGroup(newGroup);
        }
    }
}
