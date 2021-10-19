package ru.sberdorofeev.bankapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.exception.EntityNotFoundException;
import ru.sberdorofeev.bankapi.model.dto.card.CardDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardInfoDto;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.CardRepository;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;
import ru.sberdorofeev.bankapi.service.CardService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardServiceImpl implements CardService {

    private final ModelMapper modelMapper;
    private final CardRepository cardRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void addNewCard(String billNumber, CardDto cardDto) {
        try{
            CardEntity cardEntity = modelMapper.map(cardDto, CardEntity.class);
            cardEntity.setCardNumber("4426" + getRandomNumber() + getRandomNumber() + getRandomNumber());
            cardEntity.setExpDate(LocalDate.now().plusYears(3));
            cardEntity.setCcv((int) ((Math.random() * 999) + 100));
            cardEntity.setCreateDate(LocalDate.now());
            cardRepository.createNewCard(billNumber, cardEntity);
        }
        catch (RuntimeException exc){
            log.error(exc.getMessage(), exc);
            throw new EntityNotFoundException("Bill: " + billNumber + " doesn't exist");
        }
    }

    @Override
    public List<CardDto> showAllCardsByBillNumber(String billNumber){
        try{
            InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);
            return invoiceEntity.getCards()
                    .stream()
                    .map(x -> modelMapper.map(x,CardDto.class))
                    .collect(Collectors.toList());
        }
        catch (RuntimeException exc){
            log.error(exc.getMessage(),exc);
            throw new EntityNotFoundException("Bill: " + billNumber + " doesn't exist");
        }

    }

    @Override
    public void increaseCardBalance(String cardNumber, BigDecimal balance) {
        try{
            cardRepository.increaseBalance(cardNumber, balance);
        }
        catch (RuntimeException exc){
            log.error(exc.getMessage(),exc);
            throw new EntityNotFoundException("Card number: " + cardNumber + " doesn't exist");
        }
    }

    @Override
    public BigDecimal getCardBalance(String cardNumber){
        try {
            return cardRepository.checkBalance(cardNumber);
        }
        catch (RuntimeException exc){
            log.error(exc.getMessage(),exc);
            throw new EntityNotFoundException("Card number: " + cardNumber + " doesn't exist");
        }
    }

    @Override
    public List<CardDto> getAllCards(){
        return cardRepository.getAllCards()
                .stream()
                .map(x-> modelMapper.map(x, CardDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CardInfoDto getCardById(Long id){
        try{
            CardEntity entity = cardRepository.getInfoById(id);
            return modelMapper.map(entity, CardInfoDto.class);
        }
        catch (RuntimeException exc){
            log.error(exc.getMessage(),exc);
            throw new EntityNotFoundException("Card with passed ID " + id + " doesn't exist");
        }
    }

    public int getRandomNumber(){
        return (int)((Math.random() * 9999) + 1000);
    }
}
