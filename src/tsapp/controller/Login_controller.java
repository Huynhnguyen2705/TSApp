/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.controller;

import Entity.Employee;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tsapp.model.Login_model;

/**
 *
 * @author Huynh
 */
public class Login_controller {
    private final Login_model model = new Login_model();
    
    public Employee getEmpLogin(String username, String password){
        try {
            return model.getEmpLogin(username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Login_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

