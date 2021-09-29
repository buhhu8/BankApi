package ru.sberdorofeev.bankapi.model.dto.invoice;

import lombok.Data;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class InvoiceDto {

    private Long id;
    private String billNumber;
    private String corBill;
    private BigDecimal balance;
    private Timestamp billCreateDate;
    private InvoiceBillEnum type;
    private UsersDto usersDto;

}
