package service;

import domain.Faculty;
import domain.Teacher;
import repository.FacultyRepository;

public class FacultyService {

    private static final FacultyRepository facultyRepository = new FacultyRepository();

    public void addFaculty(Faculty faculty) {
        if(faculty == null) {
            throw new IllegalArgumentException("Факультет не може бути null");
        }
        if(faculty.getName() == null || faculty.getShortName() == null || faculty.getDean() == null || faculty.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Назва, коротка назва, декан та номер телефону факультету не можуть бути null");
        }
        if(faculty.getDean(). getFullName() == null) {
            throw new IllegalArgumentException("ПІБ декана не може бути null");
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

    public boolean editFaculty(String id, Teacher newDean) {
        Faculty faculty = facultyRepository.findById(id);
        if (faculty != null && newDean != null && newDean.getFullName() != null) {
            faculty.setDean(newDean);
            return true;
        }
        return false;
    }
    public void showAllFaculties() {
        for (Faculty faculty : facultyRepository.findAll()) {
            System.out.println(faculty);
        }
    }

    public void findFacultyById(String id) {
        Faculty faculty = facultyRepository.findById(id);
        if (faculty != null) {
            System.out.println(faculty);
        } else {
            System.out.println("Факультет з таким id не знайдено");
        }
    }


}
