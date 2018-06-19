/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Employee;
import Entity.Invoice;
import Entity.InvoiceDetail;
import Entity.Product;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import tsapp.component.myTable;
import tsapp.controller.Invoice_controller;
import tsapp.controller.Product_controller;
import tsapp.component.myButton;

/**
 *
 * @author Huynh
 */
public class Invoice_Add extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private final int STT = 0;
    private final int TEN_MON = STT + 1;
    private final int SL = TEN_MON + 1;
    private final int DON_GIA = SL + 1;
    private final int BTN = DON_GIA + 1;
    private final Employee emp;
    private final String tenND;
    private Invoice_controller controller;
    private ArrayList<Invoice> listInvoice;
    public static String CusID;

    private DefaultTableModel dtm;
    private Product_controller prod_con;
    int cups = 0;
    private boolean isTopping;
    private BigDecimal total;
    int IDInt;
    int currentToppingQuantity = 0;
    int currentProdQuantity = 0;
    ArrayList<InvoiceDetail> listDetails;

    public Invoice_Add(Employee emp) {
        initComponents();
        this.emp = emp;
        tenND = emp.getFullName();
        createUI();
        actionListener();
    }

    private void createUI() {
        usernameLabel.setText(tenND);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        currentDateLabel.setText("Ngày " + dateFormat.format(date));

        controller = new Invoice_controller();

        listInvoice = controller.searchInvoiceID("");
        IDLabel.setText("HD" + (listInvoice.get(0).getIDInt() + 1));

        prod_con = new Product_controller();
        setComboBox();

        //btn number cups
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/Images/iconLy.PNG"));
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(30, 50, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newimg);
        jButton1.setFont(new Font("Myriad Pro", Font.PLAIN, 28));
        jButton1.setVerticalTextPosition(SwingConstants.BOTTOM);
        jButton1.setHorizontalTextPosition(SwingConstants.LEFT);
        jButton1.setIcon(icon);
        jButton1.setBackground(new Color(25, 104, 192));

        total = new BigDecimal(BigInteger.ZERO);
        setTable();

        listDetails = controller.searchInvoiceDetail("");
        IDInt = listDetails.get(0).getIDInt();
    }

    private void setComboBox() {
        try {
            Object[] items = prod_con.getTypesNoTopping();

            Object[] items1 = new Object[items.length - 1];
            DefaultComboBoxModel typecbModel = new DefaultComboBoxModel(items);
            typeCbx.setModel(typecbModel);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Invoice_Add.class.getName()).log(Level.SEVERE, null, ex);
        }

        String typeID = prod_con.getMaLSP("Topping");
        ArrayList<Product> prods = prod_con.searchProduct_TypeID(typeID, 1);

        String[] names = new String[prods.size()];
        for (int i = 0; i < prods.size(); ++i) {
            names[i] = prods.get(i).getProdName();
        }
        DefaultComboBoxModel toppingcbModel = new DefaultComboBoxModel(names);
        toppingCbx.setModel(toppingcbModel);

        typeCbx.setSelectedIndex(0);
        setNameCbBox(getItems("ProdType1"));
        setSizeCbBox();

    }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public final void actionListener() {
        // Thoát
        btnBack.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedComponent(Main.invoice);
        });

