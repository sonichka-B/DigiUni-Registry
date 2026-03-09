package service;

import domain.Department;
import domain.Teacher;
import repository.DepartmentRepository;
import repository.TeacherRepository;

public class TeacherCRUDService {
    private static final TeacherRepository teacherRepository = new TeacherRepository();

    public void addTeacher(Teacher teacher) {
        if(teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        if(teacher.getFullName() == null ) {
            throw new IllegalArgumentException("Teacher's name fields cannot be null");
        }
        if(teacher.getPosition() == null) {
            throw new IllegalArgumentException("Teacher's position cannot be null");
        }
        if(teacher.getAcademicDegree() == null) {
            throw new IllegalArgumentException("Teacher's academic degree cannot be null");
        }
        if(teacher.getAcademicTitle() == null) {
            throw new IllegalArgumentException("Teacher's academic title cannot be null");
        }
        if(teacherRepository.findByName(teacher.getFullName()) != null) {
            throw new IllegalArgumentException("A teacher with the same full name already exists");
        }
        if(teacher.getDepartment() == null || teacher.getDepartment().getName() == null) {
            throw new IllegalArgumentException("Teacher must be associated with a department");
        }
        teacherRepository.add(teacher);
    }
    public void deleteTeacher(String id) {
        Teacher teacher = teacherRepository.findById(id);
        if(teacher != null) {
            teacherRepository.delete(teacher);
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
