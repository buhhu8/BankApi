package ru.sberdorofeev.bankapi.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@Table(name = "CARD")
public class CardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CARD_NUMBER")
    private String cardNumber;
    @Column(name = "EXP_DATE")
    private LocalDate expDate;
    @Column(name = "CCV")
    private Integer ccv;
    @Column(name = "CREATE_DATE")
    private LocalDate createDate;
    @Column(name = "ACTIVE_STATUS")
    private String activeStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "INVOICE_ID")
    InvoiceEntity invoiceEntity;


}
