package ru.sberdorofeev.bankapi.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.exception.userExc.UserAlreadyExistsException;
import ru.sberdorofeev.bankapi.exception.userExc.UserNotFoundException;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.repository.UserRepository;
import ru.sberdorofeev.bankapi.utils.HibernateUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    SessionFactory sessionFactory = HibernateUtils.getFactory();


    @Override
    public void insertData(UsersEntity entity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            entity.setCreateDateUser(new Timestamp(System.currentTimeMillis()));
            session.save(entity);
            tx.commit();
        }
        catch (Exception exc){
            throw new UserAlreadyExistsException(entity);
        }
    }


    @Override
    public UsersEntity getUsers(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from UsersEntity where id = :paramName");
            query.setParameter("paramName", id);
            return (UsersEntity) query.getSingleResult();
        }
        catch (Exception exc){
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        try(Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from UsersEntity ");
            List<UsersEntity> list = query.getResultList();
            return list;
        }
        catch (Exception exc){
            return new ArrayList<>();
        }
    }
}
