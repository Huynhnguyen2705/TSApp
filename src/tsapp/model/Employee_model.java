/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.model;

import Entity.Employee;
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
public class Employee_model {

    private final String SEARCH_EMPLOYEE = "{call searchEmployee(?)}";
    private final String SEARCH_EMPLOYEE_ID = "{call searchEmp_ID(?)}";
    private final String INSERT_ACCOUNT = "{call insertAccount(?,?,?)}";
    private final String INSERT_EMPLOYEE = "{call insertEmployee(?,?,?,?,?,?,?,?)}";
    private final String UPDATE_ACCOUNT = "{call updateAccount(?,?,?)}";
    private final String UPDATE_EMPLOYEE = "{call updateEmp(?,?,?,?,?,?,?)}";
    Connection connection;
    CallableStatement callStament;
    ResultSet rs;

    public ArrayList<Employee> searchEmployee(String keyword) throws ClassNotFoundException, SQLException {
        ArrayList<Employee> listEmp = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_EMPLOYEE);

            callStament.setString(1, keyword);
            rs = callStament.executeQuery();
            while (rs.next()) {
                Employee item = new Employee();
                item.setID(rs.getString("ID"));
                item.setFullName(rs.getString("EmpFullName"));
                item.setPhoneNumber(rs.getString("PhoneNumber"));
                item.setDOB(rs.getDate("DOB"));
                item.setIDCard(rs.getString("IDCard"));
                item.setEmpAddress(rs.getString("EmpAddress"));
                item.setUserName(rs.getString("UserName"));
                item.setAccRole(rs.getString("RoleName"));
                item.setStatue(rs.getInt("Statue"));
                item.setIDInt(rs.getInt("IDInt"));
                listEmp.add(item);
            }

            return listEmp;

        } catch (SQLException ex) {
            Logger.getLogger(Employee_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listEmp;
    }

    public Employee searchEmployeeID(String keyword) throws ClassNotFoundException, SQLException {
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_EMPLOYEE_ID);

            callStament.setString(1, keyword);
            rs = callStament.executeQuery();
            if (rs.next()) {
                Employee item = new Employee();
                item.setID(rs.getString("ID"));
                item.setFullName(rs.getString("EmpFullName"));
                item.setPhoneNumber(rs.getString("PhoneNumber"));
                item.setDOB(rs.getDate("DOB"));
                item.setIDCard(rs.getString("IDCard"));
                item.setEmpAddress(rs.getString("EmpAddress"));
                item.setUserName(rs.getString("UserName"));
                item.setAccRole(rs.getString("RoleName"));
                item.setStatue(rs.getInt("Statue"));
                item.setIDInt(rs.getInt("IDInt"));
                return item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Employee_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return null;
    }

    
    public boolean insertEmployee(Employee e,String passwd) {

        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(INSERT_ACCOUNT);

            callStament.setString(1, e.getUserName());
            callStament.setString(2, passwd);
            callStament.setString(3, e.getAccRole());
            int result = callStament.executeUpdate();
            if(result > 0){
                callStament = connection.prepareCall(INSERT_EMPLOYEE);
                
                callStament.setString(1, e.getID());
                callStament.setString(2, e.getFullName());
                callStament.setString(3, e.getPhoneNumber());
                callStament.setDate(4, e.getDOB());
                callStament.setString(5, e.getIDCard());
                callStament.setString(6, e.getEmpAddress());
                callStament.setString(7, e.getUserName());
                callStament.setInt(8, e.getStatue());
                int resultEmp = callStament.executeUpdate();
                if(resultEmp > 0){
                    return true;
                }
                
                
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Employee_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateAccount(Employee e, String passwd) {
        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(UPDATE_ACCOUNT);

            callStament.setString(1, e.getUserName());
            callStament.setString(2, passwd);
            callStament.setString(3, e.getAccRole());
            int result = callStament.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employee_model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateEmployee(Employee e) {
        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(UPDATE_EMPLOYEE);

            callStament.setString(1, e.getID());
            callStament.setString(2, e.getFullName());
            callStament.setString(3, e.getPhoneNumber());
            callStament.setDate(4, e.getDOB());
            callStament.setString(5, e.getIDCard());
            callStament.setString(6, e.getEmpAddress());
            callStament.setInt(7, e.getStatue());
            int result = callStament.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Employee_model.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employee_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
