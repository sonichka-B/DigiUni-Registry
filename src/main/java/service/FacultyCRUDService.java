package service;

import domain.Department;
import domain.Faculty;
import domain.Teacher;
import exceptions.IncorrectDataException;
import exceptions.NotFoundIDException;
import exceptions.NotFoundNameException;
import repository.FacultyRepository;
import repository.TeacherRepository;

import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;

public class FacultyCRUDService {
    private static final FacultyRepository facultyRepository = new FacultyRepository();
    private static final TeacherRepository teacherRepository = new TeacherRepository();
public FacultyRepository getRepository() {
        return facultyRepository;
    }
    public void addFaculty(Faculty faculty) {
        if(faculty == null) {
            throw new IncorrectDataException("Факультет не может быть null");
        }
        validateNotEmpty(faculty.getId(), "ID факультету");
        validateNotEmpty(faculty.getName(), "Назва факультету");
        validateNotEmpty(faculty.getShortName(), "Скорочена назва факультету");
        validateNotEmpty(faculty.getPhoneNumber(), "Номер телефону факультету");
       /* String dean=faculty.getDean();
        if(dean!=null && !dean.trim().isEmpty()) {
            teacherRepository.findByName(faculty.getDean())
                    .orElseThrow(() -> new NotFoundNameException("Декана", dean));
        }*/
//String id, String name, String shortName, String dean, String phoneNumber
        facultyRepository.add(faculty);
    }

    public boolean deleteFaculty(String id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            facultyRepository.delete(faculty.get());
            return true;
        }
        return false;
    }

    public boolean editFaculty(String id, String newDeanName, String newPhoneNumber) {
        Optional<Faculty> oFaculty = facultyRepository.findById(id);
        if(oFaculty.isEmpty()) {
            return false;
        }
        Faculty faculty = oFaculty.get();
        if (newPhoneNumber != null && !newPhoneNumber.trim().isEmpty()) {
            faculty.setPhoneNumber(newPhoneNumber);
        }
        if (newDeanName != null && !newDeanName.trim().isEmpty()) {
            teacherRepository.findByName(newDeanName)
                    .orElseThrow(() -> new NotFoundNameException("Декана", newDeanName));
            faculty.setDean(newDeanName);
        }
        return true;
    }
}
