package ru.sberdorofeev.bankapi.ControllerTest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public void testCreateNewCardSuccess() {
        CardDto dto = new CardDto();

        ResponseEntity<?> response = restTemplate.postForEntity(
                "/api/v1/cards/42132341427634563432",
                dto,
                Object.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testCreateNewCardFailure(){
        CardDto dto = new CardDto();

        ResponseEntity<?> response = restTemplate.postForEntity(
                "/api/v1/cards/421212",
                dto,
                Object.class
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetAllCardsByBillNumberSuccess(){
        ResponseEntity<List<CardDto>> response = restTemplate.exchange(
                "/api/v1/cards/bill/42132341427634563432",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CardDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<CardDto> cards = response.getBody();
        assertNotNull(cards);
    }

    @Test
    public void increaseBalanceByCardNumber(){
        ResponseEntity<CardBalanceDto> response = restTemplate.getForEntity(
                "/api/v1/cards/balance/4217234312123135",
                CardBalanceDto.class);

        BigDecimal balance = response.getBody().getBalance();
        CardBalanceDto cardBalanceDto = new CardBalanceDto();
        cardBalanceDto.setBalance(balance);

        restTemplate.put(
                "/api/v1/cards/balance/4217234312123135",
                cardBalanceDto);

        response = restTemplate.getForEntity(
                "/api/v1/cards/balance/4217234312123135",
                CardBalanceDto.class);

        assertNotEquals(balance,response.getBody().getBalance());
    }


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
