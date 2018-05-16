/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.util;

import com.oro.entity.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Kamil
 */
public class UserUtil extends Util{
    public static User getUserByUsername (String name) {
        prepareSession();
        String hql = "FROM User WHERE name = :n";
        TypedQuery<User> query = getSession().createQuery(hql);
        query.setParameter("n", name).setMaxResults(1);
        List<User> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }

    public static User createUser(String name, String password, String role){
        prepareSession();
        
        User user = new User(name,password,role);
        
        addToSession(user);
        commit();
        return user;
    }
    
    
}
