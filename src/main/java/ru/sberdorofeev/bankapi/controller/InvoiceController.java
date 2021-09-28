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
@RequestMapping("/api/v1/")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/{userId}/invoice")
    public ResponseEntity<?> createNewBill(@RequestBody InvoiceDto invoiceDto, @PathVariable Long userId){
        invoiceService.CreateNewBill(userId, invoiceDto);
        return new ResponseEntity<>("Invoice was succesfull created", HttpStatus.CREATED);
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
