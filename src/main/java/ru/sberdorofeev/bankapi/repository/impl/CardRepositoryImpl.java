package ru.sberdorofeev.bankapi.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.sberdorofeev.bankapi.model.entity.CardEntity;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.repository.CardRepository;
import ru.sberdorofeev.bankapi.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    private final SessionFactory sessionFactory;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void createNewCard(String billNumber, CardEntity cardEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);
            cardEntity.setInvoiceEntity(invoiceEntity);
            session.save(cardEntity);
            tx.commit();
        }
    }

    @Override
    public void increaseBalance(String cardNumber, BigDecimal balance) {
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            CardEntity cardEntity = session
                    .createQuery("from CardEntity where cardNumber = :cardNumber", CardEntity.class)
                    .setParameter("cardNumber", cardNumber)
                    .getSingleResult();

            BigDecimal existedBalance = cardEntity.getInvoiceEntity().getBalance();
            BigDecimal result = existedBalance.add(balance);
            cardEntity.getInvoiceEntity().setBalance(result);
            session.save(cardEntity);
            tx.commit();
        }
    }

    @Override
    public BigDecimal checkBalance(String cardNumber) {
        try (Session session = sessionFactory.openSession()) {
            CardEntity cardEntity = session
                    .createQuery("from CardEntity where cardNumber = :cardNumber", CardEntity.class)
                    .setParameter("cardNumber", cardNumber)
                    .getSingleResult();

            String billNumber = cardEntity.getInvoiceEntity().getBillNumber();
            BigDecimal existedBalance = cardEntity.getInvoiceEntity().getBalance();
            return existedBalance;
        }
    }

    @Override
    public List<CardEntity> getAllCards() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from CardEntity ", CardEntity.class)
                    .getResultList();
        }
    }

    @Override
    public CardEntity getInfoById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(CardEntity.class, id);
        }
    }

    @Override
    public CardEntity getCardByCardNumber(String cardNumber) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("from CardEntity where cardNumber = :paramName");
            query.setParameter("paramName", cardNumber);
            CardEntity cardEntity = (CardEntity) query.getSingleResult();
            return cardEntity;
        }
    }
}