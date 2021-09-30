package ru.sberdorofeev.bankapi;

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

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-it.yml")
public class CardControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

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
        assertFalse(cards.isEmpty());
    }

    @Test
    public void testGetBalanceByCard() {
        ResponseEntity<CardBalanceDto> response = restTemplate.getForEntity(
                "/api/v1/cards/balance/4217234356783456",
                CardBalanceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(BigDecimal.valueOf(432010.0), response.getBody().getBalance());
    }


    //http://localhost:5697/api/v1/cards/42132341427634563432
    @Test
    public void testAddNewCard() {
        CardDto dto = new CardDto();
        dto.setCardNumber("42132341427634563437");

        ResponseEntity<?> response = restTemplate.postForEntity(
                "/api/v1/cards/42132341427634563432",
                dto,
                Object.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

}
