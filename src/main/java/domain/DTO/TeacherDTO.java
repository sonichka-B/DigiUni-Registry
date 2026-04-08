package domain.DTO;

import domain.Department;

public record TeacherDTO(String id, String pib, Department department, String position, String email) {
}
