package validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ValidName implements UniqueData{
    private Validation validation = new Validation();

    public String nameUni(String message,UniqueData uniqueData) {
        while (true) {
            String input = validation.readNotEmptyString("Введіть назву: ");
            if(uniqueData.dubl(input)) {
                System.out.println("Помилка: така назва вже існує в системі");
            } else{
                return input;
            }
        }
    }
    public String nameMustExist(String message, UniqueData uniqueData) {
        while (true) {
            String input = validation.readNotEmptyString("Введіть назву: ");

            if (uniqueData.dubl(input)) {
                return input;
            } else {
                System.out.println("Помилка: така назва не знайдена в системі.");
            }
        }
    }
    @Override
    public boolean dubl(String name) {
        return false;
    }

}
