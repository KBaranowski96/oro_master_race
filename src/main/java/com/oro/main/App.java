/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oro.main;

import com.oro.entity.User;
import com.oro.util.UserUtil;
import com.oro.util.Util;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
/**
 *
 * @author Kamil
 */
public class App {
     public static void main( String[] args )
    {
        Util util = new Util();
        util.beginSession();
        
        UserUtil.createUser(util.getSession());
        
        util.endSession();
    }    
}
