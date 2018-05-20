package com.oro.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.oro.entity.Stock;
import com.oro.entity.User;
import com.oro.util.StockUtil;
import com.oro.util.TransactionUtil;
import com.oro.util.UserUtil;
import com.oro.util.Util;
import java.util.List;
import java.util.Scanner;
import javax.persistence.TypedQuery;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author Kamil
 */
public class App{
    private static User currentUser;
    private static boolean passFlag = true;
    private static boolean loginFlag = true;
    private static boolean exitFlag = false;
    private static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args){
        Util.openSession();
        if (UserUtil.getUserByUsername("root") == null)
        {
            UserUtil.createUser("root", "root", "admin");
        }
        while(!exitFlag)
        {
            login();
            library();  
            System.out.println("Wanna exit? y/n");
            String exit = sc.nextLine();
            if(exit.equals("y"))
            {
                exitFlag = true;
            }
            else
            {
                currentUser = null;
                loginFlag = true;
                passFlag = true;
            }
        }
        
        
        sc.close();
        System.exit(0);
    }    
    
    public static void library(){
        while(loginFlag)
        {
            System.out.println("What would you like to do?");
            System.out.println("1: Browse books");
            System.out.println("2: Borrow a book");
            System.out.println("3: Logout");
            if(currentUser.getRole().compareTo("admin") == 0)
                System.out.println("4: Return a book");
                System.out.println("5: Add book into Stock");

            String choose = sc.nextLine();
            switch(Integer.valueOf(choose))
            {
                case 1: 
                    List<Stock> stock = StockUtil.getBooks();
                    while(!stock.isEmpty())
                    {
                        System.out.println(stock.remove(0));   
                    }
                    break;
                case 2:
                    System.out.println("Enter BookID");
                    String bookID = sc.nextLine();
                    TransactionUtil.borrowBook(currentUser,Integer.valueOf(bookID));
                    break;
                case 3:
                    loginFlag = false;
                    break;
                case 4:
                    System.out.println("Whitch book has been returned?");
                    int i = currentUser.getUserid();
                    break;
                case 5:
                    if(currentUser.getRole().compareTo("admin") == 0){
                        System.out.println("Enter Book Title");
                        String title = sc.nextLine();
                        System.out.println("Enter Quantity");
                        String quantity = sc.nextLine();
                        StockUtil.addBook(title,Integer.valueOf(quantity));
                    }
                    break;
                default:
                    System.out.println("Wrong Code");
                    break;
            }
        }
   
    }
    
    public static void login(){
        
        System.out.println("Type Login");
        String name = sc.nextLine();
        
        while(passFlag){
            System.out.println("Type Password");
            String pass = sc.nextLine();
            currentUser = UserUtil.getUserByUsername(name);
            if(currentUser == null)
            {
                signUp(name,sc);
                passFlag = false;
            }
            else if(currentUser.getPassword().compareTo(pass) != 0)
            {
                System.out.println("Wrong password");
            }
            else
            {
                passFlag = false;
            }
        }
        System.out.println("You have successfully logged in!");  
    }
    
    public static void signUp(String name, Scanner sc){
        System.out.println("You do not have an account");
        System.out.println("Register now");
        System.out.println("Type new password");
        String pass = sc.nextLine();
        UserUtil.createUser(name, pass, "casual");
        System.out.println("Succesfuly registered");
        currentUser = UserUtil.getUserByUsername(name);
    }
}
