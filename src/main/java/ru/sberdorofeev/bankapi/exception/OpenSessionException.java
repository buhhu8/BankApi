package ru.sberdorofeev.bankapi.exception;

import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

public class OpenSessionException extends RuntimeException {

    public OpenSessionException(String message) {
        super(message );
    }
}