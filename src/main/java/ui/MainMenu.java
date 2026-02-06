package ui;

import domain.Faculty;

//меню факультет кафедри доробити
public class MainMenu extends BaseMenu{
    private StudentMenu studentMenu;
    private TeacherMenu teacherMenu;
    private DepartmentMenu departmentMenu;
    private FacultyMenu facultyMenu;
    public MainMenu(StudentMenu studentMenu, TeacherMenu teacherMenu, DepartmentMenu departmentMenu, FacultyMenu facultyMenu) {
        this.teacherMenu = teacherMenu;
        this.studentMenu = studentMenu;
        this.departmentMenu = departmentMenu;
        this.facultyMenu = facultyMenu;
    }

    @Override
    protected void printTitle() {
        System.out.println("--- Система обліку НаУКМА ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("МЕНЮ:");
        System.out.println("1. Інформація про університет");
        System.out.println("2. Інформація про факультети");
        System.out.println("3. Інформація про кафедри");
        System.out.println("4. Інформація про студентів");
        System.out.println("5. Інформація про викладачів");
        System.out.println("0. Вихід");
    }

    @Override
    protected int getMaxOption() {
        return 5;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                infoAboutUniversity();
                validation.waitZeroToExit();
                break;
            case 2:
                facultyMenu.showMenu();
                break;
            case 3:
                departmentMenu.showMenu();
                break;
            case 4:
                studentMenu.showMenu();
                break;
            case 5:
                teacherMenu.showMenu();
                break;
        }
    }

    private void infoAboutUniversity() {
        System.out.println("--------------------------------------------------");
        System.out.println("           ІНФОРМАЦІЯ ПРО УНІВЕРСИТЕТ             ");
        System.out.println("--------------------------------------------------");
        System.out.println("Повна назва:     Національний університет «Києво-Могилянська академія»");
        System.out.println("Скорочена назва: НаУКМА");
        System.out.println("Місто:           Київ");
        System.out.println("Адреса:          вул. Григорія Сковороди, 2");
        System.out.println("--------------------------------------------------");
    }

}
