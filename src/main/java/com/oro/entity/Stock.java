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
    @Column(name="ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    @Column(name="Name")
    private String name;
    
    @Column(name="Cost")
    private int cost;
    
    @Column(name="Quantity")
    private int quantity;

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", name=" + name + ", cost=" + cost + ", quantity=" + quantity + '}';
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
