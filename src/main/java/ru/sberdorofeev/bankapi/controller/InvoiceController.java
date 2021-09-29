package ru.sberdorofeev.bankapi.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;
import ru.sberdorofeev.bankapi.service.InvoiceService;

import javax.validation.Valid;

@Data
@RestController
@RequestMapping("/api/v1/bill/")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("{userId}")
    public ResponseEntity<?> createNewBill(@RequestBody InvoiceDto invoiceDto, @PathVariable Long userId){
        invoiceService.CreateNewBill(userId, invoiceDto);
        return new ResponseEntity<>("Invoice was succesfull created", HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id){
        return new ResponseEntity<>(invoiceService.getInvoiceById(id),HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> getAllInvoice(){
        return new ResponseEntity<>(invoiceService.getAllInvoices(),HttpStatus.OK);
    }

}
