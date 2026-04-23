package service;

import domain.DTO.DepartmentDTO;
import domain.Department;
import domain.Faculty;
import domain.Role;
import domain.Student;
import exceptions.NotFoundNameException;
import repository.DepartmentRepository;
import repository.StudentRepository;
import exceptions.IncorrectDataException;
import exceptions.IdAlreadyPresentException;
import exceptions.NotFoundIDException;
import security.RoleAnotation;


import java.time.LocalDate;
import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;
@RoleAnotation(requireRole={Role.ADMIN, Role.MANAGER})

public class StudentCRUDService {
    private StudentRepository studentRepository = new StudentRepository();
    private DepartmentRepository departmentRepository;

    public StudentRepository getRepository() {
        return studentRepository;
    }

    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }
    public void addStudent(Student student) {
        if(student != null) {
            if(student.getCourse() < 1 || student.getCourse() > 6){
                throw new IncorrectDataException("Помилка: Курс повинен бути в межах від 1 до 6");

            }
            if(studentRepository.findById(student.getId()).isPresent()){
                throw new IdAlreadyPresentException("Студент", student.getId());
            }
            if(student.getGroup() < 1){
                throw new IncorrectDataException("Помилка: Номер групи повинен бути позитивним числом");

            }
            if(student.getDepartment() == null || student.getDepartment().getName().trim().isEmpty()){
                throw new IncorrectDataException("Помилка: Студент повинен бути прив'язаний до кафедри");
            }if(departmentRepository!= null) {
                Department department = departmentRepository.findByName(student.getDepartment().getName())
                        .orElseThrow(() -> new NotFoundNameException("Кафедри", student.getDepartment().getName()));
                student.setDepartment(department);
            }
            if (student.getEmail() == null || !student.getEmail().contains("@")) {
                throw new IncorrectDataException("Помилка: Email має містити символ '@' та не може бути порожнім");
            }
            if(student.getPhoneNumber() == null || !student.getPhoneNumber().matches("^\\+380[0-9]{9}$")) {
                throw new IncorrectDataException("Помилка: невірний формат номера телефону (повинен починаться с +380 и 9 цифр)");
            }
            if(student.getDateOfBirth() == null || student.getDateOfBirth().isAfter(java.time.LocalDate.now())) {
                throw new IncorrectDataException("Помилка: дата народження не може бути null");
            }
            validateNotEmpty(student.getPIB(), "ПІБ студента");
            if (student.getYearOfAdmission() < 2015 || student.getYearOfAdmission() > 2025) {
                throw new IncorrectDataException("Рік вступу повинен бути в межах від 2015 до 2025");
            }
            studentRepository.add(student);
        }

        //id, firstName, middleName, lastName, dateOfBirth, email, phoneNumber, course, group, yearOfAdmission, formOfEducation, status
    }


    public void deleteStudent(String id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundIDException("Студента ", id));
        studentRepository.delete(student);
    }

    public boolean editStudent(String id, String pib,
                               int course, String department, int group,
                               String status, String email, String phoneNumber) {
        Optional<Student> oStudent = studentRepository.findById(id);
        if(oStudent.isEmpty()) {
            return false;
        }
        Student student = oStudent.get();
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            student.setPhoneNumber(phoneNumber);
        }
        if (email!= null && !email.trim().isEmpty()) {
            student.setEmail(email);
        }
        if (pib!=null&&!pib.trim().isEmpty()){
            student.setPIB(pib);
        }

        if(status!=null&&!status.trim().isEmpty()){
            student.setStatus(status);
        }
        if(course>0&&course<7){
            student.setCourse(course);
        } else {
            throw new IncorrectDataException("Помилка: Курс повинен бути в межах від 1 до 6");
        }
        if(group>0&&group<7){
            student.setGroup(group);
        } else {
            throw new IncorrectDataException("Помилка: Номер групи повинен бути в межах від 1 до 6");
        }
        if (department != null && !department.trim().isEmpty()) {
            Department found = departmentRepository.findByName(department)
                    .orElseThrow(() -> new NotFoundNameException("Кафедри", department));
            student.setDepartment(found);
        }
        return true;
    }



    public void transferToNewDepartment(String id, String department) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundIDException("Студента", id));
        Department real = departmentRepository.findByName(department)
                .orElseThrow(() -> new NotFoundNameException("Кафедри", department));
        student.setDepartment(real);
        }

    public void transferToNewCourse(String id, int newCourse) {
        if(newCourse>6 || newCourse<1){
            throw new IncorrectDataException("Неправильний номер курсу (1-6)");
        }
        Student student = studentRepository.findById(id)
        .orElseThrow(() -> new NotFoundIDException("Студента", id));
        student.setCourse(newCourse);
    }

    public void transferToNewGroup(String id, int newGroup) {
        if(newGroup>6 || newGroup<1){
            throw new IncorrectDataException("Неправильний номер групи (1-6)");
        }
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundIDException("Студента", id));
        student.setGroup(newGroup);
    }
}
