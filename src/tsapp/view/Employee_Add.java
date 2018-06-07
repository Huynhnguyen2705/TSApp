/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.view;

import Entity.Employee;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.AbstractDocument;
import tsapp.component.CustomDocumentFilter;
import tsapp.controller.Employee_controller;

/**
 *
 * @author Huynh
 */
public class Employee_Add extends javax.swing.JPanel {

    /**
     * Creates new form NewJPanel
     */
    private final Employee emp;
    private final String tenND;
    private final String THEM_KHACHHANG = ">Thêm";
    private final String ADMIN_ITEM = "Quản trị viên";
    private final String EMP_ITEM = "Nhân viên";
    private Employee_controller controller;
    private ArrayList<Employee> listEmp;
    private Date defaultDate1;
    private final Object[] items = {ADMIN_ITEM, EMP_ITEM};

    public Employee_Add(Employee emp) {
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

        ((AbstractDocument) CMNDTF.getDocument()).setDocumentFilter(new CustomDocumentFilter());
        CMNDTF.addKeyListener(cmndKL);
        NameTF.addKeyListener(nameKL);
        usernameTF.addKeyListener(usernameKL);
        passwdTF.addKeyListener(passwdKL);
        passwdTF2.addKeyListener(passwdKL2);

        setDefaultDate();

        controller = new Employee_controller();
        listEmp = controller.searchEmployee("NV");
        IDLabel.setText("NV" + (listEmp.get(0).getIDInt() + 1));
        DefaultComboBoxModel cbModel = new DefaultComboBoxModel(items);
        QuyenCbx.setModel(cbModel);

    }

    public final void setDefaultDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date date1 = cal.getTime();
        DOBPicker.setFormats("dd/MMM/yyyy");
        DOBPicker.setDate(date1);

