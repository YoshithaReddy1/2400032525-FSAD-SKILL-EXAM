package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class ClientDemo 
{
    public static void main(String[] args) 
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // ---------------- CREATE ----------------
        Restaurant r = new Restaurant();
        r.setId(1);
        r.setName("Paradise");
        r.setDate(new Date());
        r.setStatus("Open");

        session.save(r);
        System.out.println("Record Inserted");

        // ---------------- READ ----------------
        Query<Restaurant> readQuery = session.createQuery("from Restaurant", Restaurant.class);
        List<Restaurant> list = readQuery.list();

        for(Restaurant res : list)
        {
            System.out.println(res.getId()+" "+res.getName()+" "+res.getStatus());
        }

        // ---------------- UPDATE ----------------
        String hql = "update Restaurant set name=:name, status=:status where id=:id";

        Query updateQuery = session.createQuery(hql);
        updateQuery.setParameter("name","KFC");
        updateQuery.setParameter("status","Closed");
        updateQuery.setParameter("id",1);

        updateQuery.executeUpdate();
        System.out.println("Record Updated");

        // ---------------- DELETE ----------------
        String deleteHQL = "delete from Restaurant where id=:id";

        Query deleteQuery = session.createQuery(deleteHQL);
        deleteQuery.setParameter("id",1);

        deleteQuery.executeUpdate();
        System.out.println("Record Deleted");

        tx.commit();
        session.close();
    }
}