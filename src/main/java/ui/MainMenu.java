package ui;

public class MainMenu {
    private Validation validation = new Validation();
    private StudentMenu studentMenu;
    private TeacherMenu teacherMenu;
    public MainMenu(StudentMenu studentMenu, TeacherMenu teacherMenu) {
        this.teacherMenu = teacherMenu;
        this.studentMenu = studentMenu;
    }
    public void showMainMenu() {
        while (true) {
            System.out.println("--- Система обліку НаУКМА ---");
            System.out.println("МЕНЮ:");
            System.out.println("1. Інформація про університет");
            System.out.println("2. Інформація про факультети");
            System.out.println("3. Інформація про кафедри");
            System.out.println("4. Інформація про студентів");
            System.out.println("5. Інформація про викладачів");
            int choice = validation.readInt("Оберіть дію (0-5)",1,5);
            switch (choice) {
                case 1:
                    infoAboutUniversity();
                    validation.waitZeroToExit();
                    break;
                case 2:
                    System.out.println("МММММЕЕЕННННЮЮЮ ФАКУУУУЛЬЬЬЬТЕТТТІВ");
                    validation.waitZeroToExit();
                    break;
                case 3:
                    System.out.println("МММММЕЕЕННННЮЮЮ КАфедри");
                    validation.waitZeroToExit();
                    break;
                case 4:
                    studentMenu.showStudentMenu();
                    break;
                case 5:
                    teacherMenu.showTeacherMenu();
                    break;
                default:
                    System.out.println("Введіть коректне число від 1 до 5");
                    validation.waitZeroToExit();
            }
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