//        btnNumberCups.addActionListener((ActionEvent e) -> {
//                cups += 1;
//                numberCupsLbl.setText("Số ly: " + cups);
//            
//        });
        typeCbx.addItemListener((ItemEvent e) -> {
            String typeID = prod_con.getMaLSP((String) typeCbx.getSelectedItem());
            setNameCbBox(getItems(typeID));
            setSizeCbBox();
        });

        nameCbx.addItemListener((ItemEvent e) -> {
            setSizeCbBox();

        });

        btnAddTopping.addActionListener((ActionEvent e) -> {
            isTopping = true;
            //topping
            String toppingName = toppingCbx.getSelectedItem().toString();
            String toppingSize = "Topping";
            Product prod = getProd(toppingName, toppingSize);
            if (isEmpty(table)) {
                JOptionPane.showMessageDialog(null, "Chỉ có thể thêm topping khi đã có ít nhất 1 món");
            } else {
                if (!updateQuantityTopping()) {
                    addTableRow(isTopping, formatNameColumn(isTopping), 1, prod);
                } else {
                    total = total.add(prod.getPrice());
                }
                totalLbl.setText(formatPrice(total.toString()));
            }
        });

        btnNumberCups.addActionListener((ActionEvent e) -> {
            isTopping = false;
            //prod
            String prodName = nameCbx.getSelectedItem().toString();
            String sizeName = sizeCbx.getSelectedItem().toString();

            //topping
            String toppingName = toppingCbx.getSelectedItem().toString();
            String toppingSize = "Topping";
            // get prod and topping
            Product prod = getProd(prodName, sizeName);
            Product topping = getProd(toppingName, toppingSize);
            if (!existedProd(formatNameColumn(isTopping) + formatNameColumn(!isTopping))) {
                addTableRow(isTopping, formatNameColumn(isTopping), 1, prod);
                addTableRow(!isTopping, formatNameColumn(!isTopping), 1, topping);
            } else {
                total = total.add(prod.getPrice());
                total = total.add(topping.getPrice());
            }
            totalLbl.setText(formatPrice(total.toString()));

        });

        btnSearchKH.addActionListener((ActionEvent e) -> {
            CustomerSearch_view searchView = new CustomerSearch_view(emp, true);
            Main.tabbedPane.add("Tra cứu khách hàng", searchView);
            Main.tabbedPane.setSelectedComponent(searchView);
        });

        btnAdd.addActionListener((ActionEvent e) -> {
            if (isEmpty(table)) {
                JOptionPane.showMessageDialog(null, "Bạn chưa thêm sản phẩm, đừng thêm hóa đơn đờ mẹ");
            } else {
                if (cusTF.getText().isEmpty()) {
                    int yes = JOptionPane.showConfirmDialog(null, "Khách hàng hiện không có, bạn có chắc chắn tiếp tục?");
                    if (yes == JOptionPane.YES_OPTION) {
                        if (insertInvoice()) {
                            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
                            controller.callReport(IDLabel.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm hóa đơn không thành công");
                        }
                    }
                } else {
                    if (insertInvoice()) {
                        JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
                        controller.callReport(IDLabel.getText());
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm hóa đơn không thành công");
                    }
                }
            }

        });

    }

    private Product getProd(String prodName, String sizeName) {
        String sizeID = prod_con.getMaSize(sizeName);
        return prod_con.getProd(prodName, sizeID);
    }

    private boolean updateQuantityTopping() {
        DefaultTableModel d = (DefaultTableModel) table.getModel();
        int lastRow = d.getRowCount() - 1;
        if (d.getValueAt(lastRow, 1).equals(formatNameColumn(isTopping))) {
            int currentQuantity = (int) d.getValueAt(lastRow, SL);
            d.setValueAt(currentQuantity + 1, lastRow, SL);
            currentToppingQuantity = currentQuantity + 1;
            return true;
        }
        return false;
    }

    private String[] getItems(String typeID) {
        ArrayList<Product> prods = prod_con.searchProduct_TypeID(typeID, 1);
        String[] names = new String[prods.size()];
        for (int i = 0; i < prods.size(); ++i) {
            names[i] = prods.get(i).getProdName();
        }
        return Arrays.stream(names).distinct().toArray(String[]::new);

    }

    private void setNameCbBox(String[] items) {
        DefaultComboBoxModel namecbModel = new DefaultComboBoxModel(items);
        nameCbx.setModel(namecbModel);
        nameCbx.setSelectedIndex(0);
    }

    private void setSizeCbBox() {
        Object[] sizes = prod_con.getSizesWorkingProd(nameCbx.getSelectedItem().toString());
        DefaultComboBoxModel sizeCBModel = new DefaultComboBoxModel(sizes);
        sizeCbx.setModel(sizeCBModel);
    }

    private void addTableRow(boolean isTopping, String name, int quantity, Product prod) {

        String stt = "";

        if (!isTopping) {
            cups += 1;
            hideTableGrid(isTopping);
            stt = cups + "";
        } else {
            stt = "";
            hideTableGrid(isTopping);
        }
        Vector row = new Vector();
        row.add(stt);
        row.add(formatNameColumn(isTopping));
        row.add(quantity);
        row.add(formatPrice(prod.getPrice().toString()));
        total = total.add(prod.getPrice());

        jButton1.setText(Integer.toString(cups));
        dtm.addRow(row);
        myTable.setTextCenter(table);
        resizeColumnWidth(table);
    }

    private String formatPrice(String price) {
        return price.replace(".0000", " đồng");
    }

    private void hideTableGrid(boolean isTopping) {
        if (isTopping) {
            table.setShowHorizontalLines(false);
        } else {
            table.setShowHorizontalLines(true);
            table.setShowGrid(true);
        }
    }

    private String formatNameColumn(boolean isTopping) {
        if (isTopping) {
            return "    + " + toppingCbx.getSelectedItem();
        }
        return nameCbx.getSelectedItem() + "(" + sizeCbx.getSelectedItem() + ")";
    }

    private String revertNameColumn(boolean isTopping, String name) {
        if (isTopping) {
            return name.replace("    + ", "");
        }
        return name.substring(0, name.length() - 3);
    }

    private void setTable() {
        dtm = new DefaultTableModel();
        controller = new Invoice_controller();
        Vector column = new Vector();
        column.add("STT");
        column.add("Tên món");
        column.add("SL");
        column.add("Đơn giá");
        column.add("Xóa");
        /* Set Column Header lên DefaultTableModel */
        dtm.setColumnIdentifiers(column);

        table.setModel(dtm);
        resizeColumnWidth(table);
        table.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
        table.setAutoscrolls(true);
        totalLbl.setText(formatPrice("0"));
    }

    private boolean existedProd(String prodName) {
        DefaultTableModel d = (DefaultTableModel) table.getModel();
        int row = table.getRowCount();
        for (int i = 0; (i + 1) < row; i++) {
            String valueRow = (String) d.getValueAt(i, 1) + (String) d.getValueAt(i + 1, 1);
            if (valueRow.equals(prodName)) {
                int currentQuantity = (int) d.getValueAt(i, SL);
                int curQuantityTopping = (int) d.getValueAt(i + 1, SL);
                d.setValueAt(curQuantityTopping + 1, i + 1, SL);
                d.setValueAt(currentQuantity + 1, i, SL);
                currentProdQuantity = currentQuantity + 1;
                currentToppingQuantity = curQuantityTopping + 1;
                return true;
            }
        }
        return false;
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 30; // Min width
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
// too lazy, this should belong to myTable class

    private static boolean isEmpty(JTable jTable) {
        if (jTable != null && jTable.getModel() != null) {
            return jTable.getModel().getRowCount() <= 0;
        }
        return false;
    }

    private boolean insertInvoice() {

        Invoice e = new Invoice();
        e.setID(IDLabel.getText());
        e.setEmpID(emp.getID());
        e.setCusID(CusID);
        e.setTotalBill(total);

        listDetails = new ArrayList<>();
        for (int i = 0; i < table.getRowCount(); i++) {
            // find ProdID
            isTopping = false;
            String full = table.getValueAt(i, TEN_MON).toString();
            String sizeName = full.substring((full.length() - 2), (full.length() - 1));
            if (table.getValueAt(i, STT).toString().equals("")) {
                isTopping = true;
                sizeName = "Topping";
            }
            String prodName = revertNameColumn(isTopping, full);

            Product prod = getProd(prodName, sizeName);

            IDInt += 1;
            InvoiceDetail detail = new InvoiceDetail();
            detail.setID("CTHD" + IDInt);
            detail.setInvoiceID(IDLabel.getText());
            detail.setProdID(prod.getID());
            detail.setQuantity((int) table.getValueAt(i, SL));
            listDetails.add(detail);
        }
//        for(int i = 0; i < listDetails.size(); i++){
//            System.out.print(i + "\t");
//            System.out.print(listDetails.get(i).getID()+ "\t");
//            System.out.print(listDetails.get(i).getInvoiceID()+ "\t");
//            System.out.print(listDetails.get(i).getProdID()+ "\t");
//            System.out.print(listDetails.get(i).getQuantity()+ "\t");
//            System.out.print("\n");
//        }

        if (controller.insertInvoice(e, listDetails)) {
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
        currentDateLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        IDLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sizeCbx = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        toppingCbx = new javax.swing.JComboBox<>();
        typeCbx = new javax.swing.JComboBox<>();
        btnNumberCups = new javax.swing.JButton();
        btnAddTopping = new javax.swing.JButton();
        nameCbx = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cusTF = new javax.swing.JTextField();
        btnSearchKH = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new myTable();
        jLabel12 = new javax.swing.JLabel();
        totalLbl = new javax.swing.JLabel();
        btnAdd = new javax.swing.JButton();
        jButton1 =  new myButton("");

        setBackground(new java.awt.Color(25, 104, 192));
        setPreferredSize(new java.awt.Dimension(1056, 452));
        setLayout(null);

        usernameLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        usernameLabel.setText("a");
        add(usernameLabel);
        usernameLabel.setBounds(150, 40, 670, 40);

        btnBack.setBackground(new java.awt.Color(255, 255, 255));
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnBack.PNG"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        add(btnBack);
        btnBack.setBounds(40, 40, 90, 30);

        currentDateLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        currentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(currentDateLabel);
        currentDateLabel.setBounds(1010, 20, 300, 40);

        jPanel1.setLayout(null);

        IDLabel.setFont(new java.awt.Font("Myriad Pro", 0, 28)); // NOI18N
        IDLabel.setText("HD1");
        jPanel1.add(IDLabel);
        IDLabel.setBounds(40, 20, 100, 30);

        jLabel2.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel2.setText("Loại:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(160, 70, 70, 15);

        jLabel3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel3.setText("Tên món:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(160, 120, 80, 20);

        sizeCbx.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        sizeCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(sizeCbx);
        sizeCbx.setBounds(290, 210, 250, 30);

        jLabel7.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel7.setText("Topping:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(160, 170, 70, 15);

        jLabel8.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel8.setText("Khách hàng:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(160, 270, 90, 15);

        toppingCbx.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        toppingCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(toppingCbx);
        toppingCbx.setBounds(290, 160, 180, 30);

        typeCbx.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        typeCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(typeCbx);
        typeCbx.setBounds(290, 60, 250, 30);

        btnNumberCups.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        btnNumberCups.setText("+ Thêm ly");
        jPanel1.add(btnNumberCups);
        btnNumberCups.setBounds(420, 340, 120, 30);

        btnAddTopping.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
        btnAddTopping.setText("+");
        btnAddTopping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddToppingActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddTopping);
        btnAddTopping.setBounds(480, 160, 60, 30);

        nameCbx.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        nameCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(nameCbx);
        nameCbx.setBounds(290, 110, 250, 30);

        jLabel9.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel9.setText("Size:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(160, 220, 90, 15);

        cusTF.setEditable(false);
        cusTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(cusTF);
        cusTF.setBounds(290, 260, 180, 30);

        btnSearchKH.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        btnSearchKH.setText("Tìm");
        btnSearchKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchKHActionPerformed(evt);
            }
        });
        jPanel1.add(btnSearchKH);
        btnSearchKH.setBounds(480, 260, 60, 30);

        add(jPanel1);
        jPanel1.setBounds(40, 180, 660, 400);

        jPanel2.setMinimumSize(new java.awt.Dimension(2, 10));
        jPanel2.setPreferredSize(new java.awt.Dimension(2, 2));
        add(jPanel2);
        jPanel2.setBounds(740, 180, 3, 440);

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

        add(jScrollPane1);
        jScrollPane1.setBounds(780, 180, 540, 270);

        jLabel12.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Thành tiền:");
        add(jLabel12);
        jLabel12.setBounds(840, 460, 100, 50);

        totalLbl.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        totalLbl.setForeground(new java.awt.Color(255, 255, 255));
        totalLbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalLbl.setText("50000");
        add(totalLbl);
        totalLbl.setBounds(1020, 460, 290, 50);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnXuatHD.PNG"))); // NOI18N
        btnAdd.setText("jButton1");
        add(btnAdd);
        btnAdd.setBounds(1170, 510, 140, 30);

        jButton1.setBackground(new java.awt.Color(25, 104, 192));
        jButton1.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
        jButton1.setText("1");
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(jButton1);
        jButton1.setBounds(570, 80, 130, 70);

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddToppingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddToppingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddToppingActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnSearchKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchKHActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel IDLabel;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAddTopping;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnNumberCups;
    private javax.swing.JButton btnSearchKH;
    private javax.swing.JLabel currentDateLabel;
    public static javax.swing.JTextField cusTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> nameCbx;
    private javax.swing.JComboBox<String> sizeCbx;
    private javax.swing.JTable table;
    private javax.swing.JComboBox<String> toppingCbx;
    private javax.swing.JLabel totalLbl;
    private javax.swing.JComboBox<String> typeCbx;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
