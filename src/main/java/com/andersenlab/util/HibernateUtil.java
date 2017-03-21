package com.andersenlab.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Class for creating sessionFactory and getting data from database
 *
 * @author Vlad Badilovskii
 * @version 100500
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration
                    .getProperties()).buildServiceRegistry();
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Failed to create sessionFactory object");
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Method for getting sessionFactory
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
