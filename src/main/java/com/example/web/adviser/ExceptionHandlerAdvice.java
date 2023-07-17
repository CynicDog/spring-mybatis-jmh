package com.example.web.adviser;

import org.jboss.logging.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private Logger logger = Logger.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler({ Exception.class })
    public String handleException(Exception e) {

        logger.error(e.getMessage(), e);

        return "error/unknown";
    }

    @ExceptionHandler({ RuntimeException.class })
    public String handleException(RuntimeException e) {

        logger.error(e.getMessage(), e);

        return "error/server";
    }

    @ExceptionHandler({ DataAccessException.class })
    public String handleException(DataAccessException e) {

        logger.error(e.getMessage(), e);

        return "error/persistence";
    }

    // `AccessDeniedException` is to be processed at the level of Spring Security, not of Spring MVC
    @ExceptionHandler(AccessDeniedException.class)
    public String handleException(AccessDeniedException e) {
        throw e;
    }
}
