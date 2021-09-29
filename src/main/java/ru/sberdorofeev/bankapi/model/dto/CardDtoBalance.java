package ru.sberdorofeev.bankapi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@RequiredArgsConstructor
public class CardDtoBalance {

    private Long id;
    private BigDecimal balance;
}
