package io.github.thepragmaticsquad.fs.exception.transaction;

import io.github.thepragmaticsquad.fs.exception.ResourceNotFoundException;

import java.util.NoSuchElementException;

public class TransactionNotFoundException extends NoSuchElementException {
    public TransactionNotFoundException() {
        super("Transaction Not Found");
    }
    public TransactionNotFoundException(String message) {
        super(message);
    }
}
