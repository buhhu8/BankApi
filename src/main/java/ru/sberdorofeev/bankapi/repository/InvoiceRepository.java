package ru.sberdorofeev.bankapi.repository;

import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.impl.InvoiceRepositoryImpl;

import java.util.List;


public interface InvoiceRepository {

    void insertDataIntoInvoice(Long userId, InvoiceEntity invoiceEntity);
    InvoiceEntity getInvoiceByBill(String bill);
    InvoiceEntity getInvoiceById(Long id);
    List<InvoiceEntity> getAllInvoices();

}
