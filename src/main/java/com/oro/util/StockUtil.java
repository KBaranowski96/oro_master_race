/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.util;

import com.oro.entity.Stock;
import static com.oro.util.Util.getSession;
import static com.oro.util.Util.prepareSession;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author Laptopek
 */
public class StockUtil extends Util{
    public static List<Stock> getBooks() {
        prepareSession();
        String hql = "FROM Stock WHERE Quantity > 0";
        TypedQuery<Stock> query = getSession().createQuery(hql);
        List<Stock> results = query.getResultList();
        if (!results.isEmpty()) {
            return results;
        } else {
            return null;
        }
    }
    public static Stock getBookById(Integer ID) {
        prepareSession();
        String hql = "FROM Stock WHERE BookID = :n";
        TypedQuery<Stock> query = getSession().createQuery(hql);
        query.setParameter("n", ID).setMaxResults(1);
        List<Stock> results = query.getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        } else {
            return null;
        }
    }
    
    public static Stock addBook(String title, Integer quantity){
        prepareSession();
        Stock book = new Stock(title,quantity);
        
        addToSession(book);
        commit();
        return book;
    }
}
