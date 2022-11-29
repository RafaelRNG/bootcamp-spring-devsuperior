package br.com.rng.client.services.exceptions;

public class ObjectNotFound extends RuntimeException {

    public ObjectNotFound(String message) {
        super(message);
    }
}