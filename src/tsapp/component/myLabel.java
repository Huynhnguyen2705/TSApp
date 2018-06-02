/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.component;

import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author Huynh
 */
public class myLabel extends JLabel{
    
     public myLabel(String text) {
        super(text);
        super.setForeground(BLACK);
        super.setFont(new Font("Myriad Pro", Font.PLAIN, 15));

    }

    public myLabel(String text, int fontStyle, int fontSize) {
        super(text);
        super.setFont(new Font("Myriad Pro", fontStyle, fontSize));
        super.setForeground(BLACK);
    }

    public void setTitle() {
        super.setFont(new Font("Myriad Pro", Font.BOLD, 20));
        super.setHorizontalAlignment(JLabel.CENTER);
        super.setForeground(BLACK);

    }

    
}
