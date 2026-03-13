package validation;

import exceptions.IncorrectDataException;

public class ValidEmail {
    public static void isValidEmail(String email) {
        if(email == null || email.isEmpty()) {
            throw new IncorrectDataException("Email не може бути null або пуcтим");
        }
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,6}$";
        if (!email.matches(regex)) {
            throw new IncorrectDataException("Невірний формат. Email повинен містити символ '@'");
        }
    }
}
