package com.devsuperior.bds04.controllers.exceptions;

import java.util.ArrayList;
import java.util.List;

public class FieldMessage {

    private List<MessageError> errors = new ArrayList<>();

    public void addError(String fieldName, String message) {
        this.errors.add(new MessageError(fieldName, message));
    }

    public List<MessageError> getErrors() {
        return errors;
    }
}