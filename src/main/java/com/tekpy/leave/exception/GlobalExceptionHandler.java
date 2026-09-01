package com.tekpy.leave.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger =
            LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public String handleException(
            Exception exception,
            Model model) {

        logger.error(
                "Unexpected application error",
                exception);

        model.addAttribute(
                "error",
                "Something went wrong. Please try again.");

        return "error";
    }
}
