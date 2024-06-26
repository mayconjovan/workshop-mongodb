package dev.maycon.workshopmongo.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(final String message) {
        super(message);
    }
}
