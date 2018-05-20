/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Kamil
 */
@Entity
@Table(name="User")
public class User {
    @Id
    @Column(name="UserID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer userid;
    
    @Column(name="Name")
    private String name;
    
    @Column(name="Password")
    private String password;
    
    @Column(name="Role")
    private String role;

    @Column(name="Counter")
    private Integer counter;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Transaction> list = new ArrayList<Transaction>();

    @Override
    public String toString() {
        return "User{" + "userid=" + userid + ", name=" + name + ", password=" + password + ", role=" + role + ", counter=" + counter + '}';
    }

    public User() {
    }

    public User(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
        this.counter = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public List<Transaction> getList() {
        return list;
    }

    public void setList(List<Transaction> list) {
        this.list = list;
    }

    public Integer getUserid() {
        return userid;
    }
    
    
}
