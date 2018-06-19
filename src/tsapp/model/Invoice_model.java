/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.model;

import Entity.Customer;
import Entity.Invoice;
import Entity.InvoiceDetail;
import Entity.Report;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh
 */
public class Invoice_model {

    private final String SEARCH_INVOICE = "{call searchInvoice(?,?)}";
    private final String SEARCH_INVOICE_STATUS = "{call searchInvoice_Status(?,?,?)}";
    private final String SEARCH_INVOICE_EMP = "{call searchInvoice_Emp(?,?,?)}";
    private final String SEARCH_INVOICEID = "{call searchInvoiceID(?)}";
    private final String SEARCH_INVOICE_DETAIL = "{call searchInvoiceDetail(?)}";
    private final String INSERT_INVOICE_DETAIL = "{call insertInvoiceDetail (?,?,?,?)}";
    private final String INSERT_INVOICE = "{call insertInvoice(?,?,?,?)}";
    private final String UPDATE_CUSTOMER = "{call updateCustomer(?,?,?)}";
    Connection connection;
    CallableStatement callStament;
    ResultSet rs;

    public ArrayList<Invoice> searchInvoice(Date fromDate, Date toDate) throws SQLException {
        ArrayList<Invoice> listInvce = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_INVOICE);

            callStament.setDate(1, fromDate);
            callStament.setDate(2, toDate);
            rs = callStament.executeQuery();
            while (rs.next()) {
                Invoice item = new Invoice();
                item.setID(rs.getString("ID"));
                item.setCreatedDate(rs.getDate("CreatedDate"));
                item.setEmpAccount(rs.getString("UserName"));
                item.setCusName(rs.getString("CusName"));
                item.setNote(rs.getString("InvoiceNote"));
                item.setTotalBill(rs.getBigDecimal("totalBill"));
                item.setStatue(rs.getInt("statue"));
                item.setIDInt(rs.getInt("IDInt"));
                listInvce.add(item);
            }

            return listInvce;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listInvce;
    }
    
    public ArrayList<Invoice> searchInvoice_Status(Date fromDate, Date toDate, int status) {
        ArrayList<Invoice> listInvce = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_INVOICE_STATUS);

            callStament.setDate(1, fromDate);
            callStament.setDate(2, toDate);
            callStament.setInt(3, status);
            rs = callStament.executeQuery();
            while (rs.next()) {
                Invoice item = new Invoice();
                item.setID(rs.getString("ID"));
                item.setCreatedDate(rs.getDate("CreatedDate"));
                item.setEmpAccount(rs.getString("UserName"));
                item.setCusName(rs.getString("CusName"));
                item.setNote(rs.getString("InvoiceNote"));
                item.setTotalBill(rs.getBigDecimal("totalBill"));
                item.setStatue(rs.getInt("statue"));
                item.setIDInt(rs.getInt("IDInt"));
                listInvce.add(item);
            }

            return listInvce;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listInvce;
    }

    public ArrayList<Invoice> searchInvoiceID(String keyword) {
        ArrayList<Invoice> listInvce = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_INVOICEID);

            callStament.setString(1, keyword);

            rs = callStament.executeQuery();
            while (rs.next()) {
                Invoice item = new Invoice();
                item.setID(rs.getString("ID"));
                item.setCreatedDate(rs.getDate("CreatedDate"));
                item.setEmpID(rs.getString("EmpID"));
                item.setCusID(rs.getString("CusID"));
                item.setNote(rs.getString("InvoiceNote"));
                item.setTotalBill(rs.getBigDecimal("totalBill"));
                item.setStatue(rs.getInt("statue"));
                item.setIDInt(rs.getInt("IDInt"));
                listInvce.add(item);
            }

            return listInvce;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listInvce;
    }

    public ArrayList<Invoice> searchInvoice_EMP(Date fromDate, Date toDate, int status) {
        ArrayList<Invoice> listInvce = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_INVOICE_EMP);

            callStament.setDate(1, fromDate);
            callStament.setDate(2, toDate);
            callStament.setInt(3, status);
            rs = callStament.executeQuery();
            while (rs.next()) {
                Invoice item = new Invoice();
                item.setEmpID(rs.getString("EmpID"));
                item.setEmpAccount(rs.getString("UserName"));
                item.setBillNumbers(rs.getInt("billnumbers"));
                item.setTotalBill(rs.getBigDecimal("total"));
                listInvce.add(item);
            }
            return listInvce;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listInvce;
    }
    
    public ArrayList<InvoiceDetail> searchInvoiceDetail(String keyword) {
        ArrayList<InvoiceDetail> listInvce = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_INVOICE_DETAIL);

            callStament.setString(1, keyword);

            rs = callStament.executeQuery();
            while (rs.next()) {
                InvoiceDetail item = new InvoiceDetail();
                item.setID(rs.getString("ID"));
                item.setInvoiceID(rs.getString("InvceID"));
                item.setProdID(rs.getString("ProdID"));
                item.setQuantity(rs.getInt("Quantity"));
                item.setIDInt(rs.getInt("IDInt"));
                listInvce.add(item);
            }

            return listInvce;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listInvce;
    }
    
    

    public boolean insertInvoice(Invoice invoice, ArrayList<InvoiceDetail> listDetails) {

        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(INSERT_INVOICE);

            callStament.setString(1, invoice.getID());
            callStament.setString(2, invoice.getEmpID());
            callStament.setString(3, invoice.getCusID());
            callStament.setBigDecimal(4, invoice.getTotalBill());

            int result = callStament.executeUpdate();
            if (result > 0) {

                callStament = connection.prepareCall(INSERT_INVOICE_DETAIL);

                for (int i = 0; i < listDetails.size(); i++) {
                    InvoiceDetail detail = listDetails.get(i);
                    callStament.setString(1, detail.getID());
                    callStament.setString(2, detail.getInvoiceID());
                    callStament.setString(3, detail.getProdID());
                    callStament.setInt(4, detail.getQuantity());
                    int exe = callStament.executeUpdate();
                    if (exe == 0) {
                        return false;
                    }
                }

                return true;

            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateCustomer(Customer cus) {
        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(UPDATE_CUSTOMER);

            callStament.setString(1, cus.getID());
            callStament.setString(2, cus.getFullName());
            callStament.setString(3, cus.getPhoneNumber());
            int result = callStament.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean reportHD(String maHD) {
        try {
            connection = myConnection.getSQLServerConnection();
            Statement statement = connection.createStatement();
            HashMap parameterMap = new HashMap();
            parameterMap.put("ID", maHD);
            Report rpt = new Report(parameterMap, connection);
            rpt.setReportName("reportHD1"); //productlist is the name of my jasper file.
            rpt.callReport();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
        public boolean reportEmp(String EmpID) {
        try {
            connection = myConnection.getSQLServerConnection();
            Statement statement = connection.createStatement();
            HashMap parameterMap = new HashMap();
            parameterMap.put("EmpID", EmpID);
            Report rpt = new Report(parameterMap, connection);
            rpt.setReportName("reportEmp"); //productlist is the name of my jasper file.
            rpt.callReport();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Invoice_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
