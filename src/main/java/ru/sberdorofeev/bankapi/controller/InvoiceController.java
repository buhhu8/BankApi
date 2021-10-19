package ru.sberdorofeev.bankapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;
import ru.sberdorofeev.bankapi.service.InvoiceService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/bill")
@Slf4j
public class InvoiceController {

    private final InvoiceService invoiceServiceImpl;

    @PostMapping("{userId}")
    public ResponseEntity<?> createNewBill(@PathVariable Long userId,
                                           @RequestBody @Valid InvoiceDto invoiceDto){
        log.debug("invoke createNewBill", userId, invoiceDto);
        invoiceServiceImpl.createNewBill(userId, invoiceDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id){
        log.debug("Invoke getInvoiceById", id);
        return new ResponseEntity<>(invoiceServiceImpl.getInvoiceById(id),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllInvoice(){
        log.debug("invoke getAllInvoice");
        return new ResponseEntity<>(invoiceServiceImpl.getAllInvoices(),HttpStatus.OK);
    }

}
