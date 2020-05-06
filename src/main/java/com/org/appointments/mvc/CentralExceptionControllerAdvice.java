package com.org.appointments.mvc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(Ordered.LOWEST_PRECEDENCE)
@ControllerAdvice
class CentralExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorMessage> handleRuntimeException(RuntimeException e) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
    }


    @AllArgsConstructor
    @Getter
    @Setter
    class ErrorMessage {
        private String message;
    }

}
