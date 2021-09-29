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
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CardRepositoryImpl implements CardRepository {

    private final SessionFactory sessionFactory;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void produceNewCard(String billNumber, CardEntity cardEntity) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);
            cardEntity.setInvoiceEntity(invoiceEntity);
            session.save(cardEntity);
            tx.commit();
        }
        catch (Exception exc){

        }

    }

    @Override
    public List<CardEntity> getInfoByBillNumber(String billNumber) {
        try(Session session = sessionFactory.openSession()){
            InvoiceEntity invoiceEntity = invoiceRepository.getInvoiceByBill(billNumber);
            return invoiceEntity.getCards();
        }
        catch (Exception exc){
            return new InvoiceEntity().getCards();
        }
    }

    @Override
    public void increaseBalance(String cardNumber, BigDecimal balance) {
        try(Session session = sessionFactory.openSession()){
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from CardEntity where cardNumber = :paramName");
            query.setParameter("paramName", cardNumber);
            CardEntity cardEntity = (CardEntity) query.getSingleResult();
            String billNumber = cardEntity.getInvoiceEntity().getBillNumber();
            BigDecimal existedBalance = cardEntity.getInvoiceEntity().getBalance();
            BigDecimal result = existedBalance.add(balance);
            cardEntity.getInvoiceEntity().setBalance(result);
            session.save(cardEntity);
            tx.commit();
        }
        catch (Exception exc){

        }
    }

    @Override
    public BigDecimal checkBalance(String cardNumber) {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("from CardEntity where cardNumber = :paramName");
            query.setParameter("paramName", cardNumber);
            CardEntity cardEntity = (CardEntity) query.getSingleResult();
            String billNumber = cardEntity.getInvoiceEntity().getBillNumber();
            BigDecimal existedBalance = cardEntity.getInvoiceEntity().getBalance();
            return existedBalance;
        }
        catch (Exception exc){
            return new BigDecimal(1);
        }

    }

    @Override
    public List<CardEntity> showAllCards() {
        try(Session session = sessionFactory.openSession()){
            Query query = session.createQuery("FROM CardEntity ");
            List<CardEntity> allCards = query.list();
            System.out.println(allCards.get(0));
            return allCards;
        }
        catch (Exception exx){
            return new ArrayList<>();
        }

    }

    @Override
    public CardEntity getInfoById(Long id) {
        try(Session session = sessionFactory.openSession()){
            CardEntity entity = session.get(CardEntity.class,id);
            return entity;
        }
        catch (Exception exc){
            return new CardEntity();
        }

    }
}
