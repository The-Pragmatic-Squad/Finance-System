package io.github.thepragmaticsquad.fs.exception.account;

import io.github.thepragmaticsquad.fs.exception.InvalidRequestException;

public class AccountAlreadyExistException extends InvalidRequestException {
    public AccountAlreadyExistException(String message) {
        super(message);
    }

    public AccountAlreadyExistException() {
        super("Account already exist");
    }
}
