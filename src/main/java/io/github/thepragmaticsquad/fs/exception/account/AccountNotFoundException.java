package io.github.thepragmaticsquad.fs.exception.account;

import io.github.thepragmaticsquad.fs.exception.ResourceNotFoundException;

public class AccountNotFoundException extends ResourceNotFoundException {
    public AccountNotFoundException(String message) {
        super(message);
    }

    public AccountNotFoundException() {
        super("Account not found");
    }

}
