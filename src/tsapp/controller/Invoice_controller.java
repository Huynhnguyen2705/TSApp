/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.controller;

import Entity.Invoice;
import Entity.InvoiceDetail;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tsapp.model.Invoice_model;

/**
 *
 * @author Huynh
 */
public class Invoice_controller {

    Invoice_model model = new Invoice_model();

    public ArrayList<Invoice> searchInvoices(Date fromDate, Date toDate) {
        ArrayList<Invoice> rs = new ArrayList<>();
        try {
            rs = model.searchInvoice(fromDate, toDate);
        } catch (SQLException ex) {
            Logger.getLogger(Invoice_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ArrayList<InvoiceDetail> searchInvoiceDetail(String keyword){
        return model.searchInvoiceDetail(keyword);
    }
    
    public ArrayList<Invoice> searchInvoiceID(String keyword){
        return model.searchInvoiceID(keyword);
    }
    
        
    public ArrayList<Invoice> searchInvoiceStauts(Date fromDate, Date toDate, int status){
        return model.searchInvoice_Status(fromDate, toDate, status);
    }
    
    public ArrayList<Invoice> searchInvoiceEMP(Date fromDate, Date toDate, int status){
        return model.searchInvoice_EMP(fromDate, toDate, status);
    }
    

//
//    public Employee searchEmployeeID(String keyword) {
//        try {
//            return model.searchEmployeeID(keyword);
//        } catch (ClassNotFoundException | SQLException ex) {
//            Logger.getLogger(Invoice_controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//
    public boolean insertInvoice(Invoice e, ArrayList<InvoiceDetail> listDetails) {
        return model.insertInvoice(e, listDetails);
    }
    
    public boolean callReport(String ID){
        return model.reportHD(ID);
    }
    
    public boolean callReportEmp(String ID){
        return model.reportEmp(ID);
    }
//
//    public boolean updateAccount(Employee e, String passwd) {
//        return model.updateAccount(e, passwd);
//    }
//
//    public boolean updateEmployee(Employee e) {
//        return model.updateEmployee(e);
//    }

}
