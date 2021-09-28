package ru.sberdorofeev.bankapi.service;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.dto.InvoiceDto;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;

import java.sql.Timestamp;

@Service
@Data
public class InvoiceService {

    private final ModelMapper modelMapper;
    private final InvoiceRepository invoiceRepository;

    public void CreateNewBill(Long userId, InvoiceDto invoiceDto){
        InvoiceEntity invoiceEntity = modelMapper.map(invoiceDto, InvoiceEntity.class);
        invoiceEntity.setBillNumber("4807" + " " + rnd() + " " + rnd() + " " + rnd() + " " + rnd());
        invoiceEntity.setCorBill("4107 0800 0023 1486 5748");
        invoiceEntity.setBillCreateDate(new Timestamp(System.currentTimeMillis()));
        invoiceRepository.insertDataIntoInvoice(userId, invoiceEntity);
    }

    public InvoiceDto getInvoiceByBill(String billNumber){
        InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);
        return modelMapper.map(invoiceEntity, InvoiceDto.class);
    }

    public InvoiceDto getInvoiceById(Long id){
        InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceById(id);
        InvoiceDto dto =  modelMapper.map(invoiceEntity, InvoiceDto.class);
        return dto;
    }


    public int rnd(){
        return (int)((Math.random() * 9999) + 1000);
    }

}
