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
            System.out.println("1: Login");
            System.out.println("2: Register");
            System.out.println("0: Exit");
            String menu = sc.nextLine();
            switch(Integer.valueOf(menu))
            {
                case 1:
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
                    break;
                case 2:
                    signUp();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Wrong code");
                    break;
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
            System.out.println("3: Return a book");
            System.out.println("4: Logout");
            if(currentUser.getRole().compareTo("admin") == 0)
            {
                System.out.println("5: Add book into Stock");
                System.out.println("6: Print all users");
                System.out.println("7: Add user");
            }

            String choose = sc.nextLine();
            switch(Integer.valueOf(choose))
            {
                case 1: 
                    List<Stock> stock = StockUtil.getBooks();
                    if(stock != null){
                        while(!stock.isEmpty())
                        {
                            if(currentUser.getRole().equals("admin"))
                            {
                                Stock item = stock.remove(0);
                                System.out.print(item);
                                System.out.println(", Quantity:" + item.getQuantity());
                            }
                            else
                            {
                                System.out.println(stock.remove(0));
                            }
                               
                        }
                    }
                    break;
                case 2:
                    System.out.println("Enter BookID");
                    String bookID = sc.nextLine();
                    TransactionUtil.borrowBook(currentUser,Integer.valueOf(bookID));
                    break;
                case 3:
                    System.out.println("Whitch book do you return?");
                    System.out.println("Enter BookID");
                    TransactionUtil.showMyBooks(currentUser);
                    String delete = sc.nextLine();
                    TransactionUtil.returnBook(StockUtil.getBookById(Integer.valueOf(delete)), currentUser);                
                    break;
                case 4:
                    loginFlag = false;
                    break;
                case 5:
                    if(currentUser.getRole().compareTo("admin") == 0)
                    {
                        System.out.println("Enter Book Title");
                        String title = sc.nextLine();
                        System.out.println("Enter Quantity");
                        String quantity = sc.nextLine();
                        StockUtil.addBook(title,Integer.valueOf(quantity));
                    }
                    else
                    {
                        System.out.println("Wrong Code");  
                    }
                    break;
                case 6:
                    if(currentUser.getRole().compareTo("admin") == 0)
                    {
                        UserUtil.printUsers();
                    }
                    else
                    {
                        System.out.println("Wrong Code");  
                    }
                    break;
                case 7:
                    if(currentUser.getRole().compareTo("admin") == 0)
                    {
                        System.out.println("Enter username");
                        String username = sc.nextLine();
                        System.out.println("Enter password");
                        String password = sc.nextLine();
                        System.out.println("Choose role");
                        System.out.println("1: admin");
                        System.out.println("2: casual");
                        String ch = sc.nextLine();
                        switch(Integer.valueOf(ch)){
                            case 1:
                                UserUtil.createUser(username, password, "admin");
                            case 2:
                                UserUtil.createUser(username, password, "casual");
                            default:
                                System.out.println("Something went wrong");
                        }                        
                    }
                    else
                    {
                        System.out.println("Wrong Code");  
                    }
                    break;
                default:
                    System.out.println("Wrong Code");
                    break;
            }
        }
   
    }
    
    public static void login(){
        int i = 0;
        while(passFlag)
        {           
            System.out.println("Type Login");
            String name = sc.nextLine();
            System.out.println("Type Password");
            String pass = sc.nextLine();
            currentUser = UserUtil.getUserByUsername(name);
            if(currentUser == null)
            {
                System.out.println("Wrong username or password");
            }
            else if(currentUser.getPassword().compareTo(pass) != 0)
            {
                System.out.println("Wrong username or password");
            }
            else
            {   
                passFlag = false;
            }
            i++;
            if(i>=3){
                System.out.println("Too many faild attempts");
                System.out.println("Exit");
                System.exit(0);
            }
        }
        System.out.println("You have successfully logged in!");  
    }
    
    public static void signUp(){
        System.out.println("You do not have an account");
        System.out.println("Register now");
        System.out.println("Type username");
        String username = sc.nextLine();
        System.out.println("Type new password");
        String pass = sc.nextLine();
        UserUtil.createUser(username, pass, "casual");
        System.out.println("Succesfuly registered");
        currentUser = UserUtil.getUserByUsername(username);
    }
}
