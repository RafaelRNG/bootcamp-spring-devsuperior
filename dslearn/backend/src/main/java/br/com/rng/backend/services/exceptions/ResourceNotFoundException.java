package br.com.rng.backend.services.exceptions;

public class ResourceNotFoundException  extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
