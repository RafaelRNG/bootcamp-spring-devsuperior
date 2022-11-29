package br.com.rng.client.resources.exceptions;

import br.com.rng.client.services.exceptions.ObjectNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler
    public ResponseEntity<StandardError> notFoundExceptions(ObjectNotFound objectNotFound) {
        Integer statusCode = HttpStatus.NOT_FOUND.value();
        StandardError standardError = new StandardError("Object not found", objectNotFound.getMessage(), statusCode);

        return ResponseEntity.status(statusCode).body(standardError);
    }
}