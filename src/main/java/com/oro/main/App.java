/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.main;

import com.oro.entity.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Kamil
 */
public class App {
     public static void main( String[] args )
    {
        SessionFactory sessionFactory;
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = session.beginTransaction();
        User user = new User("root","root","root");
        session.save(user);
        tx.commit();
        session.close();
    }    
}
