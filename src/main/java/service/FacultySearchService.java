package service;

import domain.DTO.FacultyDTO;
import domain.Faculty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.FacultyRepository;

import java.util.Optional;

public class FacultySearchService {
    private static final Logger log = LoggerFactory.getLogger(FacultySearchService.class);
    private FacultyRepository facultyRepository ;
    public boolean existsById(String id) {
        return facultyRepository.findById(id).isPresent();
    }
    public boolean existsByName(String name) {
        return facultyRepository.findByName(name).isPresent();
    }

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
            log.warn("Факультет з таким id не знайдено");
            System.out.println("Факультет з таким id не знайдено");
        }
    }

    public void setFacultyRepository(FacultyRepository repository) {
        this.facultyRepository = repository;
    }
}
