package service;

import domain.Department;
import domain.Teacher;
import exceptions.IncorrectDataException;
import repository.DepartmentRepository;
import repository.TeacherRepository;

import java.util.Optional;

public class TeacherCRUDService {
    private static final TeacherRepository teacherRepository = new TeacherRepository();

    public void addTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IncorrectDataException("Помилка: Дані про викладача відсутні");
        }
        if (teacher.getFullName() == null || teacher.getFullName().trim().isEmpty()) {
            throw new IncorrectDataException("Помилка: ПІБ викладача не може бути порожнім");
        }
        if (teacher.getPosition() == null || teacher.getPosition().trim().isEmpty()) {
            throw new IncorrectDataException("Помилка: Посада викладача не може бути порожньою");
        }
        if (teacher.getAcademicDegree() == null || teacher.getAcademicDegree().trim().isEmpty()) {
            throw new IncorrectDataException("Помилка: Науковий ступінь викладача не може бути порожнім");
        }
        if (teacher.getAcademicTitle() == null || teacher.getAcademicTitle().trim().isEmpty()) {
            throw new IncorrectDataException("Помилка: Вчене звання викладача не може бути порожнім");
        }
        if (teacherRepository.findByName(teacher.getFullName()) != null) {
            throw new IncorrectDataException("Помилка: Викладач з таким ПІБ вже існує");
        }
        if (teacher.getDepartment() == null || teacher.getDepartment().getName() == null) {
            throw new IncorrectDataException("Помилка: Викладач повинен бути прив'язаний до кафедри");
        }
        teacherRepository.add(teacher);
    }
    public void deleteTeacher(String id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if(teacher.isPresent()) {
            teacherRepository.delete(teacher.orElse(null));
        } else {
            System.out.println("Викладача з таким id не знайдено");
        }
    }

    public boolean editTeacher(String id, String position, String academicDegree, String academicTitle, String department) {
        Teacher teacher = teacherRepository.findById(id);
        Department departments = new DepartmentRepository().findByName(department);
        if (teacher != null && departments != null) {
            teacher.setPosition(position);
            teacher.setAcademicDegree(academicDegree);
            teacher.setAcademicTitle(academicTitle);
            teacher.setDepartment(departments);
            return true;
        }
        return false;
    }
}
