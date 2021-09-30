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
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceDto;
import ru.sberdorofeev.bankapi.model.dto.invoice.InvoiceShowOnlyBillDto;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-it.yml")
class InvoiceControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateNewBill() {
        InvoiceDto dto = new InvoiceDto();
        dto.setBalance(BigDecimal.valueOf(45000));
        dto.setType(InvoiceBillEnum.CREATE);

        ResponseEntity<?> response = restTemplate.postForEntity(
                "/api/v1/bill/3",
                dto,
                Object.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetInvoiceById() {
        ResponseEntity<InvoiceDto> response = restTemplate.getForEntity(
                "/api/v1/cards/1",
                InvoiceDto.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetAllInvoice() {
        ResponseEntity<List<InvoiceShowOnlyBillDto>> response = restTemplate.exchange(
                "/api/v1/bill",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<InvoiceShowOnlyBillDto>>() {});

        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<InvoiceShowOnlyBillDto> invoices = response.getBody();
        assertNotNull(invoices);
    }

}