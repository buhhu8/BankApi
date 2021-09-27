package ru.sberdorofeev.bankapi.repository.impl;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.exception.userExc.UserAlreadyExistsException;
import ru.sberdorofeev.bankapi.model.InvoiceBillEnum;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;
import ru.sberdorofeev.bankapi.utils.HibernateUtils;

import java.sql.Timestamp;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    SessionFactory sessionFactory = HibernateUtils.getFactory();

    @Override
    public void insertDataIntoInvoice(InvoiceEntity invoiceEntity) {
        Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(invoiceEntity);
            tx.commit();
            session.close();

//        catch (Exception exc){
//            System.out.println("Some problem");
//        }
    }

    @Override
    public InvoiceEntity getInvoiceByBill(String bill) {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from InvoiceEntity where billNumber = :paramName");
            query.setParameter("paramName", bill);
            return (InvoiceEntity) query.getSingleResult();
        }
        catch (Exception exc){
            System.out.println("Some problem");
            return new InvoiceEntity();
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
            System.out.println("Some problem");
            return new InvoiceEntity();
        }
    }

    @Override
    public void increaseBalance(Long id) {

    }
}
