package service;

import domain.Faculty;
import repository.FacultyRepository;

public class FacultyService {

    private final FacultyRepository facultyRepository = new FacultyRepository();

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

    public Faculty findFacultyByName(String name) {
        return facultyRepository.findByName(name);
    }
}
