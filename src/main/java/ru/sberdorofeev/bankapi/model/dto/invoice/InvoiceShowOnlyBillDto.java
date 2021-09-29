package ru.sberdorofeev.bankapi.model.dto.invoice;

import lombok.Data;
import org.apache.tomcat.jni.Local;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class InvoiceShowOnlyBillDto {
    private Long id;
    private String billNumber;
    private String corBill;
    private BigDecimal balance;
    private LocalDate billCreateDate;
    private InvoiceBillEnum type;
}
