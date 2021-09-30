package ru.sberdorofeev.bankapi.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class InvoiceRepositoryImpl implements InvoiceRepository {

    private final SessionFactory sessionFactory;

    @Override
    public void insertInvoice(Long userId, InvoiceEntity invoiceEntity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            UsersEntity usersEntity = session.get(UsersEntity.class,userId);
            invoiceEntity.setUser(usersEntity);
            session.save(invoiceEntity);
            session.save(usersEntity);
            tx.commit();
        }
    }

    @Override
    public InvoiceEntity getInvoiceByBill(String bill) {
        try(Session session = sessionFactory.openSession()){
            InvoiceEntity invoiceEntity = session
                    .createQuery("from InvoiceEntity where billNumber = :bill", InvoiceEntity.class)
                    .setParameter("bill", bill)
                    .getSingleResult();
            return invoiceEntity;
        }
    }

    @Override
    public InvoiceEntity getInvoiceById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            InvoiceEntity invoiceEntity = session
                    .createQuery("from InvoiceEntity where id = :id", InvoiceEntity.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return invoiceEntity;
        }
    }

    @Override
    public List<InvoiceEntity> getAllInvoices() {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM InvoiceEntity ");
            List<InvoiceEntity> allBill = query.list();
            return allBill;
        }
    }

}
