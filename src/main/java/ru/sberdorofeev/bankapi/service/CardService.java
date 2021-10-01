package ru.sberdorofeev.bankapi.service;

import ru.sberdorofeev.bankapi.exception.EntityNotFoundException;
import ru.sberdorofeev.bankapi.model.dto.card.CardDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardInfoDto;

import java.math.BigDecimal;
import java.util.List;

public interface CardService {

    /**
     * Create new card by bill number
     *
     * @param billNumber the indentifier of bill
     * @param cardDto information about card, data generated random
     * @void
     * @throws EntityNotFoundException if bill number doesn't exist
     */
    void addNewCard(String billNumber, CardDto cardDto);

    /**
     * Show all cards by bill number
     *
     * @param billNumber the indentifier of bill
     * @Return List<CardDto>
     * @throws EntityNotFoundException if bill number doesn't exist
     */
    List<CardDto> showAllCardsByBillNumber(String billNumber);

    /**
     * Increase card balance by card number
     *
     * @param cardNumber the indentifier of card that increase
     * @param balance amount increase
     * @Void
     * @throws EntityNotFoundException if card doesn't exist
     */
    void increaseCardBalance(String cardNumber, BigDecimal balance);

    /**
     * Show card balance by card number
     *
     * @param cardNumber the indentifier that check balance
     * @Return BigDecimal
     * @throws EntityNotFoundException if card doesn't exist
     */
    BigDecimal getCardBalance(String cardNumber);

    /**
     * Show all cards
     *
     * @Return List<CardDto>
     */
    List<CardDto> getAllCards();

    /**
     * Show all information about card by id
     *
     * @param id the indentifier to get info about card
     * @Return CardInfoDto
     * @throws EntityNotFoundException if card doesn't exist
     */
    CardInfoDto getCardById(Long id);
}
