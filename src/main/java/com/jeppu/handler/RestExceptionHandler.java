package com.jeppu.handler;

import com.jeppu.dto.error.ErrorDetail;
import com.jeppu.dto.error.ValidateError;
import com.jeppu.exception.ResourceNotFoundException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableWebMvc
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Inject
    private MessageSource messageSource;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail("Input validation failed");
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            List<ValidateError> validateErrors = errorDetail.getErrors().get(fieldError.getField());
            if(validateErrors==null){
                validateErrors = new ArrayList<>();
                errorDetail.getErrors().put(fieldError.getField(), validateErrors);
            }
            ValidateError validateError = new ValidateError();
            validateError.setMessage(messageSource.getMessage(fieldError, null));
            validateError.setCode(fieldError.getCode());
            validateErrors.add(validateError);
        }
        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Message Not Readable");
        errorDetail.setDetail(ex.getMessage());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        return handleExceptionInternal(ex, errorDetail, headers, status, request);
    }

    //Custom method when NOT overriding ResponseEntityExceptionHandler
    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<?> handleValidationError(MethodArgumentNotValidException ex, HttpServletRequest request) {
        System.out.println("RestExceptionHandler::handleMethodArgumentNotValid()");

        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail("Input validation failed");
        errorDetail.setTitle("Method Argument NotValid Exception");
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDetail.setDeveloperMessage(ex.getClass().getName());

        String requestPath = (String) request.getAttribute("javax.servlet.error.request_uri");
        if(requestPath==null) {
           requestPath = request.getRequestURI();
        }

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for(FieldError fieldError : fieldErrors){
            List<ValidateError> validateErrors = errorDetail.getErrors().get(fieldError.getField());
            if(validateErrors==null){
                validateErrors = new ArrayList<>();
                errorDetail.getErrors().put(fieldError.getField(), validateErrors);
            }
            ValidateError validateError = new ValidateError();
            validateError.setCode(fieldError.getCode());
            //validateError.setMessage(fieldError.getDefaultMessage());
            validateError.setMessage(messageSource.getMessage(fieldError, null));
            validateErrors.add(validateError);
        }
        return new ResponseEntity<>(errorDetail, null, HttpStatus.BAD_REQUEST);
    }*/

 }
