package ru.hackaton.health_api.exceptions;

public class MyInputException extends RuntimeException {
    public MyInputException() {
    }

    public MyInputException(String message) {
        super(message);
    }
}
