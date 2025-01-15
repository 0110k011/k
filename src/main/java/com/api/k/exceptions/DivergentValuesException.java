package com.api.k.exceptions;

public class DivergentValuesException extends RuntimeException {

    public DivergentValuesException() {
        super("NF and database have different amount values!");
    }

    public DivergentValuesException(String message) {
        super(message);
    }
}
