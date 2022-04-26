package com.jeppu.handler;

import com.jeppu.dto.error.ErrorDetail;
import com.jeppu.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@EnableWebMvc
@ControllerAdvice
//@Component
public class RestExceptionHandler  extends ResponseEntityExceptionHandler{
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe, HttpServletRequest request) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetail(rnfe.getMessage());
        errorDetail.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetail.setTimestamp(new Date().getTime());
        errorDetail.setTitle("Resource Not Found");
        errorDetail.setDeveloperMessage(rnfe.getClass().getName());

        return new ResponseEntity<>(errorDetail, null, HttpStatus.NOT_FOUND);
    }
}
