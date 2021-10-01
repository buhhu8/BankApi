package ru.sberdorofeev.bankapi.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;
import ru.sberdorofeev.bankapi.service.impl.InvoiceServiceImpl;

import javax.validation.Valid;

@Data
@RestController
@RequestMapping("/api/v1/bill")
public class InvoiceController {

    private final InvoiceServiceImpl invoiceServiceImpl;

    @PostMapping("{userId}")
    public ResponseEntity<?> createNewBill(@PathVariable Long userId,
                                           @RequestBody @Valid InvoiceDto invoiceDto){
        invoiceServiceImpl.createNewBill(userId, invoiceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id){
        return new ResponseEntity<>(invoiceServiceImpl.getInvoiceById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllInvoice(){
        return new ResponseEntity<>(invoiceServiceImpl.getAllInvoices(),HttpStatus.OK);
    }

}
