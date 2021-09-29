package ru.sberdorofeev.bankapi.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.UniqueElements;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
@DynamicInsert
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UsersEntity user;

    @OneToMany(mappedBy = "invoiceEntity", fetch = FetchType.EAGER)
    private List<CardEntity> cards;

}
