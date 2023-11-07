package exceptions;

public class InvalidCharacterException extends Exception {
    public InvalidCharacterException() {
        super("More than one character passed.");
    }
}
