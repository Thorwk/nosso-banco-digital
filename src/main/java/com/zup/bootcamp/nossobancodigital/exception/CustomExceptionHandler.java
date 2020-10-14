package com.zup.bootcamp.nossobancodigital.exception;

import com.zup.bootcamp.nossobancodigital.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.management.InvalidAttributeValueException;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.xml.bind.ValidationException;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.nio.file.FileSystemException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ApiErrorResponse>> handleMethodArgumentNotValid(MethodArgumentNotValidException e){
        List<ApiErrorResponse> errors = new ArrayList<>();
        e.getBindingResult().getFieldErrors().forEach(er -> {
            ApiErrorResponse error = new ApiErrorResponse();
            error.setField(er.getField());
            error.setMessage(er.getDefaultMessage());
            errors.add(error);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<ApiErrorResponse> handleDateTimeException(DateTimeException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("Data de nascimento");
        error.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityExistsException(EntityExistsException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("Entidade");
        error.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(FileSystemException.class)
    public ResponseEntity<ApiErrorResponse> handleFileSystemException(FileSystemException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("File");
        error.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleException(FileNotFoundException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("File");
        error.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(NoSuchElementException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("Entidade");
        error.setMessage("Cliente não cadastrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiErrorResponse> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("File");
        error.setMessage("Tamanho do arquivo excede o limite máximo de upload (5MB)");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<ApiErrorResponse> handleMalformedURLException(MalformedURLException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("File");
        error.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("step");
        error.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(ValidationException e){
        ApiErrorResponse error = new ApiErrorResponse();
        error.setField("token");
        error.setMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

}
