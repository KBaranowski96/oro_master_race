/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.util;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Kamil
 */
public class Util {
    private static SessionFactory sessionFactory;
    private static Session session;
    
    public static Session beginSession(){
        sessionFactory = new Configuration()
                .configure() // configures settings from hibernate.cfg.xml
                .buildSessionFactory();
        
        session = sessionFactory.openSession();
        return session;
    }
    public static void endSession(){
        session.close();
    }
    public static Session getSession(){
        return session;
    }
       
}
