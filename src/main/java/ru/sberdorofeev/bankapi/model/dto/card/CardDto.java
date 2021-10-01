package ru.sberdorofeev.bankapi.model.dto.card;

import lombok.Data;

@Data
public class CardDto {

    private long version;
    private Long id;
    private String cardNumber;

}
