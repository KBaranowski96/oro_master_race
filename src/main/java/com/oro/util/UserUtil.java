/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.util;

import com.oro.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kamil
 */
public class UserUtil{
    public static void createUser(Session session){
        Transaction tx = session.beginTransaction();
        
        User user = new User("root","root","root");
        
        session.save(user);
        tx.commit();
    }
}
