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
    void createNewCard(String invoiceNumber, CardEntity cardEntity);

    /**
     * Increase balance for card
     *
     * @param cardNumber the card's number of card to find
     * @param balance increase card balance on this meaning
     * @void
     * @throws ru.sberdorofeev.bankapi.exception.OpenSessionException if session couldn't open
     */
    void increaseBalance(String cardNumber, BigDecimal balance);
    /**
     * Return total balance for card
     *
     * @param cardNumber the card's number of card to find
     * @return bigDecimal balance
     * @throws ru.sberdorofeev.bankapi.exception.OpenSessionException if session couldn't open
     */
    BigDecimal checkBalance(String cardNumber);
    /**
     * Return list of all cards
     *
     * @return list of cards
     * @throws ru.sberdorofeev.bankapi.exception.OpenSessionException if session couldn't open
     */
    List<CardEntity> getAllCards();
    /**
     * Return card by id
     *
     * @param id the indentifier of card to find
     * @return card entity
     * @throws ru.sberdorofeev.bankapi.exception.OpenSessionException if session couldn't open
     */
    CardEntity getInfoById(Long id);
    /**
     * Return card by card number
     *
     * @param cardNumber the card's number of card to find
     * @return card entity
     * @throws ru.sberdorofeev.bankapi.exception.OpenSessionException if session couldn't open
     */
    CardEntity getCardByCardNumber(String cardNumber);


}
