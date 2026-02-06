package service;

import domain.Faculty;
import repository.FacultyRepository;

public class FacultyService {

    private static final FacultyRepository facultyRepository = new FacultyRepository();

    public void addFaculty(Faculty faculty) {
        if(faculty == null) {
            throw new IllegalArgumentException("Faculty cannot be null");
        }
        if(faculty.getName() == null || faculty.getShortName() == null || faculty.getDean() == null || faculty.getPhoneNumber() == null) {
            throw new IllegalArgumentException("Faculty fields cannot be null");
        }
        facultyRepository.save(faculty);
    }

    public void deleteFaculty(String name) {
        facultyRepository.deleteByName(name);
    }

    public void showAllFaculties() {
        Faculty[] faculties = facultyRepository.findAll();
        for (Faculty faculty : faculties) {
            System.out.println(faculty);
        }
    }

    public static Faculty findFacultyByName(String name) {
       if(name == null) {
           throw new IllegalArgumentException("Name cannot be null");
       }
        return facultyRepository.findByName(name);
    }

    public static Faculty findFacultyById(String id) {
        if(id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        return facultyRepository.findById(id);
    }

    public boolean editFaculty(String id, String newDean) {
        Faculty faculty = facultyRepository.findById(id);
        if (faculty != null) {
            faculty.setDean(newDean);
            return true;
        }
        return false;
    }
}
