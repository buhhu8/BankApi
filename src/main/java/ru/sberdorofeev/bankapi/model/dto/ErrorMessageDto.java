package ru.sberdorofeev.bankapi.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalTime;

@Data
public class ErrorMessageDto {
    private final LocalTime timestamp;
    private final int status;
    private final String message;

    public ErrorMessageDto(int status, String message) {
        this.timestamp = LocalTime.now();
        this.status = status;
        this.message = message;
    }
}
