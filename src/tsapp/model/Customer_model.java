/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.model;

import Entity.Customer;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh
 */
public class Customer_model {

    private final String SEARCH_CUSTOMER = "{call searchCustomer(?)}";
    private final String SEARCH_CUSTOMER_ID = "{call searchCus_ID(?)}";
    private final String INSERT_CUSTOMER = "{call insertCustomer (?,?,?,?,?)}";
    private final String UPDATE_CUSTOMER = "{call updateCustomer(?,?,?)}";
    Connection connection;
    CallableStatement callStament;
    ResultSet rs;

    public ArrayList<Customer> searchCustomer(String keyword) throws ClassNotFoundException, SQLException {
        ArrayList<Customer> listCus = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_CUSTOMER);

            callStament.setString(1, keyword);
            rs = callStament.executeQuery();
            while (rs.next()) {
                Customer item = new Customer();
                item.setID(rs.getString("ID"));
                item.setFullName(rs.getString("FullName"));
                item.setPhoneNumber(rs.getString("PhoneNumber"));
                item.setTotalBill(rs.getBigDecimal("TotalBill"));
                item.setCusType(rs.getString("typeName"));
                item.setCusInt(rs.getInt("CusInt"));
                listCus.add(item);
            }

            return listCus;

        } catch (SQLException ex) {
            Logger.getLogger(Customer_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listCus;
    }
    
    
    public Customer searchCustomerID(String keyword) throws ClassNotFoundException, SQLException {
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_CUSTOMER_ID);

            callStament.setString(1, keyword);
            rs = callStament.executeQuery();
            if (rs.next()) {
                Customer item = new Customer();
                item.setID(rs.getString("ID"));
                item.setFullName(rs.getString("FullName"));
                item.setPhoneNumber(rs.getString("PhoneNumber"));
                item.setTotalBill(rs.getBigDecimal("TotalBill"));
                item.setCusType(rs.getString("typeName"));
                item.setCusInt(rs.getInt("CusInt"));
                return item;
            }

            

        } catch (SQLException ex) {
            Logger.getLogger(Customer_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return null;
    }


    public boolean insertCustomer(Customer cus) {

        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(INSERT_CUSTOMER);

            callStament.setString(1, cus.getID());
            callStament.setString(2, cus.getFullName());
            callStament.setString(3, cus.getPhoneNumber());
            callStament.setBigDecimal(4, cus.getTotalBill());
            callStament.setString(5, cus.getCusType());

            int result = callStament.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Customer_model.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Customer_model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Customer_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
