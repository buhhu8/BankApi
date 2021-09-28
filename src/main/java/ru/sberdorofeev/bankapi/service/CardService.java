package ru.sberdorofeev.bankapi.service;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.sberdorofeev.bankapi.model.dto.CardDto;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.repository.CardRepository;

import javax.smartcardio.Card;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {

    private final ModelMapper modelMapper;
    private final CardRepository cardRepository;

    public void addNewCard(String billNumber, CardDto cardDto){
        CardEntity cardEntity = modelMapper.map(cardDto, CardEntity.class);
        cardEntity.setCardNumber("4426" + rnd() + rnd() + rnd());
        cardEntity.setExpDate(LocalDate.now().plusYears(3));
        cardEntity.setCcv((int)((Math.random()*999)+100));
        cardEntity.setCreateDate(LocalDate.now());
        cardRepository.produceNewCard(billNumber,cardEntity);

    }

    public List<CardDto> showAllCardsByBillNumber(String billNumber){
        return cardRepository.showAllCards(billNumber)
                .stream().map(x -> modelMapper.map(x,CardDto.class))
                .collect(Collectors.toList());
    }

    public void increaseCardBalance(String cardNumber, BigDecimal balance){
        cardRepository.increaseBalance(cardNumber,balance);
    }

    public BigDecimal getCardBalance(String cardNumber){
        return cardRepository.checkBalance(cardNumber);
    }

    public int rnd(){
        return (int)((Math.random() * 9999) + 1000);
    }
}
