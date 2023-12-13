package com.bookstime.handler;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.stream.Collectors;
import static com.bookstime.constants.ExceptionsAndLogs.INCORRECT_DRAFT_SIZE_ERROR_MESSAGE;
import static com.bookstime.constants.ExceptionsAndLogs.NOT_BLANK_DRAFT_MESSAGE;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMethodArgumentException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet())
                .contains(NOT_BLANK_DRAFT_MESSAGE) ? NOT_BLANK_DRAFT_MESSAGE : INCORRECT_DRAFT_SIZE_ERROR_MESSAGE;
    }
}
