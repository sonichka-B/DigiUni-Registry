package service;

import domain.Department;
import domain.Faculty;
import domain.Teacher;
import exceptions.NotFoundIDException;
import repository.FacultyRepository;
import repository.TeacherRepository;

import java.util.Optional;

import static validation.ValidNotEmptyBlankForService.validateNotEmpty;

public class FacultyCRUDService {
    private static final FacultyRepository facultyRepository = new FacultyRepository();
    private static final TeacherRepository teacherRepository = new TeacherRepository();

    public void addFaculty(Faculty faculty) {
        if(faculty == null) {
            throw new IllegalArgumentException("Факультет не может быть null");
        }
        validateNotEmpty(faculty.getId(), "ID факультету");
        validateNotEmpty(faculty.getName(), "Назва факультету");
        validateNotEmpty(faculty.getShortName(), "Скорочена назва факультету");
        validateNotEmpty(faculty.getDean(), "Декан факультету");
        validateNotEmpty(faculty.getPhoneNumber(), "Номер телефону факультету");
        teacherRepository.findById(faculty.getDean())
                .orElseThrow(() -> new NotFoundIDException("Вчитель", faculty.getDean()));
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

    public boolean editFaculty(String id, String newDeanId, String newPhoneNumber) {
        Optional<Faculty> oFaculty = facultyRepository.findById(id);
        if(oFaculty.isEmpty()) {
            return false;
        }
        Faculty faculty = oFaculty.get();
        if (newPhoneNumber != null && !newPhoneNumber.trim().isEmpty()) {
            faculty.setPhoneNumber(newPhoneNumber);
        }
        if (newDeanId != null && !newDeanId.trim().isEmpty()) {
            teacherRepository.findById(newDeanId)
                    .orElseThrow(() -> new NotFoundIDException("Завідувач", newDeanId));
            faculty.setDean(newDeanId);
        }
        return true;
    }
}