        defaultDate1 = date1;
    }
    private final KeyListener kl = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            phoneTFKeyTyped(e, 11);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };
    private final KeyListener nameKL = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            nameTFKeyTyped(e, 40);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    private final KeyListener usernameKL = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            usernameTFKeyTyped(e, 20);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    private final KeyListener cmndKL = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            CMNDTFKeyTyped(e, 12);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    private final KeyListener passwdKL = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            passwrdTFKeyTyped(e, 30);
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };
    private final KeyListener passwdKL2 = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            confrimPasswdTFKeyTyped(e, 30);

        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    };

    private void phoneTFKeyTyped(java.awt.event.KeyEvent evt, int maxLength) {
        if (phoneTF.getText().length() >= maxLength) {
            evt.consume();
        }
    }

    private void CMNDTFKeyTyped(java.awt.event.KeyEvent evt, int maxLength) {
        if (CMNDTF.getText().length() >= maxLength) {
            evt.consume();
        }
    }

    private void nameTFKeyTyped(java.awt.event.KeyEvent evt, int maxLength) {
        if (NameTF.getText().length() >= maxLength) {
            evt.consume();
        }
    }

    private void usernameTFKeyTyped(java.awt.event.KeyEvent evt, int maxLength) {
        if (usernameTF.getText().length() >= maxLength) {
            evt.consume();
        }
    }

    private void passwrdTFKeyTyped(java.awt.event.KeyEvent evt, int maxLength) {
        if (passwdTF.getText().length() >= maxLength) {
            evt.consume();
        }
    }

    private void confrimPasswdTFKeyTyped(java.awt.event.KeyEvent evt, int maxLength) {
        if (passwdTF2.getText().length() >= maxLength) {
            evt.consume();
        }
    }

    private boolean validateTF() {
        if (NameTF.getText().isEmpty()
                || phoneTF.getText().isEmpty()
                || CMNDTF.getText().isEmpty()
                || usernameTF.getText().isEmpty()
                || passwdTF.getText().isEmpty()
                || passwdTF2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Không được bỏ trống các trường có tích đỏ!");
            return false;
        } else {
            if (!Arrays.equals(passwdTF.getPassword(), passwdTF2.getPassword())) {
                JOptionPane.showMessageDialog(null, "Nhập mật khẩu không khớp!");
                return false;
            } else if (!checkEmpAge()) {
                JOptionPane.showMessageDialog(null, "Nhân viên phải lớn hơn 18 tuổi");
                return false;
            } else {
                return true;
            }
        }
    }

    public java.sql.Date convertJavaDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public final void actionListener() {
        // Thoát
        btnBack.addActionListener((ActionEvent e) -> {
            Main.tabbedPane.setSelectedComponent(Main.emp_view);
        });

        btnAdd.addActionListener((ActionEvent e) -> {
            if (validateTF()) {
                if (insertEmp(passwdTF.getText())) {
                    JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm không thành công");
                }
            }

        });

        DOBPicker.addActionListener((ActionEvent e) -> {
            checkEmpAge();
        });

    }

    private boolean checkEmpAge() {
        Calendar cal = Calendar.getInstance();

        int year1 = DOBPicker.getDate().getYear();
        int year2 = defaultDate1.getYear();
        if ((year2 - year1) > 18) {
            defaultDate1 = DOBPicker.getDate();
            return true;
        } else {
            setDefaultDate();
            return false;
        }
    }

    private String formatRole(String selectedRole) {
        if (selectedRole.equals(ADMIN_ITEM)) {
            return "Role01";
        } else {
            return "Role02";
        }
    }

    private boolean insertEmp(String password) {

        Employee e = new Employee();
        e.setID(IDLabel.getText());
        e.setFullName(NameTF.getText());
        e.setPhoneNumber(phoneTF.getText());
        e.setDOB(convertJavaDateToSqlDate(defaultDate1));
        e.setIDCard(CMNDTF.getText());
        e.setEmpAddress(DCTF.getText());
        e.setUserName(usernameTF.getText());
        e.setAccRole(formatRole((String) QuyenCbx.getSelectedItem()));
        e.setStatue(1);

        if (controller.insertEmployee(e, password)) {
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
        IDLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        NameTF = new javax.swing.JTextField();
        phoneTF = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        usernameTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        CMNDTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        DCTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        QuyenCbx = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        DOBPicker = new org.jdesktop.swingx.JXDatePicker();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        passwdTF = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        passwdTF2 = new javax.swing.JPasswordField();
        lbe2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbe1 = new javax.swing.JLabel();
        lbe3 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbe4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbe5 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

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
        lablelKH.setBounds(470, 90, 240, 40);

        currentDateLabel.setFont(new java.awt.Font("Myriad Pro", 0, 36)); // NOI18N
        currentDateLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add(currentDateLabel);
        currentDateLabel.setBounds(1010, 20, 300, 40);

        jPanel1.setLayout(null);

        IDLabel.setFont(new java.awt.Font("Myriad Pro", 0, 18)); // NOI18N
        IDLabel.setText("NV1");
        jPanel1.add(IDLabel);
        IDLabel.setBounds(20, 10, 100, 30);

        jLabel2.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel2.setText("Họ tên:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(60, 50, 70, 15);

        jLabel3.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel3.setText("SĐT:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(60, 90, 50, 15);

        NameTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(NameTF);
        NameTF.setBounds(160, 40, 250, 30);

        phoneTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(phoneTF);
        phoneTF.setBounds(160, 80, 250, 30);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/btnThemBlue.PNG"))); // NOI18N
        jPanel1.add(btnAdd);
        btnAdd.setBounds(220, 490, 110, 30);

        usernameTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(usernameTF);
        usernameTF.setBounds(160, 280, 250, 30);

        jLabel4.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel4.setText("Tên tài khoản:");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(60, 290, 100, 20);

        CMNDTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(CMNDTF);
        CMNDTF.setBounds(160, 180, 250, 30);

        jLabel5.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel5.setText("CMND:");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(60, 190, 50, 15);

        DCTF.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jPanel1.add(DCTF);
        DCTF.setBounds(160, 230, 250, 30);

        jLabel6.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel6.setText("Địa chỉ:");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(60, 240, 50, 15);

        QuyenCbx.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        QuyenCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(QuyenCbx);
        QuyenCbx.setBounds(160, 430, 250, 30);

        jLabel1.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel1.setText("Quyền:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(60, 440, 70, 15);

        DOBPicker.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        DOBPicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DOBPickerActionPerformed(evt);
            }
        });
        jPanel1.add(DOBPicker);
        DOBPicker.setBounds(160, 130, 270, 30);

        jLabel7.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel7.setText("Ngày sinh:");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(60, 140, 70, 15);

        jLabel8.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel8.setText("Mật khẩu:");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(60, 340, 90, 15);
        jPanel1.add(passwdTF);
        passwdTF.setBounds(160, 330, 250, 30);

        jLabel9.setFont(new java.awt.Font("Myriad Pro", 0, 14)); // NOI18N
        jLabel9.setText("Nhập lại MK:");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(60, 390, 90, 15);
        jPanel1.add(passwdTF2);
        passwdTF2.setBounds(160, 380, 250, 30);

        lbe2.setForeground(new java.awt.Color(255, 0, 0));
        lbe2.setText("*");
        jPanel1.add(lbe2);
        lbe2.setBounds(150, 80, 20, 20);

        jLabel11.setForeground(new java.awt.Color(255, 0, 0));
        jLabel11.setText("*");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(150, 380, 20, 14);

        lbe1.setForeground(new java.awt.Color(255, 0, 0));
        lbe1.setText("*");
        jPanel1.add(lbe1);
        lbe1.setBounds(150, 40, 20, 20);

        lbe3.setForeground(new java.awt.Color(255, 0, 0));
        lbe3.setText("*");
        jPanel1.add(lbe3);
        lbe3.setBounds(150, 130, 20, 14);

        jLabel14.setForeground(new java.awt.Color(255, 0, 0));
        jLabel14.setText("*");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(150, 130, 20, 14);

        lbe4.setForeground(new java.awt.Color(255, 0, 0));
        lbe4.setText("*");
        jPanel1.add(lbe4);
        lbe4.setBounds(150, 180, 20, 14);

        jLabel16.setForeground(new java.awt.Color(255, 0, 0));
        jLabel16.setText("*");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(150, 180, 20, 14);

        lbe5.setForeground(new java.awt.Color(255, 0, 0));
        lbe5.setText("*");
        jPanel1.add(lbe5);
        lbe5.setBounds(150, 280, 20, 14);

        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setText("*");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(150, 330, 20, 14);

        add(jPanel1);
        jPanel1.setBounds(480, 140, 460, 540);

        getAccessibleContext().setAccessibleParent(this);
    }// </editor-fold>//GEN-END:initComponents

    private void DOBPickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DOBPickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DOBPickerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField CMNDTF;
    private javax.swing.JTextField DCTF;
    private org.jdesktop.swingx.JXDatePicker DOBPicker;
    private javax.swing.JLabel IDLabel;
    private javax.swing.JTextField NameTF;
    private javax.swing.JComboBox<String> QuyenCbx;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel currentDateLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lablelKH;
    private javax.swing.JLabel lbe1;
    private javax.swing.JLabel lbe2;
    private javax.swing.JLabel lbe3;
    private javax.swing.JLabel lbe4;
    private javax.swing.JLabel lbe5;
    private javax.swing.JPasswordField passwdTF;
    private javax.swing.JPasswordField passwdTF2;
    private javax.swing.JTextField phoneTF;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTF;
    // End of variables declaration//GEN-END:variables
}
