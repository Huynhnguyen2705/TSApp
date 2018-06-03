/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Employee;
import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import tsapp.component.myLabel;
import tsapp.component.myPanel;
import tsapp.controller.Login_controller;

/**
 *
 * @author Huynh
 */
public class Login_view extends JFrame {
    // Khai báo các Panel

    private Container c;
    private myLabel lblTitle;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private myLabel ErrorLabel;
    private myLabel ErrorLabel1;

    // BLL
    private final Login_controller controller = new Login_controller();
    private Employee emp = new Employee();

    public Login_view() throws IOException {
        super("Quản lý cửa hàng trà sữa");
        createGUI();
        setFocusTF();
        actionListener();
    }

    private void createGUI() {
        c = getContentPane();
        c.setBackground(new myPanel().getBackground());
        c.setLayout(new GridBagLayout());

        lblTitle = new myLabel("ĐĂNG NHẬP");
        lblTitle.setTitle();
        GridBagConstraints gc = new GridBagConstraints();
        gc.gridx = 0;
        gc.gridy = 0;
        c.add(lblTitle, gc);
        // Add Username
        lblUsername = new JLabel("Tài khoản");
        lblUsername.setFont(new Font("Myriad Pro", Font.PLAIN, 14));

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(250, 35));

        JPanel temp1 = new myPanel(new FlowLayout());

        temp1.add(lblUsername);
        temp1.add(txtUsername);

        //Add Password
        lblPassword = new JLabel("Mật khẩu ");
        lblPassword.setFont(new Font("Myriad Pro", Font.PLAIN, 14));

        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(250, 35));

        JPanel temp2 = new myPanel(new FlowLayout());

        txtUsername.setText("huynhntn");
        txtPassword.setText("admin");
        
        temp2.add(lblPassword);
        temp2.add(txtPassword);

        // Add 2 error
        ErrorLabel = new myLabel("Đăng nhập thất bại. Kiểm tra lại tài khoản và mật khẩu");
        ErrorLabel1 = new myLabel("Nhân viên này hiện tại không làm việc. Xin hãy đăng nhập bằng tài khoản khác");
        setErrorLabelVisible(false, false);

        // Tiến hành add 3 panel username, password  và button vào c
        gc.gridy = 1;
        c.add(temp1, gc);

        gc.gridy = 2;
        c.add(temp2, gc);

        gc.gridy = 3;
        c.add(ErrorLabel, gc);

        gc.gridy = 3;
        c.add(ErrorLabel1, gc);

    }

    private void setErrorLabelVisible(boolean error1, boolean error2) {
        ErrorLabel.setVisible(error1);
        ErrorLabel1.setVisible(error2);
    }

    private void actionListener() {
        txtUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });

        txtPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
    }

    private void Login() {

        emp = controller.getEmpLogin(txtUsername.getText(), txtPassword.getText());
        if (emp.getStatue() != 0) {
            if (emp.getStatue() == 2 || emp.getStatue() == 3) {
                setErrorLabelVisible(false, true);
            } else {
                setErrorLabelVisible(false, false);
                Main frame = new Main(emp);
                frame.setSize(1280, 800);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                this.dispose();

            }

        } else {
            setErrorLabelVisible(true, false);
        }

    }

    private void setFocusTF() {
//        txtUsername.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                txtUsername.setForeground(Color.BLACK);
//                txtUsername.setFont(new Font("Myriad Pro", Font.ITALIC, 18));
//                txtUsername.setText("");
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//            }
//        });

        txtPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                txtPassword.setForeground(Color.BLACK);
                txtPassword.setFont(new Font("Myriad Pro", Font.ITALIC, 18));
                txtPassword.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

}
