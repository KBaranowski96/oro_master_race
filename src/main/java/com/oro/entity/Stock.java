/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.entity;

import javax.persistence.*;
/**
 *
 * @author Kamil
 */
@Entity
@Table(name="Stock")
public class Stock {
    @Id
    @Column(name="BookID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer bookid;
    
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "stockDetails")
    private Transaction transaction;
    
    @Column(name="Name")
    private String name;
      
    @Column(name="Quantity")
    private Integer quantity;

    @Override
    public String toString() {
        return "BookID:" + bookid + ", Title:" + name;
    }

    public Stock() {
    }

    
    public Stock(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    
}
