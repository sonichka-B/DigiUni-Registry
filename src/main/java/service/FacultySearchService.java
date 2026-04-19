package service;

import domain.DTO.FacultyDTO;
import domain.Faculty;
import repository.FacultyRepository;

import java.util.Optional;

public class FacultySearchService {
    private FacultyRepository facultyRepository ;

    public void showAllFaculties() {
        facultyRepository.findAll().stream()
                .map(faculty -> new FacultyDTO(
                        faculty.getId(),
                        faculty.getName(),
                        faculty.getShortName(),
                        faculty.getDean() != null ? faculty.getDean().getPIB() : "не призначено",
                        faculty.getPhoneNumber()
                ))
                .forEach(System.out::println);
    }


    public void findFacultyById(String id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent()) {
            System.out.println(faculty.get());
        } else {
            System.out.println("Факультет з таким id не знайдено");
        }
    }

    public void setFacultyRepository(FacultyRepository repository) {
        this.facultyRepository = repository;
    }
}
