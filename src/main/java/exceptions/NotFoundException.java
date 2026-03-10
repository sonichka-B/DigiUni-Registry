package exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String name, String id) {
        super(name + " з ID " + id + " не знайдено");
    }
}
