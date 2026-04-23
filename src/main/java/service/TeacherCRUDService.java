package service;

import domain.Department;
import domain.Faculty;
import domain.Role;
import domain.Teacher;
import exceptions.IdAlreadyPresentException;
import exceptions.IncorrectDataException;
import exceptions.NotFoundNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.DepartmentRepository;
import repository.TeacherRepository;
import security.RoleAnotation;

import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;
@RoleAnotation(requireRole={Role.ADMIN, Role.MANAGER})

public class TeacherCRUDService {
    private TeacherRepository teacherRepository = new TeacherRepository();
    private DepartmentRepository departmentRepository ;
    private static final Logger log = LoggerFactory.getLogger(TeacherCRUDService.class);

    public TeacherRepository getRepository() {
        return teacherRepository;
    }
    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public void addTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new IncorrectDataException("Помилка: Викладач не может быть null");
        }
        validateNotEmpty(teacher.getId(), "ID викладача");
        if(teacherRepository.findById(teacher.getId()).isPresent()){
            throw new IdAlreadyPresentException("Викладач", teacher.getId());
        }
        validateNotEmpty(teacher.getPIB(), "ПІБ викладача");
        validateNotEmpty(teacher.getPosition(), "Посада викладача");
        validateNotEmpty(teacher.getAcademicDegree(), "Науковий ступінь викладача");
        validateNotEmpty(teacher.getAcademicTitle(), "Вчене звання викладача");
        if(teacher.getDateOfBirth() == null || teacher.getDateOfBirth().isAfter(java.time.LocalDate.now())) {
            throw new IncorrectDataException("Помилка: дата народження не может быть null");
        }
        if (teacher.getDepartment() == null || teacher.getDepartment().getName() == null) {
            throw new IncorrectDataException("Помилка: Викладач повинен бути прив'язаний до кафедри");
        }
        String fakeName = teacher.getDepartment().getName();
        Department realDepartment = departmentRepository.findByName(fakeName)
                .orElseThrow(() -> new NotFoundNameException("Кафедри", fakeName));
        teacher.setDepartment(realDepartment);
        teacherRepository.add(teacher);
        log.info("Створено нового викладача");
    }
    public boolean deleteTeacher(String id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            teacherRepository.delete(teacher.get());
            log.info("Викладача з id {} видалено", id);
            return true;
        }
        log.warn("Викладача з таким ID не знайдено");
        return false;
    }


    public boolean editTeacher(String id, String position, String academicDegree, String academicTitle,String pib, String department) {
        Optional<Teacher> oTeacher = teacherRepository.findById(id);
        if(oTeacher.isEmpty()) {
            log.warn("Викладача з таким ID не знайдено");
            return false;
        }
        Teacher teacher = oTeacher.get();
        if (position!= null && !position.trim().isEmpty()) {
            teacher.setPosition(position);
        }
        if (academicDegree != null && !academicDegree.trim().isEmpty()) {
            teacher.setAcademicDegree(academicDegree);
        }
        if (academicTitle != null && !academicTitle.trim().isEmpty()) {
            teacher.setAcademicTitle(academicTitle);
        }
        if (pib!=null&&!pib.trim().isEmpty()){
            teacher.setPIB(pib);
        }
        if (department != null && !department.trim().isEmpty()) {
            Department foundDepartment=departmentRepository.findByName(department)
                    .orElseThrow(() -> new NotFoundNameException("Кафедри", department));
            teacher.setDepartment(foundDepartment);
        }
        log.info("Викладача з id {} відредаговано", id);
        return true;
    }
}
