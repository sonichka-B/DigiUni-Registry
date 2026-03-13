package validation;

import exceptions.IncorrectDataException;

public class ValidNotEmptyBlankForService {
    public static void validateNotEmpty(String userPut, String fieldName) {
        if (userPut == null || userPut.trim().isEmpty()) {
            throw new IncorrectDataException("Поле '" + fieldName + "' не може бути порожнім");
        }
    }
}
