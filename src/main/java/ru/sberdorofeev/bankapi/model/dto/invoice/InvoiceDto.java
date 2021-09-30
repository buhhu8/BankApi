package ru.sberdorofeev.bankapi.model.dto.invoice;

import lombok.Data;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class InvoiceDto {

    private Long id;
    private String billNumber;
    private String corBill;
    @NotNull(message = "Balance must be non null")
    private BigDecimal balance;
    private LocalDate billCreateDate;
    @NotNull(message = "Bill type must be non null")
    private InvoiceBillEnum type;
    private UsersDto usersDto;

}
