package ru.sberdorofeev.bankapi.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.sberdorofeev.bankapi.model.CardEnum;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private CardEnum activeStatus;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "INVOICE_ID")
    InvoiceEntity invoiceEntity;


}
