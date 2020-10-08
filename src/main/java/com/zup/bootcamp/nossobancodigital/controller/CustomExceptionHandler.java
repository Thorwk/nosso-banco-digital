package com.zup.bootcamp.nossobancodigital.controller;

import com.zup.bootcamp.nossobancodigital.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ApiErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<ApiErrorResponse> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(er -> {
            ApiErrorResponse error = new ApiErrorResponse();
            error.setField(er.getField());
            error.setMessage(er.getDefaultMessage());
            errors.add(error);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({Exception.class})
    public ApiErrorResponse handleException(Exception e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("email");
        error.setMessage(e.getMessage());

        return error;
    }

}
