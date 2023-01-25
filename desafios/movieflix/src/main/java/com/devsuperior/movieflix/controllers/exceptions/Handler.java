package com.devsuperior.movieflix.controllers.exceptions;

import com.devsuperior.movieflix.services.exceptions.ObjNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(ObjNotFoundException.class)
    public ResponseEntity<FormatException> notFound(ObjNotFoundException obj) {

        Integer httpStatus = HttpStatus.NOT_FOUND.value();

        FormatException format = new FormatException(httpStatus, obj.getMessage());

        return ResponseEntity.status(httpStatus).body(format);
    }
}