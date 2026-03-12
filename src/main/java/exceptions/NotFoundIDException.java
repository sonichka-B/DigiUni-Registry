package exceptions;

public class NotFoundIDException extends RuntimeException{
    public NotFoundIDException(String name, String id) {
        super(name + " з ID " + id + " не знайдено");
    }
}
