package br.com.rng.dscatalog.resources.exceptions;

import br.com.rng.dscatalog.services.exceptions.DatabaseException;
import br.com.rng.dscatalog.services.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException entityNotFoundException,
                                                        HttpServletRequest httpServletRequest) {

        Integer status = HttpStatus.NOT_FOUND.value();

        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status);
        standardError.setError("Resource not found");
        standardError.setMessage(entityNotFoundException.getMessage());
        standardError.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databaseException(DatabaseException databaseException, HttpServletRequest httpServletRequest) {

        Integer status = HttpStatus.BAD_REQUEST.value();

        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status);
        standardError.setError("Database exception");
        standardError.setMessage(databaseException.getMessage());
        standardError.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }

}