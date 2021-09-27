package ru.sberdorofeev.bankapi.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.model.dto.InvoiceDto;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.service.InvoiceService;

@Data
@RestController
@RequestMapping("/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<?> createNewBill(@RequestBody InvoiceDto invoiceDto){
        invoiceService.CreateNewBill(invoiceDto);
        return new ResponseEntity<>(invoiceDto, HttpStatus.CREATED);
    }

    @GetMapping("/bill/{billNumber}")
    public ResponseEntity<?> getInvoiceByBillNumber(@PathVariable String billNumber){
        return new ResponseEntity<>(invoiceService.getInvoiceByBill(billNumber),HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable Long id){
        return new ResponseEntity<>(invoiceService.getInvoiceById(id),HttpStatus.OK);
    }

}
