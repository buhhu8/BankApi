package ru.sberdorofeev.bankapi.repository;

import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.impl.InvoiceRepositoryImpl;

import java.util.List;


public interface InvoiceRepository {

    /**
     * Create new bill
     *
     * @param userId the identifier of user to find
     * @param invoiceEntity our entity to add into db
     * @void
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    void insertDataIntoInvoice(Long userId, InvoiceEntity invoiceEntity);

    /**
     * Show information about invoice by bill
     *
     * @param bill the identifier of invoice to find
     * @return invoiceEntity
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    InvoiceEntity getInvoiceByBill(String bill);
    /**
     * Show information about invoice by id
     *
     * @param id the identifier of invoice to find
     * @return invoiceEntity
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    InvoiceEntity getInvoiceById(Long id);
    /**
     * Return list of all invoices
     *
     * @return list of invoices
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    List<InvoiceEntity> getAllInvoices();

}
