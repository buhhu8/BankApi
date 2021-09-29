package ru.sberdorofeev.bankapi.model.dto.invoice;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
public class InvoiceDto {

    private Long id;
    private String billNumber;
    private String corBill;
    private BigDecimal balance;
    private LocalDate billCreateDate;
    private InvoiceBillEnum type;
    private UsersDto usersDto;

}
