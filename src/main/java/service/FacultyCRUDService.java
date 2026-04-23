package service;

import domain.Department;
import domain.Faculty;
import domain.Role;
import domain.Teacher;
import exceptions.IdAlreadyPresentException;
import exceptions.IncorrectDataException;
import exceptions.NotFoundIDException;
import exceptions.NotFoundNameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.FacultyRepository;
import repository.TeacherRepository;
import security.RoleAnotation;

import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;

@RoleAnotation(requireRole={Role.ADMIN, Role.MANAGER})
public class FacultyCRUDService {
    private static final Logger log = LoggerFactory.getLogger(FacultyCRUDService.class);
    private FacultyRepository facultyRepository = new FacultyRepository();
    private TeacherRepository teacherRepository;

    public FacultyRepository getRepository() {
        return facultyRepository;
    }

    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void addFaculty(Faculty faculty) {
        if(faculty == null) {
            log.warn("Спроба додати факультет: передано null");
            throw new IncorrectDataException("Факультет не може бути null");
        }
        if(facultyRepository.findById(faculty.getId()).isPresent()){
            log.warn("Спроба додати дублікат: Факультет з ID='{}' вже існує", faculty.getId());
            throw new IdAlreadyPresentException("Факультет", faculty.getId());
        }
        validateNotEmpty(faculty.getName(), "Назва факультету");
        validateNotEmpty(faculty.getShortName(), "Скорочена назва факультету");
        validateNotEmpty(faculty.getPhoneNumber(), "Номер телефону факультету");
        String dean = faculty.getDean().getPIB();
        if(dean != null && !dean.trim().isEmpty()) {
            Teacher realT = teacherRepository.findByName(dean)
                    .orElseThrow(() -> new NotFoundNameException("Декана", dean));
            faculty.setDean(realT);
        }
        facultyRepository.add(faculty);
        log.info("Створено новий факультет: ID={}, Назва={}", faculty.getId(), faculty.getName());
    }

    public boolean deleteFaculty(String id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.delete(faculty.get());
            log.info("Факультет з ID='{}' успішно видалено", id);
            return true;
        }
        log.warn("Спроба видалення: Факультет з ID='{}' не знайдено", id);
        return false;
    }

    public boolean editFaculty(String id, String newDeanName, String newPhoneNumber) {
        Optional<Faculty> oFaculty = facultyRepository.findById(id);
        if(oFaculty.isEmpty()) {
            log.warn("Спроба редагування: Факультет з ID='{}' не знайдено", id);
            return false;
        }
        Faculty faculty = oFaculty.get();
        if (newPhoneNumber != null && !newPhoneNumber.trim().isEmpty()) {
            faculty.setPhoneNumber(newPhoneNumber);
        }
        if (newDeanName != null && !newDeanName.trim().isEmpty()) {
            Teacher real = teacherRepository.findByName(newDeanName)
                    .orElseThrow(() -> new NotFoundNameException("Декана", newDeanName));
            faculty.setDean(real);
        }
        log.info("Дані факультету з ID='{}' успішно оновлено", id);
        return true;
    }
}