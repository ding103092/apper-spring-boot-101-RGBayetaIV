package com.apper.exception;

import com.apper.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ErrorResponse handleUsernameAlreadyRegisteredException(UsernameAlreadyRegisteredException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ResponseBody
    public ErrorResponse handleAccountNotFoundException(AccountNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }

    @ExceptionHandler(NoRegisteredAccountsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ResponseBody
    public ErrorResponse handleNoRegisteredAccountsException(NoRegisteredAccountsException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(ex.getClass().getSimpleName());
        errorResponse.setMessage(ex.getMessage());
        return errorResponse;
    }
}
