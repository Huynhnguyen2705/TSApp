/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.controller;

import Entity.Customer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tsapp.model.Customer_model;

/**
 *
 * @author Huynh
 */
public class Customer_controller {
    
    Customer_model model = new Customer_model();
    
    
    public ArrayList<Customer> searchCustomer(String keyword){
        ArrayList<Customer> rs = new ArrayList<>();
        try {
            rs = model.searchCustomer(keyword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Customer_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public Customer searchCustomerID(String ID){
        try {
            return model.searchCustomerID(ID);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Customer_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean insertCustomer(Customer cus){
        return model.insertCustomer(cus);
    }
    
    public boolean updateCustomer(Customer cus){
        return model.updateCustomer(cus);
    }
    
}
