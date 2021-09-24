package ru.sberdorofeev.bankapi.service;

import ru.sberdorofeev.bankapi.entity.UsersEntity;

public interface UserService {

    void insertData(UsersEntity entity);
    UsersEntity getUsers(Long id);

}
