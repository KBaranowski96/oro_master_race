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
@Table(name="Transaction")
public class Transaction {   
    @Id
    @Column(name="TransactionID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer transactionid;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserID")
    private User user;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BookID")
    private Stock stockDetails;

    public Transaction() {
    }

    public Transaction(User user, Stock stockDetails) {
        this.user = user;
        this.stockDetails = stockDetails;
    }

    public Integer getId() {
        return transactionid;
    }

    public void setId(Integer id) {
        this.transactionid = id;
    }

    public User getUser() {
        return user;
    }

    public void setUserid(User user) {
        this.user = user;
    }

    public Stock getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(Stock stockDetails) {
        this.stockDetails = stockDetails;
    }

  
}
