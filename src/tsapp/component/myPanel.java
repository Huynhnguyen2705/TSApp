/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.component;

import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JPanel;

/**
 *
 * @author Huynh
 */
public class myPanel extends JPanel{
    Color color = new Color(204, 0, 0);
//    Color color = new Color(203, 220, 230);


    public myPanel() {
        super();
        super.setOpaque(true);
        super.setBackground(color);
    }

    public myPanel(LayoutManager layout) {
        super(layout);
        super.setOpaque(true);
        super.setBackground(color);

    }
}
