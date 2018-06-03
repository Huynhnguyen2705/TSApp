/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.component;

import java.awt.Button;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Huynh
 */
public class myButton extends JButton {
    public myButton() {
        super();
    }

    public myButton(String text) {
        super(text);
        super.setPreferredSize(new Dimension(80, 30));
        super.setVerticalTextPosition(SwingConstants.CENTER);
        super.setHorizontalTextPosition(SwingConstants.CENTER);
//        super.setBackground(new Color(183, 205, 175));
        super.setBackground(Color.WHITE);
        super.setBorder(new LineBorder(new Color(67, 98, 72)));
        super.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
        super.setForeground(BLACK);
        super.setContentAreaFilled(false);
        super.setOpaque(true);
    }

    public myButton(String text, String iconURL) throws IOException {
        Image img = ImageIO.read(getClass().getClassLoader().getResource(iconURL));
        Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        super.setIcon(new ImageIcon(newimg));
        super.setText(text);
        super.setVerticalTextPosition(SwingConstants.BOTTOM);
        super.setHorizontalTextPosition(SwingConstants.CENTER);
        super.setBackground(new Color(255, 153, 153));
        super.setBorder(new LineBorder(new Color(67, 98, 72), 5));
        super.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
        super.setForeground(BLACK);
        super.setContentAreaFilled(false);
        super.setOpaque(true);

    }

    public myButton(String text, String iconURL, int size, int borderThickness) throws IOException {
        Image img = ImageIO.read(getClass().getClassLoader().getResource(iconURL));
        Image newimg = img.getScaledInstance(size, size, java.awt.Image.SCALE_SMOOTH);
        super.setIcon(new ImageIcon(newimg));
        super.setText(text);
        super.setVerticalTextPosition(SwingConstants.CENTER);
        super.setHorizontalTextPosition(SwingConstants.RIGHT);
        super.setBackground(new Color(255, 153, 153));
        super.setBorder(new LineBorder(new Color(67, 98, 72), borderThickness));
        super.setForeground(BLACK);
        super.setContentAreaFilled(false);
        super.setOpaque(true);
        // Button Pressed
        
    }
    
}
