/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Employee;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Huynh
 */
public class Invoice_view extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private final Employee emp;
    private final String tenND;
    private final String THEM = ">Thêm";
    private final String TRACUU = ">Tra cứu";

    public Invoice_view(Employee emp) {
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

    }

    public final void actionListener() {
        // Thoát
        btnBack.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedIndex(0);

        });
        
        btnTraCuu.addActionListener((ActionEvent e) -> {
            Main.addTabBottomDown(Main.HOADON, Main.invoice);
            InvoiceSearch_view search_view = new InvoiceSearch_view(emp);
            Main.tabbedPane.add(TRACUU,search_view);
            Main.tabbedPane.setSelectedComponent(search_view);
        });
        
        btnThem.addActionListener((ActionEvent e) -> {
            Main.addTabBottomDown(Main.HOADON, Main.invoice);
            Invoice_Add add = new Invoice_Add(emp);
            Main.tabbedPane.add(THEM,add);
            Main.tabbedPane.setSelectedComponent(add);
        });
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
        btnThem = new javax.swing.JButton();
        btnTraCuu = new javax.swing.JButton();

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
        lablelKH.setText("BÁN HÀNG");
        add(lablelKH);
        lablelKH.setBounds(340, 160, 260, 60);

        currentDateLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        currentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(currentDateLabel);
        currentDateLabel.setBounds(1010, 20, 300, 40);

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnThemHD.PNG"))); // NOI18N
        btnThem.setText("jButton2");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });
        add(btnThem);
        btnThem.setBounds(760, 270, 290, 100);

        btnTraCuu.setBackground(new java.awt.Color(255, 255, 255));
        btnTraCuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnTraCuuHD.PNG"))); // NOI18N
        btnTraCuu.setText("jButton3");
        add(btnTraCuu);
        btnTraCuu.setBounds(340, 270, 300, 100);

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTraCuu;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JLabel lablelKH;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
