package ru.sberdorofeev.bankapi.model.dto.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.sberdorofeev.bankapi.model.CardEnum;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class CardInfoDto {

    private long version;
    private Long id;
    private String cardNumber;
    private LocalDate expDate;
    private Integer ccv;
    private LocalDate createDate;
    private CardEnum activeStatus;
    private InvoiceDto invoiceDto;

}
