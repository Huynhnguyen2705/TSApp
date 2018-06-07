/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Employee;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import tsapp.component.myButton;
import tsapp.component.myLabel;
import tsapp.component.myPanel;

/**
 *
 * @author Huynh
 */
public class Main extends JFrame {

    public static JTabbedPane tabbedPane = new JTabbedPane();
    // Khai báo các panel
    private Container c;
    private JPanel pane;
    private static JPanel panelMain;
    private JPanel panelTitle;
    private JPanel panelLogout;
    private JPanel panelButton;

    // Khai báo các components
    private myLabel lblTitle;
    private JLabel lblTennd;
    private JButton btnSanpham;
    private JButton btnHoadon;
    private JButton btnKhachhang;
    private JButton btnNguoidung;
    private JButton btnQuydinh;
    private JButton btnLogout;

    private String tenND = "Nguyễn Thị A";

    //Entity
    private Employee nguoidung = new Employee();
    public static Customer_view kh;
    public static Employee_view emp_view;

    // Cac man hinh
    public static final String MAINFORM = "TRANG CHỦ";
    public static final String HOADON = "HÓA ĐƠN";
    public static final String SANPHAM = "SẢN PHẨM";
    public static final String KHACHHANG = "KHÁCH HÀNG";
    public static final String NHANVIEN = "NHÂN VIÊN";

    public Main(Employee nd) {
        super("QUẢN LÝ CỬA HÀNG TRÀ SỮA");
        try {
            tenND = nd.getFullName();
            nguoidung = nd;
                
            createGUI();
            //setEnable(nd.getAccRole());
            actionListener();
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public final void createGUI() throws Exception {
        c = getContentPane();
        mainForm();

        // add cac panel
        tabbedPane.addTab(MAINFORM, panelMain);
        tabbedPane.setFont(new Font("Myriad Pro", Font.PLAIN, 15));

        // Hien thi man hinh main
        c.add(tabbedPane);
        c.setVisible(true);
    }

    public final void mainForm() throws IOException {
        panelMain = new myPanel(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Khởi tạo các JPanel
        panelTitle = new myPanel(new BorderLayout());
        panelLogout = new myPanel(new BorderLayout());
        panelButton = new myPanel(new GridLayout(2, 3, 20, 20));

        //Add title
        lblTitle = new myLabel("QUẢN LÝ CỬA HÀNG TRÀ SỮA");
        lblTitle.setTitle();

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.gridx = 0;
        gc.gridy = 0;

        panelTitle.add(lblTitle, BorderLayout.CENTER);
        panelMain.add(panelTitle, gc);

        //Add tennd và btnLogout
        lblTennd = new JLabel("Xin chào, " + tenND);
        lblTennd.setFont(new Font("Myriad Pro", Font.ITALIC, 14));


        JPanel temp1 = new myPanel(new FlowLayout());
        temp1.add(lblTennd);

        panelLogout.add(temp1, BorderLayout.LINE_END);

        gc.gridx = 0;
        gc.gridy = 1;

        panelMain.add(panelLogout, gc);

        //Add các button
        btnHoadon = new myButton("HOÁ ĐƠN");

        btnSanpham = new myButton("SẢN PHẨM");

        btnNguoidung = new myButton("NHÂN VIÊN");

        btnKhachhang = new myButton("KHÁCH HÀNG");

        btnQuydinh = new myButton("QUY ĐỊNH");

        btnLogout = new myButton("ĐĂNG XUẤT");
        btnLogout.setForeground(new Color (25,104,192));

        panelButton.add(btnHoadon);
        panelButton.add(btnSanpham);
        panelButton.add(btnNguoidung);
        panelButton.add(btnKhachhang);
        panelButton.add(btnQuydinh);
        panelButton.add(btnLogout);

        gc.weighty = 30;
        gc.insets = new Insets(30, 30, 50, 30);
        gc.gridx = 0;
        gc.gridy = 2;
        panelMain.add(panelButton, gc);
    }

   
    private void setEnable(String role){
        if("Role02".equals(role)){
            btnNguoidung.setEnabled(false);
            btnQuydinh.setEnabled(false);
        }
        btnHoadon.setEnabled(true);
        btnSanpham.setEnabled(true);
        btnNguoidung.setEnabled(true);
        btnKhachhang.setEnabled(true);
        btnQuydinh.setEnabled(true);
        btnLogout.setEnabled(true);
    }

    public static void addTabBottomDown(String tabName, JPanel componentName){
        tabbedPane.removeAll();
        tabbedPane.addTab(MAINFORM, panelMain);
        tabbedPane.addTab(tabName, componentName);
        tabbedPane.setFont(new Font("Myriad Pro", Font.PLAIN, 15));
        
    }

    public final void actionListener() {
        // Thoát
        btnLogout.addActionListener((ActionEvent e) -> {
                JFrame frame = new Login();
                frame.setSize(1300, 760);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
                frame.pack();
                dispose();
        });
        btnKhachhang.addActionListener((ActionEvent e) -> {
            kh = new Customer_view(nguoidung);
            addTabBottomDown(KHACHHANG, kh);
            tabbedPane.setSelectedIndex(1);
            
            
        });
        
        btnNguoidung.addActionListener((ActionEvent e) -> {
           emp_view = new Employee_view(nguoidung);
            addTabBottomDown(NHANVIEN, emp_view);
            tabbedPane.setSelectedComponent(emp_view);
        });
        
    }
}
