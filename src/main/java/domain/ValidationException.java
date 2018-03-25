package domain;

/**
 * Exception thrown when User object fails internal state validation.

 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValidationException(String message) {
        super(message);
    }

}
