/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.util;

import com.oro.entity.Stock;
import com.oro.entity.Transaction;
import com.oro.entity.User;
import static com.oro.util.Util.addToSession;
import static com.oro.util.Util.commit;
import static com.oro.util.Util.getSession;
import static com.oro.util.Util.prepareSession;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Laptopek
 */
public class TransactionUtil extends Util{
    public static Transaction borrowBook(User user, Integer bookid){
        prepareSession();
        Stock book = StockUtil.getBookById(bookid);
        if(book == null){
            System.out.println("No such book in stock");
            return null;
        }
        if(book.getQuantity() == 0)
        {
            System.out.println("Book unavailable");
            return null;
        }
        if(maybeAlreadyHaveOne(book,user) == null)
        {
            Transaction transaction = new Transaction(user,book);
            addToSession(transaction);
            commit();
            System.out.println("Book is yours");
            return transaction;
        }    
        else
        {
            System.out.println("You already have this book");
            return null;
        }
    }
    public static void returnBook(Stock book, User user){
        prepareSession();
        String hql = "FROM Transaction WHERE BookID = :n AND UserID = :m ";
        TypedQuery<Transaction> query = getSession().createQuery(hql);
        query.setParameter("n", book.getBookid());
        query.setParameter("m", user.getUserid());
        try{
           Transaction transaction = query.getSingleResult();
           getSession().delete(transaction);
           commit();
        }catch(Exception e){
            System.out.println("Wrong BookID");
        }
        
    }
    
    public static void showMyBooks(User user){
        prepareSession();
        String hql = "FROM Transaction WHERE UserID = :n";
        TypedQuery<Transaction> query = getSession().createQuery(hql);
        query.setParameter("n", user.getUserid()).setMaxResults(1);
        List<Transaction> results = query.getResultList();
        while(!results.isEmpty()) 
        {
            System.out.println(results.remove(0));
        }
    }
    public static Transaction maybeAlreadyHaveOne(Stock book, User user){
        prepareSession();
        String hql = "FROM Transaction WHERE BookID = :n and UserID = :m";
        TypedQuery<Transaction> query = getSession().createQuery(hql);
        query.setParameter("n", book.getBookid()).setMaxResults(1);
        query.setParameter("m", user.getUserid()).setMaxResults(1);
        List<Transaction> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
}
