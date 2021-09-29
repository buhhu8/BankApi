package ru.sberdorofeev.bankapi.repository;

import ru.sberdorofeev.bankapi.model.entity.CardEntity;

import java.math.BigDecimal;
import java.util.List;

public interface CardRepository {

    /**
     * Create new Card
     *
     * @param invoiceNumber the invoice's number of bill to find
     * @param cardEntity this entity we want to add into db
     * @void
     * @throws ru.sberdorofeev.bankapi.exception.cardExc.CardAlreadyExistsException if no user with such ID
     */
    void produceNewCard(String invoiceNumber, CardEntity cardEntity);

    /**
     * Return list of all cards for one bill
     *
     * @param billNumber the bill's number of bill to find
     * @return list of Cards
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    List<CardEntity> getInfoByBillNumber(String billNumber);

    /**
     * Increase balance for card
     *
     * @param cardNumber the card's number of card to find
     * @param balance increase card balance on this meaning
     * @void
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    void increaseBalance(String cardNumber, BigDecimal balance);
    /**
     * Return total balance for card
     *
     * @param cardNumber the card's number of card to find
     * @return bigDecimal balance
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    BigDecimal checkBalance(String cardNumber);
    /**
     * Return list of all cards
     *
     * @return list of cards
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    List<CardEntity> showAllCards();
    /**
     * Return card by id
     *
     * @param id the indentifier of card to find
     * @return card entity
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    CardEntity getInfoById(Long id);
    /**
     * Return card by card number
     *
     * @param cardNumber the card's number of card to find
     * @return card entity
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    CardEntity getCardByCardNumber(String cardNumber);


}
