package ui;

import service.TeacherSearchService;
import service.TeacherService;
import validation.Validation;


public class SearchTeacher extends BaseMenu{
    TeacherService teacherService;
//    private TeacherSearchService teacherSearchService;
    private Validation validation = new Validation();

    public SearchTeacher(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    @Override
    protected void printTitle() {
        System.out.println("--- ПОШУК ВИКЛАДАЧА ---");
    }
    @Override
    protected void printOptions() {
        System.out.println("1. За ПІБ");
        System.out.println("2. За ID");
        System.out.println("0. Назад");
    }
    @Override
    protected int getMaxOption() {
        return 2;
    }
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                findTeacherByFullName();
                    break;
            case 2:
                findTeacherById();
                    break;
        }
        validation.waitZeroToExit();
    }

    public void findTeacherByFullName() {
        System.out.println("Введіть ІМ'Я ПРІЗВИЩЕ ПО-БАТЬКОВІ:");
        String fullName = validation.readNotEmptyString("(через пробіл)");
//        teacherSearchService.findTeacherByFullName(fullName);
        teacherService.search().findTeacherByFullName(fullName);
    }
    public void findTeacherById() {
        String id = validation.readNotEmptyString("Введіть ID: ");
//        teacherSearchService.findTeacherByFullName(id);
        teacherService.search().findTeacherById(id);
    }
}
