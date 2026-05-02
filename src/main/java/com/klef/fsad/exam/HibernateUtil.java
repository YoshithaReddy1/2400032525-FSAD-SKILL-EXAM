package com.klef.fsad.exam;

import javax.security.auth.login.Configuration;

public class HibernateUtil 
{
    private static final SessionFactory sessionFactory;

    static 
    {
        try 
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } 
        catch (Throwable ex) 
        {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() 
    {
        return sessionFactory;
    }
}