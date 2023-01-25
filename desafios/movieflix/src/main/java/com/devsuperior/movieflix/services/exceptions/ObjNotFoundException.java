package com.devsuperior.movieflix.services.exceptions;

public class ObjNotFoundException extends RuntimeException {

    public ObjNotFoundException(String message) {
        super(message);
    }
}