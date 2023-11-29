package challenges.exceptions;

public class NonExistentAdressException extends RuntimeException {
    private String message;

    public NonExistentAdressException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
