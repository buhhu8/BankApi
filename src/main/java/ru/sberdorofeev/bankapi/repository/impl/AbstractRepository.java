package ru.sberdorofeev.bankapi.repository.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Consumer;

@RequiredArgsConstructor
public class AbstractRepository {

    protected final SessionFactory factory;

    protected void runInTransaction(Consumer<Session> consumer) {
        Transaction tx = null;

        try(Session session = factory.openSession()){
            tx = session.beginTransaction();

            consumer.accept(session);

            tx.commit();
        } catch ( Exception exc) {
            if (tx!= null){
                System.out.println("Rollback transaction");
                tx.rollback();
            }
        }

    }

    protected void runWithoutTransaction(Consumer<Session> consumer) {
        try (Session session = factory.openSession()) {
            consumer.accept(session);
        }
    }


}
