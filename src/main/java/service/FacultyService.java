package service;

import domain.Faculty;
import domain.Teacher;
import repository.FacultyRepository;

public class FacultyService {
    private final FacultyCRUDService facultyCRUDService;
    private final FacultySearchService facultySearchService;

    public FacultyService() {
        this.facultyCRUDService = new FacultyCRUDService();
        this.facultySearchService = new FacultySearchService();

        FacultyRepository facultyRepository = new FacultyRepository();
        this.facultySearchService.setFacultyRepository(facultyRepository);
    }

    public FacultyCRUDService crud() {
        return facultyCRUDService;
    }

    public FacultySearchService search() {
        return facultySearchService;
    }
}
