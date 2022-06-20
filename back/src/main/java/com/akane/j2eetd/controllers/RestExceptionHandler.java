package com.akane.j2eetd.controllers;

import com.akane.j2eetd.dto.ValidationError;
import com.akane.j2eetd.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice(annotations = RestController.class) public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        ValidationError error = new ValidationError();
        //error= ex.getBindingResult().getFieldError();

// Utiliser ex.getBindingResult().getFieldErrors() pour
// renseigner les erreurs dans l’objet ValidationError
        List<FieldError> test = ex.getBindingResult().getFieldErrors();
        error.setErrorMessage(ex.getMessage());
;
        Map<String,String> errors = new HashMap<>();

        for (FieldError currentError: test
             ) {
            errors.put(currentError.getField(),currentError.getDefaultMessage());
        }


        error.setFieldErrors(errors);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

}
