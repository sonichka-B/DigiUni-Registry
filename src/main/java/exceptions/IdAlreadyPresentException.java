package exceptions;

public class IdAlreadyPresentException extends RuntimeException{
    public IdAlreadyPresentException(String name, String id) {
        super("Помилка: "+name+ " з ID:"+ id + " вже існує в системі.");
    }
}
