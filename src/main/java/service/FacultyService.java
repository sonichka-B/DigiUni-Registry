package service;

import domain.Faculty;
import domain.Teacher;
import repository.FacultyRepository;
import repository.TeacherRepository;

public class FacultyService {
    private final FacultyCRUDService facultyCRUDService;
    private final FacultySearchService facultySearchService;

    public FacultyService() {
        this.facultyCRUDService = new FacultyCRUDService();
        this.facultySearchService = new FacultySearchService();

        FacultyRepository facultyRepository = this.facultyCRUDService.getRepository();
        this.facultySearchService.setFacultyRepository(facultyRepository);
    }

    public FacultyCRUDService crud() {
        return facultyCRUDService;
    }

    public FacultySearchService search() {
        return facultySearchService;
    }

    public void setSharedTeacherRepository(TeacherRepository teacherRepo) {
        this.facultyCRUDService.setTeacherRepository(teacherRepo);
    }
}
