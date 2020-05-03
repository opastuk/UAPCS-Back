package ru.hackaton.health_api.exceptions;

public class MyAlreadyCreatedException extends MyInputException {
    public MyAlreadyCreatedException() {
    }

    public MyAlreadyCreatedException(String message) {
        super(message);
    }
}
