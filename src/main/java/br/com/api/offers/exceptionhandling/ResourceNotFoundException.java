package br.com.api.offers.exceptionhandling;


public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException() {
		super();
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}