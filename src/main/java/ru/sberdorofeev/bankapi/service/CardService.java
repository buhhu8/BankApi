package ru.sberdorofeev.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.exception.EntityNotFoundException;
import ru.sberdorofeev.bankapi.model.dto.card.CardDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardInfoDto;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.CardRepository;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final ModelMapper modelMapper;
    private final CardRepository cardRepository;
    private final InvoiceRepository invoiceRepository;

    public void addNewCard(String billNumber, CardDto cardDto) {
        try{
            CardEntity cardEntity = modelMapper.map(cardDto, CardEntity.class);
            cardEntity.setCardNumber("4426" + rnd() + rnd() + rnd());
            cardEntity.setExpDate(LocalDate.now().plusYears(3));
            cardEntity.setCcv((int) ((Math.random() * 999) + 100));
            cardEntity.setCreateDate(LocalDate.now());
            cardRepository.createNewCard(billNumber, cardEntity);
        }
        catch (RuntimeException exc){
            throw new EntityNotFoundException("Bill: " + billNumber + " doesn't exist");
        }
    }

    public List<CardDto> showAllCardsByBillNumber(String billNumber){
        try{
            InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);
            return invoiceEntity.getCards()
                    .stream()
                    .map(x -> modelMapper.map(x,CardDto.class))
                    .collect(Collectors.toList());
        }
        catch (RuntimeException exc){
            throw new EntityNotFoundException("Bill: " + billNumber + " doesn't exist");
        }

    }

    public void increaseCardBalance(String cardNumber, BigDecimal balance) {
        try{
            cardRepository.increaseBalance(cardNumber, balance);
        }
        catch (RuntimeException exc){
            throw new EntityNotFoundException("Card number: " + cardNumber + " doesn't exist");
        }
    }

    public BigDecimal getCardBalance(String cardNumber){
        return cardRepository.checkBalance(cardNumber);
    }

    public List<CardDto> getAllCards(){
        return cardRepository.getAllCards()
                .stream()
                .map(x-> modelMapper.map(x, CardDto.class))
                .collect(Collectors.toList());
    }

    public CardInfoDto getCardById(Long id){
        try{
            CardEntity entity = cardRepository.getInfoById(id);
            return modelMapper.map(entity, CardInfoDto.class);
        }
        catch (RuntimeException exc){
            throw new EntityNotFoundException("Card with passed ID " + id + " doesn't exist");
        }
    }

    public int rnd(){
        return (int)((Math.random() * 9999) + 1000);
    }
}
