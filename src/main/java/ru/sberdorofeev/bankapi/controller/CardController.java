package ru.sberdorofeev.bankapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sberdorofeev.bankapi.model.dto.card.CardDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardBalanceDto;
import ru.sberdorofeev.bankapi.service.impl.CardServiceImpl;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cards")
@Slf4j
public class CardController {

    private final CardServiceImpl cardService;

    @PostMapping("/{billNumber}")
    public ResponseEntity<?> createNewCard(@PathVariable String billNumber, @RequestBody CardDto cardDto){
        log.debug("invoke createNew card", billNumber, cardDto);
        cardService.addNewCard(billNumber, cardDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/bill/{billNumber}")
    public ResponseEntity<?> getAllCardsByBillNumber(@PathVariable String billNumber) {
        log.debug("invoke getAllCardsByBillNumber", billNumber);
        return new ResponseEntity<>(cardService.showAllCardsByBillNumber(billNumber), HttpStatus.OK);
    }

    @PutMapping("/balance/{cardNumber}")
    public ResponseEntity<?> increaseBalanceByCardNumber(@PathVariable String cardNumber,
                                                         @RequestBody CardBalanceDto dtoBalance){
        log.debug("invoke increaseBalanceByCardNumber", cardNumber, dtoBalance);
        cardService.increaseCardBalance(cardNumber, dtoBalance.getBalance());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/balance/{cardNumber}")
    public ResponseEntity<?> getCardBalance(@PathVariable String cardNumber){
        log.debug("invoke getCardBalance" , cardNumber);
        BigDecimal balance = cardService.getCardBalance(cardNumber);
        return new ResponseEntity<>(new CardBalanceDto(balance), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCards(){
        log.debug("invoke getAllCards");
        return new ResponseEntity<>(cardService.getAllCards(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCardById(@PathVariable Long id){
        log.debug("invoke getCardById", id);
        return new ResponseEntity<>(cardService.getCardById(id), HttpStatus.OK);
    }

}
