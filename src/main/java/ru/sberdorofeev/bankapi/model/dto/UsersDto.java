package ru.sberdorofeev.bankapi.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
public class UsersDto {

    private Long id;

    @NotEmpty(message = "User firstName couldn't be empty")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String firstName;

    @NotEmpty(message = "User LastName couldn't be empty")
    @Pattern(regexp = "^[а-яА-ЯёЁa-zA-Z]+$")
    private String lastName;

    private String middleName;

    @NotEmpty(message = "User passport series couldn't be empty")
    private String passportSeries;

    @NotEmpty(message = "User passport number couldn't be empty")
    private String passportNumber;

    private LocalDate createDateUser;

}
