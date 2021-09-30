package ru.sberdorofeev.bankapi.exception.cardExc;

import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;

public class CardAlreadyExistsException extends RuntimeException {

    public CardAlreadyExistsException() {
        super();
    }

    public CardAlreadyExistsException(CardEntity cardEntity) {
        super("card: " + cardEntity.getCardNumber() + " already exists");
    }
}