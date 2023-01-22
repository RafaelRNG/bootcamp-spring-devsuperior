package br.com.rng.backend.resources.exceptions;

import br.com.rng.backend.services.exceptions.DatabaseException;
import br.com.rng.backend.services.exceptions.ForbiddenException;
import br.com.rng.backend.services.exceptions.ResourceNotFoundException;
import br.com.rng.backend.services.exceptions.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException entityNotFoundException, HttpServletRequest httpServletRequest) {

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
    public ResponseEntity<StandardError> database(DatabaseException databaseException, HttpServletRequest httpServletRequest) {

        Integer status = HttpStatus.BAD_REQUEST.value();

        StandardError standardError = new StandardError();
        standardError.setTimestamp(Instant.now());
        standardError.setStatus(status);
        standardError.setError("Database exception");
        standardError.setMessage(databaseException.getMessage());
        standardError.setPath(httpServletRequest.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException notValidException, HttpServletRequest httpServletRequest) {
        Integer status = HttpStatus.UNPROCESSABLE_ENTITY.value();

        ValidationError validationError = new ValidationError();
        validationError.setTimestamp(Instant.now());
        validationError.setStatus(status);
        validationError.setError("Validation exception");
        validationError.setMessage("Fields Error");
        validationError.setPath(httpServletRequest.getRequestURI());

        for(FieldError fieldError: notValidException.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.status(status).body(validationError);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<OAuthCustomError> forbidden(ForbiddenException forbiddenException, HttpServletRequest httpServletRequest) {

        OAuthCustomError oAuthCustomError = new OAuthCustomError("Forbidden", forbiddenException.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(oAuthCustomError);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<OAuthCustomError> unauthorized(UnauthorizedException unauthorizedException, HttpServletRequest httpServletRequest) {

        OAuthCustomError oAuthCustomError = new OAuthCustomError("unauthorized", unauthorizedException.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(oAuthCustomError);
    }
}