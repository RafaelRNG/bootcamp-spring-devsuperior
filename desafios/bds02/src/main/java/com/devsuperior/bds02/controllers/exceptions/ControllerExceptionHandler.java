package com.devsuperior.bds02.controllers.exceptions;

import com.devsuperior.bds02.services.exceptions.ObjNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ ObjNotFoundException.class, EmptyResultDataAccessException.class })
    public ResponseEntity<Format> notFound(ObjNotFoundException objNotFoundException) {
        Format format = new Format("Object not found!", objNotFoundException.getMessage(), Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(format);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Format> badRequest(DataIntegrityViolationException violationException) {
        Format format = new Format("Integrity violation", violationException.getMessage(), Instant.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(format);
    }
}