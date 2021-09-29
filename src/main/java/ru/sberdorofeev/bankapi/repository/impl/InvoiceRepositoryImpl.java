package ru.sberdorofeev.bankapi.repository.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.exception.OpenSessionException;
import ru.sberdorofeev.bankapi.exception.invoiceExc.InvoiceAlreadyExistsException;
import ru.sberdorofeev.bankapi.exception.userExc.UserAlreadyExistsException;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void insertDataIntoInvoice(Long userId, InvoiceEntity invoiceEntity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            UsersEntity usersEntity = session.get(UsersEntity.class,userId);
            invoiceEntity.setUser(usersEntity);
            session.save(invoiceEntity);
            session.save(usersEntity);
            tx.commit();
        }
        catch (ConstraintViolationException exc){
            throw new InvoiceAlreadyExistsException(invoiceEntity);
        }
        catch (Exception exc){
            throw new OpenSessionException("Something goes wrong. Try again");
        }
    }

    @Override
    public InvoiceEntity getInvoiceByBill(String bill) {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from InvoiceEntity where billNumber = :paramName");
            query.setParameter("paramName", bill);
            return (InvoiceEntity) query.getSingleResult();
        }
        catch (Exception exc){
            throw new OpenSessionException("Something goes wrong. Try again");
        }
    }

    @Override
    public InvoiceEntity getInvoiceById(Long id) {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from InvoiceEntity where id = :paramName");
            query.setParameter("paramName", id);
            return (InvoiceEntity) query.getSingleResult();
        }
        catch (Exception exc){
            throw new OpenSessionException("Something goes wrong. Try again");
        }
    }

    @Override
    public List<InvoiceEntity> getAllInvoices() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM InvoiceEntity ");
            List<InvoiceEntity> allBill = query.list();
            return allBill;
        } catch (Exception exc) {
            throw new OpenSessionException("Something goes wrong. Try again");
        }
    }

}
