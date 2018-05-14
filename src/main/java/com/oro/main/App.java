/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.main;

import com.oro.entity.User;
import com.oro.util.UserUtil;
import com.oro.util.Util;
import java.util.List;
import java.util.Scanner;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Kamil
 */
public class App extends Util {
    public static void main(String[] args){
        beginSession();
        
        UserUtil.createUser(getSession());
        //login(getSession());
        
        endSession();
    }    
    public static void login(Session session){
        Scanner sc = new Scanner(System.in);
        System.out.println("Type Login");
        String name = sc.nextLine();
        System.out.println("Type Password");
        String pass = sc.nextLine();
        sc.close();
       
        beginTrans();
        String hql = "SELECT * FROM user WHERE name = " + name +
                " AND password = " + pass;
        TypedQuery<User> query = session.createQuery(hql);
        List<User> result = query.getResultList();
        System.out.println(result.toString());    
    }
}
