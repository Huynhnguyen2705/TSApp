/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Customer;
import Entity.Employee;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import tsapp.component.CustomDocumentFilter;
import tsapp.controller.Customer_controller;

/**
 *
 * @author Huynh
 */
public class Customer_Edit extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private final Employee emp;
    private final String tenND;
    private final String THEM_KHACHHANG = ">Thêm";
    private Customer_controller controller;
    Customer cus;
    private String selectedID;

    public Customer_Edit(Employee emp) {
        initComponents();
        this.emp = emp;
        tenND = emp.getFullName();
        createUI();
        actionListener();
    }

    private void createUI() {
        usernameLabel.setText("Xin chào " + tenND);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        currentDateLabel.setText("Ngày " + dateFormat.format(date));

        ((AbstractDocument) phoneTF.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        phoneTF.addKeyListener(kl);

        controller = new Customer_controller();
        selectedID = CustomerSearch_view.selectedCustomerID;
        cus = controller.searchCustomerID(selectedID);
        CusIDLabel.setText(selectedID);
        NameTF.setText(cus.getFullName());
        phoneTF.setText(cus.getPhoneNumber());

    }

    private KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            textFieldKeyTyped(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    private void textFieldKeyTyped(java.awt.event.KeyEvent evt) {
        if (phoneTF.getText().length() >= 11) {
            evt.consume();
        }
    }

    public final void actionListener() {
        // Thoát
        btnBack.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedComponent(CustomerSearch_view.searchView);
            Main.tabbedPane.remove(3);
        });
        btnCancel.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedComponent(CustomerSearch_view.searchView);
            Main.tabbedPane.remove(3);
        });

        btnEdit.addActionListener((ActionEvent e) -> {
            if (updateCus()) {
                JOptionPane.showMessageDialog(null, "Cập nhật khách hàng thành công");
                Main.tabbedPane.remove(3);
                Main.addTabBottomDown(Main.KHACHHANG, Main.kh);
                Main.tabbedPane.add("Tra cứu", new CustomerSearch_view(emp, false));
                Main.tabbedPane.setSelectedIndex(2);
                
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật không thành công");
            }
        });

    }

    private boolean updateCus() {

        Customer cus = new Customer();
        cus.setID(CusIDLabel.getText());
        cus.setFullName(NameTF.getText());
        cus.setPhoneNumber(phoneTF.getText());

        if (controller.updateCustomer(cus)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usernameLabel = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        lablelKH = new javax.swing.JLabel();
        currentDateLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        CusIDLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NameTF = new javax.swing.JTextField();
        phoneTF = new javax.swing.JTextField();
        btnCancel = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();

        setBackground(new java.awt.Color(25, 104, 192));
        setPreferredSize(new java.awt.Dimension(1056, 452));
        setLayout(null);

        usernameLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        usernameLabel.setText("Xin chào");
        add(usernameLabel);
        usernameLabel.setBounds(40, 20, 670, 40);

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBack.PNG"))); // NOI18N
        add(btnBack);
        btnBack.setBounds(40, 90, 130, 30);

        lablelKH.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        lablelKH.setText("Sửa");
        add(lablelKH);
        lablelKH.setBounds(410, 180, 240, 40);

        currentDateLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        currentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(currentDateLabel);
        currentDateLabel.setBounds(1010, 20, 300, 40);

        jPanel1.setLayout(null);

        CusIDLabel.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        CusIDLabel.setText("KH1");
        jPanel1.add(CusIDLabel);
        CusIDLabel.setBounds(20, 10, 60, 15);

        jLabel2.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel2.setText("Họ tên:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(150, 50, 70, 15);

        jLabel3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel3.setText("SĐT:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(150, 110, 50, 15);
        jPanel1.add(NameTF);
        NameTF.setBounds(200, 40, 250, 30);

        phoneTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(phoneTF);
        phoneTF.setBounds(200, 100, 250, 30);

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnHuyBlue.PNG"))); // NOI18N
        jPanel1.add(btnCancel);
        btnCancel.setBounds(220, 150, 110, 30);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnSuaBlue.PNG"))); // NOI18N
        jPanel1.add(btnEdit);
        btnEdit.setBounds(340, 150, 110, 30);

        add(jPanel1);
        jPanel1.setBounds(410, 230, 570, 230);

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CusIDLabel;
    private javax.swing.JTextField NameTF;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnEdit;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lablelKH;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
