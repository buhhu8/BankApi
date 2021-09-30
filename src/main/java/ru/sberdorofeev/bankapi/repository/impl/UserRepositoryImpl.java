package ru.sberdorofeev.bankapi.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.repository.UserRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void insertUser(UsersEntity entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        }
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            List<UsersEntity> usersEntities = session
                    .createQuery("from UsersEntity")
                    .getResultList();
            return usersEntities;
        }
    }
}
