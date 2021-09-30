package ru.sberdorofeev.bankapi.repository;

import org.hibernate.HibernateException;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;

import java.util.List;


public interface UserRepository {

    /**
     * Create new user
     *
     * @param entity our entity to add into db
     * @void
     * @throws HibernateException if session couldn't open
     */
    void insertUser(UsersEntity entity);
    /**
     * Return all users
     *
     * @return list of users
     * @throws HibernateException if session couldn't open
     */
    List<UsersEntity> getAllUsers();

}
