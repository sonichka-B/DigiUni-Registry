package service;

import domain.Faculty;
import repository.FacultyRepository;

import java.util.Optional;

public class FacultySearchService {
    private static FacultyRepository facultyRepository = new FacultyRepository();

    public void showAllFaculties() {
        for (Faculty faculty : facultyRepository.findAll()) {
            System.out.println(faculty);
        }
    }

    public void findFacultyById(String id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            System.out.println(faculty);
        } else {
            System.out.println("Факультет з таким id не знайдено");
        }
    }

    public void setFacultyRepository(FacultyRepository repository) {
        this.facultyRepository = repository;
    }
}
