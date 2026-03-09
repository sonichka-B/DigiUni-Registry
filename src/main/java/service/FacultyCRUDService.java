package service;

import domain.Faculty;
import domain.Teacher;
import repository.FacultyRepository;
import repository.TeacherRepository;

public class FacultyCRUDService {
    private static final FacultyRepository facultyRepository = new FacultyRepository();

    public void addFaculty(Faculty faculty) {
        if(faculty == null) {
            throw new IllegalArgumentException("Факультет не може бути null");
        }
        if(faculty.getName() == null || faculty.getShortName() == null || faculty.getDean() == null || faculty.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Назва, коротка назва, декан та номер телефону факультету не можуть бути null");
        }

        facultyRepository.add(faculty);
    }

    public void deleteFaculty(String id) {
        Faculty faculty = facultyRepository.findById(id);
        if (faculty != null) {
            facultyRepository.delete(faculty);
        } else {
            System.out.println("Факультет з таким id не знайдено");
        }
    }

    public boolean editFaculty(String id, String newDean) {
        Faculty faculty = facultyRepository.findById(id);
        Teacher Dean = new TeacherRepository().findByName(newDean);
        if (faculty != null && Dean != null) {
            faculty.setDean(newDean);
            return true;
        }
        return false;
    }
}
