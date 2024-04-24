package com.BookManageMent.Book.Exceptions;

import com.BookManageMent.Book.Utils.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFound(){


        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(400);
        response.setErroDescription("Resource Not found");

        return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String,String>errorDetails = new HashMap<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fe : fieldErrors){

            errorDetails.put(fe.getField(),fe.getDefaultMessage());
        }


        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }



}
