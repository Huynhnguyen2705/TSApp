/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.component;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Huynh
 */
public class myTheme {
    
    
    public myTheme() {
        Color mainColor = new Color(255, 153, 153);
        Color color = new Color(51, 98, 140);

        // Button
//        UIManager.put("nimbusBase", color);
//        UIManager.put("nimbusBlueGrey", mainColor);
//        UIManager.put("control", color);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//              UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(myTheme.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
