package ru.sberdorofeev.bankapi.service;

import ru.sberdorofeev.bankapi.model.dto.UsersDto;

import java.util.List;

public interface UserService {

    /**
     * Create new User
     *
     * @param usersDto information about user, set FirstName,
     *                 lastName, middleName(if exists), passport series, passport number
     * @Void
     */
    void insertUser(UsersDto usersDto);

    /**
     * Show all users
     *
     * @Return List<UsersDto>
     */
    List<UsersDto> getAllUsers();
}
