package validation;

import exceptions.IncorrectDataException;

public class ValidNotEmptyBlankForService {
    public static void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IncorrectDataException("Поле '" + fieldName + "' не може бути порожнім");
        }
    }
}
