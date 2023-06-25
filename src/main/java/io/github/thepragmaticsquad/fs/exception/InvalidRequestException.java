package io.github.thepragmaticsquad.fs.exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message) {
        super(message);
    }
    public InvalidRequestException() {
        super("Invalid request!");
    }
}
