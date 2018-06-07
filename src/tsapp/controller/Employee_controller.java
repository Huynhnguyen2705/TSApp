/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.controller;

import Entity.Employee;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tsapp.model.Employee_model;

/**
 *
 * @author Huynh
 */
public class Employee_controller {
    
    Employee_model model = new Employee_model();
    
    
    public ArrayList<Employee> searchEmployee(String keyword){
        ArrayList<Employee> rs = new ArrayList<>();
        try {
            rs = model.searchEmployee(keyword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Employee_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    public boolean insertEmployee(Employee e, String passwd){
        return model.insertEmployee(e,passwd);
    }
    
    public boolean updateAccount(Employee e, String passwd){
        return model.updateAccount(e,passwd);
    }
    
    public boolean updateEmployee(Employee e){
        return model.updateEmployee(e);
    }
    
}
