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
import ru.sberdorofeev.bankapi.model.dto.UsersDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-it.yml")
class UserControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateNewUser() {
        UsersDto dto = new UsersDto();
        dto.setFirstName("Denis");
        dto.setLastName("Dorofeev");
        dto.setMiddleName("Aleksandrovich");
        String series =  String.valueOf((int)((Math.random() * 9999) + 1000));
        dto.setPassportSeries(series);
        String number = String.valueOf((int)((Math.random() * 999999) + 100000));
        dto.setPassportNumber("number");
        ResponseEntity<?> response = restTemplate.postForEntity(
                "/api/v1/users",
                dto,
                Object.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetAllUsers() {
        ResponseEntity<List<UsersDto>> response = restTemplate.exchange(
                "/api/v1/users",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UsersDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<UsersDto> users = response.getBody();
        assertNotNull(users);
    }
}