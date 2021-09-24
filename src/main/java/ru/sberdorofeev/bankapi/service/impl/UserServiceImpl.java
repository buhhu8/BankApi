package ru.sberdorofeev.bankapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.sberdorofeev.bankapi.entity.UsersEntity;
import ru.sberdorofeev.bankapi.service.UserService;
import ru.sberdorofeev.bankapi.utils.HibernateUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    SessionFactory sessionFactory = HibernateUtils.getFactory();


    @Override
    public void insertData(UsersEntity entity) {
       try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            entity.setCreateDateUser(new Timestamp(System.currentTimeMillis()));
            session.save(entity);
            tx.commit();
        }
    }

    @Override
    public UsersEntity getUsers(Long id) {

        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from UsersEntity where id = :paramName");
            query.setParameter("paramName", id);
            return (UsersEntity) query.getSingleResult();
        }
        }

    @Override
    public List<UsersEntity> getAllUsers() {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from UsersEntity ");
            List<UsersEntity> list = query.getResultList();
            return list;

        }

    }


}

