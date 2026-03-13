package exceptions;

public class NotFoundNameException extends RuntimeException {
    public NotFoundNameException(String entityType,String name) {
        super(entityType+" з ПІБ"+name+ " не знайдено");
    }
}
