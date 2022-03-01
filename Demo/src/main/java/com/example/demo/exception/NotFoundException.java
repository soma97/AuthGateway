package com.example.demo.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(Class<?> clazz, long id) {
        super(getFormattedMessage(clazz.getSimpleName(), String.valueOf(id)));
    }

    public NotFoundException(Class<?> clazz, String id) {
        super(getFormattedMessage(clazz.getSimpleName(), id));
    }

    private static String getFormattedMessage(String entity, String id) {
        return String.format("%s with id %s not found", entity, id );
    }
}
