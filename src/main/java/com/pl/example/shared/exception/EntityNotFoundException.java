package com.pl.example.shared.exception;

public abstract class EntityNotFoundException extends RuntimeException {
    protected EntityNotFoundException(String entityName, Object id) {
        super(entityName + " not found with id: " + id);
    }

    protected EntityNotFoundException(String entityName, Object id, String context) {
        super(entityName + " not found with id: " + id + (context != null ? " " + context : ""));
    }
}