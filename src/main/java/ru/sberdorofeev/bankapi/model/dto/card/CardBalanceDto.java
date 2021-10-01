package ru.sberdorofeev.bankapi.model.dto.card;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class CardBalanceDto {

    private long version;
    private BigDecimal balance;

    public CardBalanceDto(BigDecimal balance) {
        this.balance = balance;
    }
}

