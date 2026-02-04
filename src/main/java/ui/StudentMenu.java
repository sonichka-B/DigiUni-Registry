package ui;

public class StudentMenu extends BaseMenu{
    @Override
    protected void printTitle() {
        System.out.println("--- Студенти ---");
    }

    @Override
    protected void printOptions() {
        System.out.println("1. Скористатися пошуком, щоб знайти студента");
        System.out.println("2. Вивести всіх студентів певного курсу");
        //Сонічка
        System.out.println("3. Додати студента");
        System.out.println("4. Редагувати інформацію про студента");
        System.out.println("5. Видалити студента");
        System.out.println("6. Перевести в іншу групу студента");
        System.out.println("7. Перевести на наступний курс");
        System.out.println("0. Повернутися назад");
    }

    @Override
    protected int getMaxOption() {
        return 7;
    }

    @Override
    protected void handleChoice(int choice) {
    switch (choice) {
        case 1:
            showSearchStudentMenu();
            break;
        case 2:
            validation.waitZeroToExit();
            break;
        case 3:
            AddchooseDepartmentStudent();
            break;
        case 4:
            validation.waitZeroToExit();
            break;
        case 5:
            deleteChooseDepartmentStudent();
            break;
        case 6:
            validation.waitZeroToExit();
            break;
        case 7:
            validation.waitZeroToExit();
            break;
        }
    validation.waitZeroToExit();
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
    private void AddchooseDepartmentStudent() {
        System.out.println("--- ОБЕРІТЬ КАФЕДРУ ДО ЯКОЇ БАЖАЄТЕ ДОДАТИ ---");
        //тут має бути список кафедр або щось таке цикл
        System.out.println("0. Повернутися назад");
        int choice = validation.readInt("Оберіть кафедру: ", 0, 3);
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
                System.out.println("Введіть коректне число від !!!!!!!! до !!!!!!!!");
                validation.waitZeroToExit();
                break;
        }
    }
        private void deleteChooseDepartmentStudent() {
            System.out.println("--- ОБЕРІТЬ КАФЕДРУ З ЯКОЇ БАЖАЄТЕ ВИДАЛИТИ ---");
            //ТОЙ САМИЙ СПИСОК КАФЕДР
            System.out.println("0. Повернутися назад");
            int choice = validation.readInt("Оберіть кафедру: ", 0, 3);
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
                    System.out.println("Введіть коректне число від !!!!!!!! до !!!!!!!!");
                    validation.waitZeroToExit();
                    break;
            }
        }
    }

