package ru.sberdorofeev.bankapi.exception.userExc;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long userId) {
        super("User with such ID " + userId + " doesn't exist");
    }
}