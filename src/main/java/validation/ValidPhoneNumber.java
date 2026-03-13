package validation;

import exceptions.IncorrectDataException;
public class ValidPhoneNumber {

    public static void isValidPhoneNumber(String phoneNumber) {
            if (phoneNumber == null || phoneNumber.isEmpty()) {
                throw new IncorrectDataException("Номер телефону не може бути null або пустим");
            }
            String regex = "^\\+380[0-9]{9}$"; //^початок зчитування,//захист, +380-обовязково на початку, [0-9]символи які можна використовувати, {9}кількість символів
            if (!phoneNumber.matches(regex)) {
                throw new IncorrectDataException("Невірний формат номера телефону. Номер повинен починатися з +380 і містити 9 цифр після цього");
            }
        }
    }

