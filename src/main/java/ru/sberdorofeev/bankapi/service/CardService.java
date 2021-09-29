package ru.sberdorofeev.bankapi.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.model.dto.card.CardDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardDtoInfo;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.repository.CardRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final ModelMapper modelMapper;
    private final CardRepository cardRepository;

    public void addNewCard(String billNumber, CardDto cardDto){
        CardEntity cardEntity = modelMapper.map(cardDto, CardEntity.class);
       // cardEntity.setCardNumber("4426" + rnd() + rnd() + rnd());
        String number = "4426278018784597";
        cardEntity.setCardNumber("4426278018784597");
        cardEntity.setExpDate(LocalDate.now().plusYears(3));
        cardEntity.setCcv((int)((Math.random()*999)+100));
        cardEntity.setCreateDate(LocalDate.now());
        cardRepository.produceNewCard(billNumber,cardEntity);

    }

    public List<CardDto> showAllCardsByBillNumber(String billNumber){
        return cardRepository.getInfoByBillNumber(billNumber)
                .stream().map(x -> modelMapper.map(x,CardDto.class))
                .collect(Collectors.toList());
    }

    public void increaseCardBalance(String cardNumber, BigDecimal balance){
        cardRepository.increaseBalance(cardNumber,balance);
    }

    public BigDecimal getCardBalance(String cardNumber){
        return cardRepository.checkBalance(cardNumber);
    }

    public List<CardDto> getAllCards(){
        return cardRepository.showAllCards()
                .stream().map(x-> modelMapper.map(x, CardDto.class))
                .collect(Collectors.toList());
    }

    public CardDtoInfo getCardById(Long id){
        CardEntity entity = cardRepository.getInfoById(id);
        return modelMapper.map(entity, CardDtoInfo.class);
    }

    public CardDto getCardByNumber(String number){
        CardEntity entity = cardRepository.getCardByCardNumber(number);
        return modelMapper.map(entity,CardDto.class);
    }

    public int rnd(){
        return (int)((Math.random() * 9999) + 1000);
    }
}
