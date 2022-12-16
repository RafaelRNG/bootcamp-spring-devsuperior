package com.devsuperior.bds02.services.exceptions;

public class ObjNotFoundException extends RuntimeException {

    public ObjNotFoundException(String message) {
        super(message);
    }
}