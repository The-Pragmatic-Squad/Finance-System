package io.github.thepragmaticsquad.fs.exception;

public class ResourceConflictException extends RuntimeException{
    public ResourceConflictException(String message) {
        super(message);
    }
    public ResourceConflictException() {
        super("Resource already exist!");
    }
}
