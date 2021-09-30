package ru.sberdorofeev.bankapi.repository;

import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

import java.util.List;


public interface UserRepository {

    /**
     * Create new user
     *
     * @param entity our entity to add into db
     * @void
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    void insertUser(UsersEntity entity);
    /**
     * Return all users
     *
     * @return list of users
     * @throws org.dins.exception.UserNotFoundException if no user with such ID
     */
    List<UsersEntity> getAllUsers();

}
