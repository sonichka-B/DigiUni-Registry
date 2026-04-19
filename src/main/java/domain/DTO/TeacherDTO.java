package domain.DTO;

import domain.Department;

public record TeacherDTO(String id, String pib, DepartmentDTO department, String position, String email) {
}