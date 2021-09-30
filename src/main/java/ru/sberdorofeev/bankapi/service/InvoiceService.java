package ru.sberdorofeev.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.exception.EntityNotFoundException;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceShowOnlyBillDto;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final ModelMapper modelMapper;
    private final InvoiceRepository invoiceRepository;

    public void createNewBill(Long userId, InvoiceDto invoiceDto) {
        try{
            InvoiceEntity invoiceEntity = modelMapper.map(invoiceDto, InvoiceEntity.class);
            invoiceEntity.setBillNumber("4807" + rnd() + rnd() + rnd() + rnd());
            invoiceEntity.setCorBill("41070800002314865748");
            invoiceEntity.setBillCreateDate(LocalDate.now());
            invoiceRepository.insertInvoice(userId, invoiceEntity);
        }
        catch (RuntimeException exc){
            throw new EntityNotFoundException("User with passed id: " + userId + " doesn't exist");
        }

    }

    public InvoiceDto getInvoiceById(Long id) {
        try{
            InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceById(id);
            return modelMapper.map(invoiceEntity, InvoiceDto.class);
        }
        catch (RuntimeException exc){
            throw new EntityNotFoundException("Invoice with passed id: " + id + " doesn't exist");
        }

    }

    public List<InvoiceShowOnlyBillDto> getAllInvoices() {
        return invoiceRepository.getAllInvoices()
                .stream().map(x -> modelMapper.map(x, InvoiceShowOnlyBillDto.class))
                .collect(Collectors.toList());
    }

    public int rnd() {
        return (int) ((Math.random() * 9999) + 1000);
    }

}