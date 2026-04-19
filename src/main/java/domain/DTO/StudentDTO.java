package domain.DTO;

public record StudentDTO(String id, String pib, int course, DepartmentDTO department, int group, String email) {
}
