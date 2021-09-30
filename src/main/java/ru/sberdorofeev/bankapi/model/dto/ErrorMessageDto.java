package ru.sberdorofeev.bankapi.model.dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorMessageDto {

    private final LocalTime timestamp;
    private final int status;
    private final String message;
    private final Map<String, String> errors;

    public ErrorMessageDto(int status, String message) {
        this(status, message, new HashMap<>());
    }

    public ErrorMessageDto(int status, String message, Map<String, String> errors) {
        this(LocalTime.now(), status, message, errors);
    }

    public ErrorMessageDto(LocalTime timestamp, int status, String message, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

}
