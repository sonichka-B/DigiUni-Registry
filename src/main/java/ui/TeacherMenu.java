package ui;

public class TeacherMenu {
    private Validation validation=new Validation();

    public void showTeacherMenu() {
        while (true) {
            System.out.println("--- Викладачі ---");
            System.out.println("1. Скористатися пошуком, щоб знайти викладача за ПІБ");
            //Сонічка
            System.out.println("2. Додати викладача в кафедрі");
            System.out.println("3. Редагувати інформацію про викладача");
            System.out.println("4. Видалити викладача з кафедри");
            System.out.println("0. Повернутися назад");

            int choiceForStudent = validation.readInt("Оберіть дію",0,7);
            switch (choiceForStudent) {
                case 1:
                    validation.waitZeroToExit();
                    break;
                case 2:
                    validation.waitZeroToExit();
                    break;
                case 3:
                    validation.waitZeroToExit();
                    break;
                case 4:
                    validation.waitZeroToExit();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Введіть коректне число від 0 до 4");
                    validation.waitZeroToExit();
                    break;
            }
        }

        }
    }

