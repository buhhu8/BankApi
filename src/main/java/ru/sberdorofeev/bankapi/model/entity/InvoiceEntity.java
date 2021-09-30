package ru.sberdorofeev.bankapi.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
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
    private LocalDate billCreateDate;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private InvoiceBillEnum type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private UsersEntity user;

    @OneToMany(mappedBy = "invoiceEntity", fetch = FetchType.EAGER)
    private List<CardEntity> cards;

}
