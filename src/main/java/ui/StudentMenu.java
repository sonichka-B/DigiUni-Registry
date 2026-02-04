package ui;

public class StudentMenu {
        private Validation validation = new Validation();

        public void showStudentMenu() {
            while (true) {
                System.out.println("--- Студенти ---");
                System.out.println("1. Скористатися пошуком, щоб знайти студента");
                System.out.println("2. Вивести всіх студентів певного курсу");
                //Сонічка
                System.out.println("3. Додати нового студента");
                System.out.println("4. Редагувати інформацію про студента");
                System.out.println("5. Перевести в іншу групу");
                System.out.println("6. Перевести в іншу групу студента");
                System.out.println("7. Перевести на наступний курс");

                System.out.println("0. Повернутися назад");

                int choiceForStudent = validation.readInt("Оберіть дію",0,7);
                switch (choiceForStudent) {
                    case 1:
                        showSearchStudentMenu();
                        validation.waitZeroToExit();
                        break;
                    case 2:
                        validation.waitZeroToExit();
                        break;
                    case 3://Я тут ще не додала в яку кафедру додати/видалити?
                        validation.waitZeroToExit();
                        break;
                    case 4:
                        validation.waitZeroToExit();
                        break;
                    case 5:
                        validation.waitZeroToExit();
                        break;
                    case 6:
                        validation.waitZeroToExit();
                        break;
                    case 7:
                        validation.waitZeroToExit();
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Введіть коректне число від 0 до 7");
                        validation.waitZeroToExit();
                        break;
                }
            }
        }
    private void showSearchStudentMenu() {
        System.out.println("--- ОБЕРІТЬ ТИП ПОШУКУ ---");
        System.out.println("1. За прізвищем");
        System.out.println("2. За курсом");
        System.out.println("3. За групою");
        System.out.println("0. Повернутися назад");
        int choice = validation.readInt("Тип пошуку: " ,0,3);
        switch (choice) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                return;
            default:
                System.out.println("Введіть коректне число від 0 до 3");
                validation.waitZeroToExit();
                break;
        }
    }
}
