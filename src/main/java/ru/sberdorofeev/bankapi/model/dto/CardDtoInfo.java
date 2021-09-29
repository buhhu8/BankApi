package ru.sberdorofeev.bankapi.model.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
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
