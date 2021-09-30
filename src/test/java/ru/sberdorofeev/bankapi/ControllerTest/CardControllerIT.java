package ru.sberdorofeev.bankapi.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.TestPropertySource;
import ru.sberdorofeev.bankapi.model.dto.card.CardBalanceDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardDto;
import ru.sberdorofeev.bankapi.model.dto.card.CardInfoDto;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-it.yml")
public class CardControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateNewCard() {
        CardDto dto = new CardDto();
        dto.setCardNumber("42132341427634563437");

        ResponseEntity<?> response = restTemplate.postForEntity(
                "/api/v1/cards/42132341427634563432",
                dto,
                Object.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetAllCardsByBillNumber(){
        ResponseEntity<List<CardDto>> response = restTemplate.exchange(
                "/api/v1/cards/bill/42132341427634563432",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<CardDto> cards = response.getBody();
        assertNotNull(cards);
    }

//    @Test
//    public void increaseBalanceByCardNumber(){
//        ResponseEntity<CardBalanceDto> response = restTemplate.getForEntity(
//                "/api/v1/cards/balance/4217234312123135",
//                CardBalanceDto.class);
//
//        BigDecimal balance = response.getBody().getBalance();
//        CardBalanceDto cardBalanceDto = new CardBalanceDto();
//        cardBalanceDto.setBalance(balance);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<CardBalanceDto> requestUpdate = new HttpEntity<CardBalanceDto>(cardBalanceDto);
//
//        response = restTemplate.exchange(
//                "/api/v1/cards/balance/4217234312123135",
//                HttpMethod.PUT,
//                requestUpdate,
//                CardBalanceDto.class);
//
//        assertNotEquals(balance,response.getBody().getBalance());
//    }

    @Test
    public void testGetBalanceByCard() {
        ResponseEntity<CardBalanceDto> response = restTemplate.getForEntity(
                "/api/v1/cards/balance/4217234356783456",
                CardBalanceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(BigDecimal.valueOf(432010.0), response.getBody().getBalance());
    }

    @Test
    public void testGetAllCards() {
        ResponseEntity<List<CardDto>> response = restTemplate.exchange(
                "/api/v1/cards",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<CardDto> cards = response.getBody();
        assertNotNull(cards);
    }

    @Test
    public void testGetCardById(){
        ResponseEntity<CardInfoDto> response = restTemplate.getForEntity(
                "/api/v1/cards/1",
                CardInfoDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }



}
