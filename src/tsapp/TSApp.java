/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import tsapp.component.MyViewReference;
import tsapp.view.Login_view;

/**
 *
 * @author Huynh
 */
public class TSApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    JFrame f = new JFrame();
    f.setLayout(new BorderLayout());
    final Login_view p = new Login_view();
    
    f.add(p, BorderLayout.CENTER);
    f.pack();
    f.setVisible(true);
 
      
    }
    
}
