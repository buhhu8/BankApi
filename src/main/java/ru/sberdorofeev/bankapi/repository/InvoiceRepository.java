package ru.sberdorofeev.bankapi.repository;

import org.hibernate.HibernateException;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;

import java.util.List;


public interface InvoiceRepository {

    /**
     * Create new bill
     *
     * @param userId the identifier of user to find
     * @param invoiceEntity our entity to add into db
     * @void
     * @throws HibernateException if session couldn't open
     */
    void insertInvoice(Long userId, InvoiceEntity invoiceEntity);

    /**
     * Show information about invoice by bill
     *
     * @param bill the identifier of invoice to find
     * @return invoiceEntity
     * @throws HibernateException if session couldn't open
     */
    InvoiceEntity getInvoiceByBill(String bill);
    /**
     * Show information about invoice by id
     *
     * @param id the identifier of invoice to find
     * @return invoiceEntity
     * @throws HibernateException if session couldn't open
     */
    InvoiceEntity getInvoiceById(Long id);
    /**
     * Return list of all invoices
     *
     * @return list of invoices
     * @throws HibernateException if session couldn't open
     */
    List<InvoiceEntity> getAllInvoices();

}
