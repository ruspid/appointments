package com.org.appointments.mvc;

import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
class CentralExceptionControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorMessage> handleNotFoundFilms(EntityNotFoundException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }


    @AllArgsConstructor
    class ErrorMessage {
        private String message;
    }

}
