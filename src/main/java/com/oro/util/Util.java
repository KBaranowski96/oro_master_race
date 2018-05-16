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

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession(){
        return session;
    }

    public static Session prepareSession() {
        openTransaction();
        return session;
    }

    public static void addToSession(Object e) {
        session.save(e);
    }

    public static void commit() {
        if(session.isOpen() && session.getTransaction().isActive()){
            session.getTransaction().commit();
        }
    }
    private static void openTransaction() {
        if (isOpenSession() || !session.getTransaction().isActive()) {
            session.beginTransaction();
        }
    }

    private static boolean isOpenSession(){
        if (session == null || !session.isOpen()) {
            return false;
        } else {
            return true;
        }
    }

    public static void openSession() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public static void closeSession() {
        session.close();
    }
}
