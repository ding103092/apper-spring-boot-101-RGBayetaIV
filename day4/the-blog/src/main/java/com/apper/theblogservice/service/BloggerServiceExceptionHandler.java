package com.apper.theblogservice.service;

import com.apper.theblogservice.exception.BlogNotFoundException;
import com.apper.theblogservice.exception.BloggerNotFoundException;
import com.apper.theblogservice.exception.EmailAlreadyRegisteredException;
import com.apper.theblogservice.exception.InvalidBloggerIdException;
import com.apper.theblogservice.payload.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;

@ControllerAdvice
public class BloggerServiceExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(Objects.requireNonNull(ex.getBindingResult().getFieldError()).getDefaultMessage());
        return errorResponse;
    }

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ErrorResponse handleEmailAlreadyRegisteredException(EmailAlreadyRegisteredException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(InvalidBloggerIdException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ErrorResponse handleInvalidBloggerIdException(InvalidBloggerIdException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(BloggerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleBloggerNotFoundException(BloggerNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(BlogNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleBlogNotFoundException(BlogNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }
}
