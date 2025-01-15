package com.api.k.infra;

import com.api.k.exceptions.DivergentValuesException;
import com.api.k.exceptions.TransactionNotFoundException;
import com.k.webscraper.exceptions.NFNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TransactionNotFoundException.class)
    private ResponseEntity<RestResponseStructure> transactionNotFoundHandler(TransactionNotFoundException exception) {
        RestResponseStructure responseStructure = new RestResponseStructure(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
    }

    @ExceptionHandler(DivergentValuesException.class)
    private ResponseEntity<RestResponseStructure> divergentValuesHandler(DivergentValuesException exception) {
        RestResponseStructure responseStructure = new RestResponseStructure(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseStructure);
    }

    @ExceptionHandler(NFNotFoundException.class)
    private ResponseEntity<RestResponseStructure> nfNotFoundHandler(NFNotFoundException exception) {
        RestResponseStructure responseStructure = new RestResponseStructure(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseStructure);
    }
}
