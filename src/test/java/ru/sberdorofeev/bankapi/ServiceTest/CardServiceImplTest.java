package ru.sberdorofeev.bankapi.ServiceTest;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;
import ru.sberdorofeev.bankapi.model.CardEnum;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.dto.card.CardDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardInfoDto;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;
import ru.sberdorofeev.bankapi.repository.impl.CardRepositoryImpl;
import ru.sberdorofeev.bankapi.service.impl.CardServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RequiredArgsConstructor
class CardServiceImplTest {

    @Mock
    private ModelMapper modelMapper;
    @Mock
    private CardRepositoryImpl cardRepository;
    @Mock
    private InvoiceRepository invoiceRepository;
    @InjectMocks
    private CardServiceImpl cardServiceImpl;

    @Test
    void testAddNewCard_billExists() {
        CardDto dto = createCardDto();
        CardEntity entity = createCardEntity();
        String billNumber = "23451234123498762345";
        when(modelMapper.map(dto,CardEntity.class))
                .thenReturn(entity);

        cardServiceImpl.addNewCard(billNumber,dto);
        verify(cardRepository,times(1)).createNewCard(billNumber,entity);
    }

    @Test
    void increaseCardBalance() {
        String cardNumber = "2345123418762345";
        BigDecimal balance = BigDecimal.valueOf(123);

        cardServiceImpl.increaseCardBalance(cardNumber,balance);

        verify(cardRepository,times(1)).increaseBalance(cardNumber,balance);
    }

    @Test
    void testGetCardBalance_cardExists_returnBalance() {
        String cardNumber = "4321456712349876";
        BigDecimal expResult = BigDecimal.valueOf(123);
        when(cardRepository.checkBalance(cardNumber))
                .thenReturn(BigDecimal.valueOf(123));

        BigDecimal result = cardServiceImpl.getCardBalance(cardNumber);

        assertEquals(expResult,result);
    }


    @Test
    void testGetCardById() {
        Long id = 1l;
        CardInfoDto infoDto = createCardInfoDto();
        CardEntity entity = createCardEntity();
        when(cardRepository.getInfoById(id))
                .thenReturn(entity);
        when(modelMapper.map(entity,CardInfoDto.class))
                .thenReturn(infoDto);

        CardInfoDto result = cardServiceImpl.getCardById(id);

        assertEquals(infoDto, result);
    }

    private CardEntity createCardEntity(){
        CardEntity entity = new CardEntity();
        entity.setId(1L);
        entity.setCardNumber("1");
        entity.setCardNumber("4321456712349876");
        entity.setExpDate(LocalDate.now().plusYears(3));
        entity.setCcv(456);
        entity.setCreateDate(LocalDate.now());
        entity.setActiveStatus(CardEnum.ACTIVE);
        return entity;
    }

    private CardDto createCardDto(){
        CardDto dto = new CardDto();
        dto.setId(1L);
        dto.setCardNumber("4321456712349876");
        return dto;
    }

    private CardInfoDto createCardInfoDto(){
        CardInfoDto dto = new CardInfoDto();
        dto.setId(1L);
        dto.setCardNumber("4321456712349876");
        dto.setCreateDate(LocalDate.now());
        dto.setCcv(123);
        dto.setExpDate(LocalDate.now().plusYears(3));
        dto.setActiveStatus(CardEnum.ACTIVE);
        return dto;
    }

    private InvoiceEntity createInvoiceEntity(){
        InvoiceEntity entity = new InvoiceEntity();
        CardEntity cardEntity = createCardEntity();
        List<CardEntity> list = new ArrayList<>();
        list.add(cardEntity);
        entity.setId(1L);
        entity.setBillNumber("42132341427634563432");
        entity.setCorBill("234598760987234512345");
        entity.setBalance(BigDecimal.valueOf(12345));
        entity.setType(InvoiceBillEnum.CREATE);
        entity.setBillCreateDate(LocalDate.now());
        entity.setCards(list);
        return entity;
    }

}