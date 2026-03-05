package service;

import domain.Faculty;
import repository.FacultyRepository;

public class FacultySearchService {
    private static final FacultyRepository facultyRepository = new FacultyRepository();

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
