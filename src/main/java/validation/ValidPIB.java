package validation;

public class ValidPIB {
    Validation valid = new Validation();
    public String validPIB(String prompt) {
        String pib= valid.readNotEmptyString("Введіть ПІБ: ");
        //від випадкових подвійних пробілів
        String[] parts = pib.split("\\s+");
        while(parts.length!=3){
            System.out.println("Помилка: ПІБ має містити три слова (ім'я, прізвище, по батькові). ");
            pib= valid.readNotEmptyString("Введіть ПІБ (через пробіл): ");
            parts = pib.split("\\s+");
        }
        return String.join(" ", parts);
        }
    }

