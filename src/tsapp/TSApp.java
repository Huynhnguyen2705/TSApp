/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import tsapp.component.myTheme;
import tsapp.view.Login;

/**
 *
 * @author Huynh
 */
public class TSApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        myTheme myTheme = new myTheme();
        SwingUtilities.invokeLater(() -> {
            try {
                JFrame frame = new Login();
                frame.setSize(1280, 800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.pack();
            } catch (Exception ex) {
                Logger.getLogger(TSApp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
