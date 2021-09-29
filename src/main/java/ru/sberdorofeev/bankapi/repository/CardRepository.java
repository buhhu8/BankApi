package ru.sberdorofeev.bankapi.repository;

import ru.sberdorofeev.bankapi.model.entity.CardEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository {

    void produceNewCard(String invoiceNumber, CardEntity cardEntity);
    List<CardEntity> getInfoByBillNumber(String billNumber);
    void increaseBalance(String cardNumber, BigDecimal balance);
    BigDecimal checkBalance(String cardNumber);
    List<CardEntity> showAllCards();
    CardEntity getInfoById(Long id);


}
