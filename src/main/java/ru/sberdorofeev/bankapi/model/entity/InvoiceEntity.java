package ru.sberdorofeev.bankapi.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "INVOICE")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "BILL_NUMBER")
    private String billNumber;
    @Column(name = "COR_BILL")
    private String corBill;
    @Column(name = "BALANCE")
    private BigDecimal balance;
    @Column(name = "BILL_CREATE_DATE")
    private Timestamp billCreateDate;
    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private InvoiceBillEnum type;
    @Column(name = "USER_ID")
    private long userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", nullable = false)
    private UsersEntity user;

}
