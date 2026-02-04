package ui;

import java.awt.*;

public class TeacherMenu extends BaseMenu {

    @Override
    protected void printTitle() {
        System.out.println("--- Викладачі ---");
    }

    @Override
    protected void printOptions() {

        System.out.println("1. Скористатися пошуком, щоб знайти викладача за ПІБ");
        //Сонічка
        System.out.println("2. Додати викладача в кафедрі");
        System.out.println("3. Редагувати інформацію про викладача");
        System.out.println("4. Видалити викладача з кафедри");
        System.out.println("0. Повернутися назад");
    }

    @Override
    protected int getMaxOption() {
        return 4;
    }

    @Override
    protected void handleChoice(int choice) {
        switch (choice) {
            case 1:
                //Пошук викладача за ПІБ
                break;
            case 2:
                //AddchooseDepartmentTeacher();
                break;
            case 3:
               //
                break;
            case 4:
                //deleteChooseDepartmentTeacher();
                break;
        }
        validation.waitZeroToExit();
    }
}


