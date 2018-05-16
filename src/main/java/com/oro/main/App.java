package com.oro.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.oro.entity.User;
import com.oro.util.UserUtil;
import com.oro.util.Util;
import java.util.List;
import java.util.Scanner;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author Kamil
 */
public class App{
    private static User currentUser;
    private static int flag = 0;
    public static void main(String[] args){
        Util.openSession();
        UserUtil.createUser("root", "root", "admin");
        
        login();
        
    }    
    
    public static void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Type Login");
        String name = sc.nextLine();
        System.out.println("Type Password");
        String pass = sc.nextLine();
        sc.close();
        currentUser = UserUtil.getUserByUsername(name);
        if(currentUser == null){
            signUp(name);
        }else if(currentUser.getPassword() != pass){
            System.out.println("Wrong password");
            ++flag;
            System.exit(0);
        }
        System.out.println("You have successfully logged in!");       
    }
    
    public static void signUp(String name){
        Scanner sc2 = new Scanner(System.in);
        System.out.println("You do not have an account");
        System.out.println("Register now");
        System.out.println("Type new password");
        String pass = sc2.nextLine();
        UserUtil.createUser(name, pass, "casual");
        System.out.println("Succesfuly registered");
    }
}
