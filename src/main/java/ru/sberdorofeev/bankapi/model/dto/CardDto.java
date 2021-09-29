package ru.sberdorofeev.bankapi.model.dto;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class CardDto {

    private Long id;
    private String cardNumber;

}
