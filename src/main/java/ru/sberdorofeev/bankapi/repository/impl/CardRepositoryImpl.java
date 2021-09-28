package ru.sberdorofeev.bankapi.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.model.dto.CardDto;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.CardRepository;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    private final SessionFactory sessionFactory;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void produceNewCard(String billNumber, CardEntity cardEntity) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);
        cardEntity.setInvoiceEntity(invoiceEntity);
        session.save(cardEntity);
        tx.commit();
        session.close();
    }

    @Override
    public List<CardEntity> showAllCards(String billNumber) {
        Session session = sessionFactory.openSession();
        InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);

        session.close();
        return invoiceEntity.getCards();

    }

    @Override
    public void increaseBalance(String cardNumber, BigDecimal balance) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("from CardEntity where cardNumber = :paramName");
        query.setParameter("paramName", cardNumber);
        CardEntity cardEntity = (CardEntity) query.getSingleResult();
        String billNumber = cardEntity.getInvoiceEntity().getBillNumber();
        cardEntity.getInvoiceEntity().setBalance(balance);
        session.save(cardEntity);
        tx.commit();
        session.close();



    }

    @Override
    public BigDecimal checkBalance(String cardNumber) {
        return null;
    }
}
