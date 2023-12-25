package org.unibl.etf.ip.backend.advices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.unibl.etf.ip.backend.exceptions.MethodNotAllowedException;
import org.unibl.etf.ip.backend.exceptions.ModifiedUserNameException;
import org.unibl.etf.ip.backend.exceptions.NotFoundException;
import org.unibl.etf.ip.backend.exceptions.UserNotActiveException;

import java.io.IOException;

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
}
