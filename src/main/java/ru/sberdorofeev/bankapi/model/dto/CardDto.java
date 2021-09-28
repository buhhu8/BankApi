package ru.sberdorofeev.bankapi.model.dto;

import lombok.Data;

import javax.persistence.Column;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class CardDto {

    private Long id;
    private String cardNumber;
    private Timestamp expDate;
    private Integer ccv;
    private LocalDate createDate;
    private String activeStatus;
    private InvoiceDto invoiceDto;


}
