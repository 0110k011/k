package com.api.k.exceptions;

import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException{
    public TransactionNotFoundException(UUID id) {
        super("Transaction id " + id + " not found!");
    }

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
