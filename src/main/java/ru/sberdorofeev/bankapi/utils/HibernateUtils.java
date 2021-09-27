package ru.sberdorofeev.bankapi.utils;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.sberdorofeev.bankapi.model.entity.InvoiceEntity;
import ru.sberdorofeev.bankapi.model.entity.UsersEntity;


public final class HibernateUtils {

    private static SessionFactory sessionFactory;

    public HibernateUtils() {
    }

    static {
        Configuration configuration = new Configuration()
                .configure();

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getFactory(){
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(InvoiceEntity.class);
                configuration.addAnnotatedClass(UsersEntity.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }


}
