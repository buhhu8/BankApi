package ru.sberdorofeev.bankapi.utils;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@AllArgsConstructor
public final class HibernateUtils {

    private static SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration()
                .configure();

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactory getFactory(){
        return sessionFactory;
    }


}
