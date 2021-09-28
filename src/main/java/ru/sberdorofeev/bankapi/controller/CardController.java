package ru.sberdorofeev.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.model.dto.CardDto;
import ru.sberdorofeev.bankapi.service.CardService;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
public class CardController {

    private final CardService cardService;

    @PostMapping("/{billNumber}")
    public ResponseEntity<?> addNewCard (@PathVariable String  billNumber, @RequestBody CardDto cardDto){
        cardService.addNewCard(billNumber, cardDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/billnumber/{billNumber}")
    public ResponseEntity<?> showAllCardsByBillNumber(@PathVariable String billNumber){
       return new ResponseEntity<>(cardService.showAllCardsByBillNumber(billNumber),HttpStatus.OK);
    }

    @PatchMapping("/{cardNumber}/{balance}")
    public ResponseEntity<?> increaseCardBalanceByNumber(@PathVariable String cardNumber,
                                                        @PathVariable BigDecimal balance){
        cardService.increaseCardBalance(cardNumber,balance);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cardnumber/{cardNumber}")
    public ResponseEntity<?> getCardBalance(@PathVariable String cardNumber){
        BigDecimal balance = cardService.getCardBalance(cardNumber);
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }

}
