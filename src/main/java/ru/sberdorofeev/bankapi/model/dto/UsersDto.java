package ru.sberdorofeev.bankapi.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class UsersDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String passportSeries;
    private String passportNumber;
    private Timestamp createDateUser;
    private List<InvoiceDto> invoiceDtoList;

}
