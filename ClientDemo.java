package com.klef.endlabexam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        // Initialize SessionFactory
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Open a Session
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            // HQL Update using Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            Query query = session.createQuery(hql);
            query.setParameter(1, "Updated Name");
            query.setParameter(2, "Updated Location");
            query.setParameter(3, 1);

            int rowsAffected = query.executeUpdate();
            System.out.println(rowsAffected + " rows updated.");

            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            // Close the session
            session.close();
            sessionFactory.close();
        }
    }
}

