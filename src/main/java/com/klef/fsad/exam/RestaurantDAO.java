package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class RestaurantDAO 
{

    // CREATE
    public void insertRestaurant(Restaurant r)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.save(r);

        tx.commit();
        session.close();
    }

    // READ
    public List<Restaurant> viewRestaurants()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Query<Restaurant> query = session.createQuery("from Restaurant", Restaurant.class);
        List<Restaurant> list = query.list();

        session.close();
        return list;
    }

    // UPDATE using HQL
    public void updateRestaurant(int id,String name,String status)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "update Restaurant set name=:name, status=:status where id=:id";

        Query query = session.createQuery(hql);
        query.setParameter("name",name);
        query.setParameter("status",status);
        query.setParameter("id",id);

        query.executeUpdate();

        tx.commit();
        session.close();
    }

    // DELETE using HQL
    public void deleteRestaurant(int id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String hql = "delete from Restaurant where id=:id";

        Query query = session.createQuery(hql);
        query.setParameter("id",id);

        query.executeUpdate();

        tx.commit();
        session.close();
    }
}