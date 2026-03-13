package validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidLocalDate {
    public LocalDate readLocalDate(String message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        while (true) {
            String input = new Validation().readNotEmptyString(message+" (формат: ДД.ММ.РРРР)");
            try {
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Невірний формат дати. Введіть дату у форматі ДД.ММ.РРРР");
            }
        }
    }
}
