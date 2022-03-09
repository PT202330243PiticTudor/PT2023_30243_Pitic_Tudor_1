package exceptions;

public class InvalidInputException extends NumberFormatException{
    public InvalidInputException(String message) {
        super(message);
    }
}
