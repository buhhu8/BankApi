package ru.sberdorofeev.bankapi.exception.invoiceExc;

import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

public class InvoiceAlreadyExistsException extends RuntimeException {

    public InvoiceAlreadyExistsException(InvoiceEntity invoiceEntity) {
        super("bill: " + invoiceEntity.getBillNumber() + " already exists");
    }
}