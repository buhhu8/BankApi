package ru.sberdorofeev.bankapi.exception.userExc;

import ru.sberdorofeev.bankapi.model.dto.UsersDto;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(UsersEntity usersEntity) {
        super("User: " + usersEntity.getFirstName() + " " + usersEntity.getLastName() + " with passport: "
                        + usersEntity.getPassportSeries() + " " + usersEntity.getPassportNumber() + " already exists" );
    }
}
