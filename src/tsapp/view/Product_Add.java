/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Employee;
import Entity.Product;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;
import tsapp.controller.Product_controller;

/**
 *
 * @author Huynh
 */
public class Product_Add extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private final Employee emp;
    private final String tenND;
    private final String THEM = ">Thêm";
    private Product_controller controller;
    private ArrayList<Product> listProd;

    public Product_Add(Employee emp) {
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

        controller = new Product_controller();
        listProd = controller.searchProduct("sp");
        CusIDLabel.setText("SP" + (listProd.get(0).getIDInt() + 1));
        try {
            typeCbx.setModel(new DefaultComboBoxModel(controller.getTypes()));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Product_Add.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            sizeCbx.setModel(new DefaultComboBoxModel(controller.getSizes()));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Product_Add.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public final void actionListener() {
        // Thoát
        btnBack.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedComponent(Main.prod_view);
        });

        btnAdd.addActionListener((ActionEvent e) -> {
            if (!NameTF.getText().isEmpty() && !priceTF.getText().isEmpty()) {
                if (insertProd()) {
                    JOptionPane.showMessageDialog(null, "Thêm sản phẩm thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm không thành công");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Không được để trống các ô giá trị tích đỏ!");
            }

        });

    }

    private boolean insertProd() {

        Product prod = new Product();
        prod.setID(CusIDLabel.getText());
        prod.setProdName(NameTF.getText());
        String priceString = priceTF.getText().replace(",","");
        prod.setPrice(new BigDecimal(priceString));
        String sizeID = controller.getMaSize((String) sizeCbx.getSelectedItem());
        String typeID = controller.getMaLSP((String) typeCbx.getSelectedItem());
        prod.setSizeID(sizeID);
        prod.setTypeID(typeID);
        prod.setStatus(1);
        if (controller.insertCustomer(prod)) {
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
        btnAdd = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        typeCbx = new javax.swing.JComboBox<>();
        sizeCbx = new javax.swing.JComboBox<>();
        priceTF = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

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
        lablelKH.setText("Thêm");
        add(lablelKH);
        lablelKH.setBounds(410, 150, 240, 40);

        currentDateLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        currentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(currentDateLabel);
        currentDateLabel.setBounds(1010, 20, 300, 40);

        jPanel1.setLayout(null);

        CusIDLabel.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        CusIDLabel.setText("SP1");
        jPanel1.add(CusIDLabel);
        CusIDLabel.setBounds(20, 10, 60, 15);

        jLabel2.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel2.setText("Tên SP:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(60, 70, 70, 15);

        jLabel3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel3.setText("Đơn giá:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(60, 120, 50, 15);

        NameTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(NameTF);
        NameTF.setBounds(150, 60, 250, 30);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnThemBlue.PNG"))); // NOI18N
        jPanel1.add(btnAdd);
        btnAdd.setBounds(220, 270, 110, 30);

        jLabel1.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel1.setText("Loại");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(60, 170, 40, 20);

        jLabel4.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel4.setText("Size");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(60, 220, 23, 15);

        typeCbx.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        typeCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        typeCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeCbxActionPerformed(evt);
            }
        });
        jPanel1.add(typeCbx);
        typeCbx.setBounds(150, 160, 250, 30);

        sizeCbx.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        sizeCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(sizeCbx);
        sizeCbx.setBounds(150, 210, 250, 30);

        priceTF.setFormatterFactory(new DefaultFormatterFactory(
            new NumberFormatter(new DecimalFormat("#,##0.00"))));
    priceTF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    priceTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
    priceTF.setMargin(new java.awt.Insets(5, 10, 5, 20));
    jPanel1.add(priceTF);
    priceTF.setBounds(150, 110, 250, 30);

    jLabel5.setForeground(new java.awt.Color(255, 0, 0));
    jLabel5.setText("*");
    jPanel1.add(jLabel5);
    jLabel5.setBounds(140, 110, 34, 14);

    jLabel6.setForeground(new java.awt.Color(255, 0, 0));
    jLabel6.setText("*");
    jPanel1.add(jLabel6);
    jLabel6.setBounds(140, 60, 34, 14);

    add(jPanel1);
    jPanel1.setBounds(410, 200, 520, 320);

    getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void typeCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeCbxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_typeCbxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CusIDLabel;
    private javax.swing.JTextField NameTF;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lablelKH;
    private javax.swing.JFormattedTextField priceTF;
    private javax.swing.JComboBox<String> sizeCbx;
    private javax.swing.JComboBox<String> typeCbx;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
