package ru.sberdorofeev.bankapi.repository;

import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

import java.util.List;


public interface UserRepository {

    void insertData(UsersEntity entity);
    List<UsersEntity> getAllUsers();

}
