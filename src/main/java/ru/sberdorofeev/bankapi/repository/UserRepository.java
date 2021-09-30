package ru.sberdorofeev.bankapi.repository;

import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

import java.util.List;


public interface UserRepository {

    /**
     * Create new user
     *
     * @param entity our entity to add into db
     * @void
     * @throws ru.sberdorofeev.bankapi.exception.OpenSessionException if session couldn't open
     */
    void insertUser(UsersEntity entity);
    /**
     * Return all users
     *
     * @return list of users
     * @throws ru.sberdorofeev.bankapi.exception.OpenSessionException if session couldn't open
     */
    List<UsersEntity> getAllUsers();

}
