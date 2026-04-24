package validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ValidID implements UniqueData {
    private Validation validation = new Validation();

    public String idUni(String message,UniqueData uniqueData) {
        while (true) {
            String input = validation.readNotEmptyString("Введіть ID: ");
            if(uniqueData.dubl(input)) {
                System.out.println("Помилка: Такий ID вже існує в системі");
            } else{
                return input;
            }
        }
    }
    public String idMustExist(String message, UniqueData uniqueData) {
        while (true) {
                String input = validation.readNotEmptyString("Введіть ID: ");

            if (uniqueData.dubl(input)) {
                return input;
            } else {
                System.out.println("Помилка: таке ID не знайдено.");
            }
        }
    }
    @Override
    public boolean dubl(String id) {
        return false;
    }
}
