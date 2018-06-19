/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Customer;
import Entity.Employee;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import tsapp.component.myTable;
import tsapp.controller.Customer_controller;

/**
 *
 * @author Huynh
 */
public class CustomerSearch_view extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private final Employee emp;
    private final String tenND;
    private Customer_controller controller;
    private ArrayList<Customer> listCus;
    private DefaultTableModel dtm;
    public static String selectedCustomerID;
    public static JPanel searchView;
    private boolean isIntoInvoice;

    public CustomerSearch_view(Employee emp, boolean isIntoInvoice) {
        initComponents();
        this.emp = emp;
        this.isIntoInvoice = isIntoInvoice;
        tenND = emp.getFullName();
        this.searchView = this;
        createUI();
        actionListener();
        searchCustomer();
        
    }

    private void createUI() {
        usernameLabel.setText("Xin chào " + tenND);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        currentDateLabel.setText("Ngày " + dateFormat.format(date));
    }
    
    

    private void searchCustomer() {
        dtm = new DefaultTableModel();
        controller = new Customer_controller();
        Vector column = new Vector();
        column.add("STT");
        column.add("Mã khách hàng");
        column.add("Họ và tên");
        column.add("Số điện thoại");
        column.add("Cấp độ");
        /* Set Column Header lên DefaultTableModel */
        dtm.setColumnIdentifiers(column);
        
        listCus = controller.searchCustomer(tracuuTF.getText());
        
        for (int i = 0; i < listCus.size(); i++) {
            Customer item = listCus.get(i);
            Vector row = new Vector();
            row.add(i + 1);
            row.add(item.getID());
            row.add(item.getFullName());
            row.add(item.getPhoneNumber());
            row.add(item.getCusType());
            dtm.addRow(row);
        }    
        
        table.setModel(dtm);
        resizeColumnWidth(table);
        myTable.setTextCenter(table);
        table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        table.setAutoscrolls(true);
        
    }
    
    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void actionListener() {
        // Thoát
        btnBack.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedComponent(Main.kh);
        });

        table.addMouseListener(mouseListener);
    }
    private final MouseListener  mouseListener = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                selectedCustomerID = (String) table.getValueAt(table.getSelectedRow(), 1);
                if(!isIntoInvoice){
                    Customer_Edit edit = new Customer_Edit(emp);
                    Main.addTabBottomDown(Main.KHACHHANG    , Main.kh);
                    Main.tabbedPane.add("Tra cứu",searchView);
                    Main.tabbedPane.add(">Sửa",edit);
                    Main.tabbedPane.setSelectedComponent(edit);
                }
                else{
                    Invoice_Add.CusID = selectedCustomerID;
                    Invoice_Add.cusTF.setText((String) table.getValueAt(table.getSelectedRow(), 2));
                    Main.tabbedPane.remove(searchView);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        };

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
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new myTable();
        tracuuTF = new javax.swing.JTextField();

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
        lablelKH.setText("Tra cứu:");
        add(lablelKH);
        lablelKH.setBounds(230, 160, 260, 40);

        currentDateLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        currentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(currentDateLabel);
        currentDateLabel.setBounds(1010, 20, 300, 40);

        table.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setColumnSelectionAllowed(true);
        table.setDragEnabled(true);
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(new java.awt.Color(25, 104, 192));
        jScrollPane1.setViewportView(table);
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        add(jScrollPane1);
        jScrollPane1.setBounds(230, 220, 890, 402);

        tracuuTF.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        tracuuTF.setForeground(new java.awt.Color(102, 102, 102));
        tracuuTF.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tracuuTF.setAlignmentY(0.0F);
        tracuuTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tracuuTFActionPerformed(evt);
            }
        });
        add(tracuuTF);
        tracuuTF.setBounds(370, 150, 750, 50);

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void tracuuTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tracuuTFActionPerformed
        // TODO add your handling code here:
        searchCustomer();
    }//GEN-LAST:event_tracuuTFActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lablelKH;
    private javax.swing.JTable table;
    private javax.swing.JTextField tracuuTF;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
