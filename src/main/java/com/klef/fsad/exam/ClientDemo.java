package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.Date;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // I. INSERT RECORD (Persistent Object)
        Restaurant r = new Restaurant("Food Palace", new Date(), "Open", "Hyderabad");
        session.save(r);

        System.out.println("Record Inserted Successfully");

        // II. UPDATE using HQL with Named Parameters
        String hql = "UPDATE Restaurant SET name = :name, status = :status WHERE id = :id";
        Query query = session.createQuery(hql);

        query.setParameter("name", "Spicy Hub");
        query.setParameter("status", "Closed");
        query.setParameter("id", r.getId());

        int result = query.executeUpdate();

        System.out.println("Rows Updated: " + result);

        tx.commit();
        session.close();
    }
}