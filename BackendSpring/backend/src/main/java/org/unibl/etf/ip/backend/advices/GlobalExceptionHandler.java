package org.unibl.etf.ip.backend.advices;

import com.rometools.rome.io.FeedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.unibl.etf.ip.backend.exceptions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound() {
        logger.error("Resource not found!");
    }

    @ExceptionHandler(IOException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleIOException() {
        logger.error("Problem with files!");
    }

    @ExceptionHandler(ModifiedUserNameException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleModifiedUserNameException() {
        logger.error("Forbidden username change!");
    }

    @ExceptionHandler(UserNotActiveException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleUserNotActiveException() {
        logger.error("User not active, can not change profile!");
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public void handleMethodNotAllowedException() {
        logger.error("Action not allowed!");
    }

    @ExceptionHandler(FeedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleFeedException() {
        logger.error("Feed error!");
    }

    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleNotEnoughMoneyException() {
        logger.error("Not enough money!");
    }

    @ExceptionHandler(InvalidUsernameException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleInvalidUsernameException() {
        logger.error("Incorrect username!");
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleUsernameNotFoundException() {
        logger.error("Incorrect username!");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public void handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        //Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            //errors.put(fieldName, errorMessage);
            logger.error("Wrong input for field with name " + fieldName + " with message " + errorMessage);
        });
        //return errors;
    }
}
