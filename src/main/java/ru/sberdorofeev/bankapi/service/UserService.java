package ru.sberdorofeev.bankapi.service;

import ru.sberdorofeev.bankapi.entity.UsersEntity;

import java.util.List;

public interface UserService {

    void insertData(UsersEntity entity);
    UsersEntity getUsers(Long id);
    List<UsersEntity> getAllUsers();

}
