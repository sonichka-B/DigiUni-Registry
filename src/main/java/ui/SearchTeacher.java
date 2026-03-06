package ui;

import domain.Teacher;
import service.TeacherService;

public class SearchTeacher {
    private TeacherService teacherService;
    private Validation validation = new Validation();

    public SearchTeacher(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    public void searchTeacherByName() {
        System.out.println("--- ПОШУК ВИКЛАДАЧА ---");
        System.out.println("Введіть ІМ'Я ПРІЗВИЩЕ ПО-БАТЬКОВІ:");
        String fullName = validation.readNotEmptyString("(через пробіл)");
        String[] parts = fullName.trim().split("\\s+");
        if (parts.length < 3) {
            System.out.println("Помилка вводу");
            return;
        }
        Teacher found = teacherService.findTeacherByFullName(parts[0], parts[1], parts[2]);
        if (found== null) {
            System.out.println("Викладача не знайдено");
        } else {
            System.out.println("Знайдено: ");
            System.out.println(found);
        }
    }
}
