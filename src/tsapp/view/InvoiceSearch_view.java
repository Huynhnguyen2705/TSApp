/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Employee;
import Entity.Invoice;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import tsapp.component.CurrencyToWords;
import tsapp.component.myTable;
import tsapp.controller.Invoice_controller;

/**
 *
 * @author Huynh
 */
public class InvoiceSearch_view extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private final Employee emp;
    private final String tenND;
    private Invoice_controller controller;
    private ArrayList<Invoice> listInvoice;
    private DefaultTableModel dtm;
    public static String selectedID;
    public static JPanel searchView;
    private Date defaultDate1;
    private Date defaultDate2;
    private BigDecimal total;
    private boolean mouseEnable;

    public InvoiceSearch_view(Employee emp) {
        initComponents();
        this.emp = emp;
        tenND = emp.getFullName();
        this.searchView = this;
        createUI();
        actionListener();
        //searchEmp();

    }

    private void createUI() {
        usernameLabel.setText("Xin chào " + tenND);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        currentDateLabel.setText("Ngày " + dateFormat.format(date));
        mouseEnable = true;
        setDefaultDate();
        searchInvoice(0);
    }

    public final void setDefaultDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date date1 = cal.getTime();
        fromDatePicker.setDate(date1);
        Date date2 = new Date();
        toDatePicker.setDate(date2);

        fromDatePicker.setFormats("dd/MM/yyyy");
        toDatePicker.setFormats("dd/MM/yyyy");
        defaultDate1 = date1;
        defaultDate2 = date2;
    }

    private String formatStatue(int statue){
        if(statue == 1){
            return "Hợp lệ";
        }
        return "Không hợp lệ";
    }
    private void searchInvoice(int status) {
        dtm = new DefaultTableModel();
        controller = new Invoice_controller();
        Vector column = new Vector();
        column.add("STT");
        column.add("Mã HĐ");
        column.add("Ngày lập");
        column.add("Nhân viên");
        column.add("Khách hàng");
        column.add("Thành tiền");
        column.add("Ghi chú");
        column.add("Trạng thái");
        /* Set Column Header lên DefaultTableModel */
        dtm.setColumnIdentifiers(column);

        java.sql.Date fromDate = convertJavaDateToSqlDate(defaultDate1);
        java.sql.Date toDate = convertJavaDateToSqlDate(defaultDate2);
        if(status != 0){
            listInvoice = controller.searchInvoiceStauts(fromDate, toDate, status);
        }else{
             listInvoice = controller.searchInvoices(fromDate, toDate);
        }
       
        total = new BigDecimal(0);
        for (int i = 0; i < listInvoice.size(); i++) {
            Invoice item = listInvoice.get(i);
            Vector row = new Vector();
            row.add(i + 1);
            row.add(item.getID());
            row.add(convertSQLDateFormat(item.getCreatedDate()));
            row.add(item.getEmpAccount());
            row.add(item.getCusName());
            row.add(formatPrice(item.getTotalBill().toString()));
            total = total.add(item.getTotalBill());
            if (item.getNote() == null) {
                row.add("Không có ghi chú");
            } else {
                row.add(item.getNote());
            }
            row.add(formatStatue(item.getStatue()));

            dtm.addRow(row);
        }
        table.setModel(dtm);
        myTable.setTextCenter(table);
        resizeColumnWidth(table);
        table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        table.setAutoscrolls(true);
        totalNumberLbl.setText("Tổng tiền: " + formatPrice(total.toString()));
        ArrayList<String> rs = CurrencyToWords.readNum(total.toString().replace(".0000", ""));
        String totalText = "";
        for (int i = 0; i < rs.size(); i++) {
            totalText += rs.get(i) + " ";
        }
        totalText = totalText.substring(0, 1).toUpperCase() + totalText.substring(1);
        totalTextLbl.setText(totalText + "đồng");
    }

    private void searchInvoiceEMP(int status) {
        dtm = new DefaultTableModel();
        controller = new Invoice_controller();
        Vector column = new Vector();
        column.add("STT");
        column.add("ID");
        column.add("Nhân viên");
        column.add("Tổng số hóa đơn");
        column.add("Tổng doanh số");
        /* Set Column Header lên DefaultTableModel */
        dtm.setColumnIdentifiers(column);

        java.sql.Date fromDate = convertJavaDateToSqlDate(defaultDate1);
        java.sql.Date toDate = convertJavaDateToSqlDate(defaultDate2);
        listInvoice = controller.searchInvoiceEMP(fromDate, toDate, status);
        total = new BigDecimal(0);
        for (int i = 0; i < listInvoice.size(); i++) {
            Invoice item = listInvoice.get(i);
            Vector row = new Vector();
            row.add(i + 1);
            row.add(item.getEmpID());
            row.add(item.getEmpAccount());
            row.add(item.getBillNumbers());
            row.add(formatPrice(item.getTotalBill().toString()));
            total = total.add(item.getTotalBill());
            row.add(formatStatue(status));

            dtm.addRow(row);
        }
        table.setModel(dtm);
        myTable.setTextCenter(table);
        resizeColumnWidth(table);
        table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        table.setAutoscrolls(true);
        totalNumberLbl.setText("Tổng tiền: " + formatPrice(total.toString()));
        ArrayList<String> rs = CurrencyToWords.readNum(total.toString().replace(".0000", ""));
        String totalText = "";
        for (int i = 0; i < rs.size(); i++) {
            totalText += rs.get(i) + " ";
        }
        totalText = totalText.substring(0, 1).toUpperCase() + totalText.substring(1);
        totalTextLbl.setText(totalText + "đồng");
    }
    private String formatPrice(String price) {
        return price.replace(".0000", " Đồng");
    }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public final String convertSQLDateFormat(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
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

    private final void actionListener() {
        // Thoát
        btnBack.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedComponent(Main.invoice);
        });

        //datepicker
        fromDatePicker.addActionListener((ActionEvent e) -> {
            if (fromDatePicker.getDate().compareTo(toDatePicker.getDate()) > 0) {
                JOptionPane.showMessageDialog(this, "Từ ngày phải trước đến ngày", "Lỗi", JOptionPane.ERROR_MESSAGE);
                fromDatePicker.setDate(defaultDate1);
            } else {
                defaultDate1 = fromDatePicker.getDate();
                //search here
                     if(statusCkbx.isSelected()){
                    searchInvoice(1);
                }else if(empCkbx.isSelected()){
                    searchInvoiceEMP(1);
                }else{
                    searchInvoice(0);
                }
                 
            }
        });
        toDatePicker.addActionListener((ActionEvent e) -> {
            if (fromDatePicker.getDate().compareTo(toDatePicker.getDate()) > 0) {
                JOptionPane.showMessageDialog(this, "Từ ngày phải trước đến ngày", "Lỗi", JOptionPane.ERROR_MESSAGE);
                toDatePicker.setDate(defaultDate2);
            } else {
                defaultDate2 = toDatePicker.getDate();
                //search here
                if(statusCkbx.isSelected()){
                    searchInvoice(1);
                }else if(empCkbx.isSelected()){
                    searchInvoiceEMP(1);
                }else{
                    searchInvoice(0);
                }
                
                
                
            }
        });
        
        statusCkbx.addItemListener((ItemEvent e) -> {
            if(statusCkbx.isSelected()){
                empCkbx.setSelected(false);
                searchInvoice(1);
            }else{
                searchInvoice(2);
            }
        });

        empCkbx.addItemListener((ItemEvent e) -> {
            if(empCkbx.isSelected()){
                statusCkbx.setSelected(false);
                searchInvoiceEMP(1);
                mouseEnable = false;
            }else{
                searchInvoice(0);
                mouseEnable = true;
            }
        });
        table.addMouseListener(mouseListener);
     
    }

    private MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            selectedID = (String) table.getValueAt(table.getSelectedRow(), 1);
            if(mouseEnable){
                controller.callReport(selectedID);
            }else{
                controller.callReportEmp(selectedID);
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
        jLabel1 = new javax.swing.JLabel();
        fromDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        toDatePicker = new org.jdesktop.swingx.JXDatePicker();
        totalTextLbl = new javax.swing.JLabel();
        totalNumberLbl = new javax.swing.JLabel();
        empCkbx = new javax.swing.JCheckBox();
        statusCkbx = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(25, 104, 192));
        setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
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

        lablelKH.setFont(new java.awt.Font("Myriad Pro", 0, 24)); // NOI18N
        lablelKH.setText("Tra cứu:");
        add(lablelKH);
        lablelKH.setBounds(230, 110, 100, 40);

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
        jScrollPane1.setBounds(230, 220, 890, 360);

        jLabel1.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
        jLabel1.setText("Từ ngày:");
        add(jLabel1);
        jLabel1.setBounds(230, 170, 90, 30);
        add(fromDatePicker);
        fromDatePicker.setBounds(310, 170, 280, 30);

        jLabel2.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
        jLabel2.setText("Đến ngày:");
        add(jLabel2);
        jLabel2.setBounds(610, 170, 90, 30);
        add(toDatePicker);
        toDatePicker.setBounds(720, 170, 280, 30);

        totalTextLbl.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        totalTextLbl.setForeground(new java.awt.Color(255, 255, 255));
        totalTextLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalTextLbl.setText("Tám mươi triệu đồng");
        add(totalTextLbl);
        totalTextLbl.setBounds(230, 640, 890, 30);

        totalNumberLbl.setFont(new java.awt.Font("Calibri", 0, 24)); // NOI18N
        totalNumberLbl.setForeground(new java.awt.Color(255, 255, 255));
        totalNumberLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalNumberLbl.setText("Tổng tiền: 80000000 Đ");
        add(totalNumberLbl);
        totalNumberLbl.setBounds(230, 600, 890, 30);

        empCkbx.setText("Nhân viên");
        add(empCkbx);
        empCkbx.setBounds(1020, 190, 100, 20);

        statusCkbx.setText("Hợp lệ");
        add(statusCkbx);
        statusCkbx.setBounds(1020, 170, 100, 20);

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JCheckBox empCkbx;
    private org.jdesktop.swingx.JXDatePicker fromDatePicker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lablelKH;
    private javax.swing.JCheckBox statusCkbx;
    private javax.swing.JTable table;
    private org.jdesktop.swingx.JXDatePicker toDatePicker;
    private javax.swing.JLabel totalNumberLbl;
    private javax.swing.JLabel totalTextLbl;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
