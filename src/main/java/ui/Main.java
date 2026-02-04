package ui;
public class Main {
    public static void main(String[] args) {
        StudentMenu studentMenu = new StudentMenu();
        TeacherMenu teacherMenu = new TeacherMenu();
        MainMenu mainMenu = new MainMenu(studentMenu, teacherMenu);
        mainMenu.showMenu();
    }
}
