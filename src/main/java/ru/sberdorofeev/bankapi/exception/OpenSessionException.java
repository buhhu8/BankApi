package ru.sberdorofeev.bankapi.exception;

public class OpenSessionException extends RuntimeException {

    public OpenSessionException(String message) {
        super(message );
    }
}
