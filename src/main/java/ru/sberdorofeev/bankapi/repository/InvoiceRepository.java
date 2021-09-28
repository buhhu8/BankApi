package ru.sberdorofeev.bankapi.repository;

import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;


public interface InvoiceRepository {

    void insertDataIntoInvoice(Long userId, InvoiceEntity invoiceEntity);
    InvoiceEntity getInvoiceByBill(String bill);
    InvoiceEntity getInvoiceById(Long id);
    void increaseBalance(Long id);

}
