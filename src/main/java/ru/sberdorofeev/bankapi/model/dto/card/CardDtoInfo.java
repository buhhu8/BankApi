package ru.sberdorofeev.bankapi.model.dto.card;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;

import java.time.LocalDate;

@Setter
@Getter
@RequiredArgsConstructor
public class CardDtoInfo {

    private Long id;
    private String cardNumber;
    private LocalDate expDate;
    private Integer ccv;
    private LocalDate createDate;
    private String activeStatus;
    private InvoiceDto invoiceDto;

}
