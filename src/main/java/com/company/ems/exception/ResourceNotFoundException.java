package com.company.ems.exception;

public final class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String id, String message) {
        super(message.concat(" with id = ").concat(id));
    }
}