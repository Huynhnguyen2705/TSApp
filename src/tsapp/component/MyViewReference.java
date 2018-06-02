/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.component;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

/**
 *
 * @author Huynh
 */
public class MyViewReference {
    private static Button myButton;
    private Label myTitle;
    private Label myText;
    
    public static Button myButton(String text){
        myButton = new Button(text);
        myButton.setBackground(Color.WHITE);
        myButton.setFont(new Font("Myriad Pro",Font.PLAIN,18));
        return myButton;
    }
    
    public static Button myButton(String text, Color color, String fontName, int fontStyle,int fontSize){
        myButton = new Button(text);
        myButton.setBackground(color);
        myButton.setFont(new Font(fontName,fontStyle,fontSize));
        return myButton;
    }
    
    
}
