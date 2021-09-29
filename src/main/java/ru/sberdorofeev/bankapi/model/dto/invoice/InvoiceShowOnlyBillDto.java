package ru.sberdorofeev.bankapi.model.dto.invoice;

import lombok.Data;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class InvoiceShowOnlyBillDto {
    private Long id;
    private String billNumber;
    private String corBill;
    private BigDecimal balance;
    private Timestamp billCreateDate;
    private InvoiceBillEnum type;
}
