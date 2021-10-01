package ru.sberdorofeev.bankapi.service;

import ru.sberdorofeev.bankapi.exception.EntityNotFoundException;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceShowOnlyBillDto;

import java.util.List;

public interface InvoiceService {

    /**
     * Create new bill by user id
     *
     * @param userId the indentifier of user to insert bill
     * @param invoiceDto information about bill, set only balance and type
     * @Void
     * @throws EntityNotFoundException if card doesn't exist
     */
    void createNewBill(Long userId, InvoiceDto invoiceDto);

    /**
     * Get bill information by bill id
     *
     * @param id the indentifier of bill to get
     * @Return InvoiceDto
     * @throws EntityNotFoundException if bill doesn't exist
     */
    InvoiceDto getInvoiceById(Long id);

    /**
     * Get all bill
     *
     * @Return List<InvoiceShowOnlyBillDto>
     */
    List<InvoiceShowOnlyBillDto> getAllInvoices();
}
