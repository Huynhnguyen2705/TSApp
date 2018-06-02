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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Huynh
 */
public class Login_model {

    // Lấy ra đối tượng Connection kết nối vào database
    Connection connection;
    CallableStatement callStament;
    ResultSet rs;
    private final String GET_EMP_LOGIN = "{call get_Employee_login (?,?)}";

    public Employee getEmpLogin(String userName, String password) throws ClassNotFoundException, SQLException {

        Employee emp = new Employee();
        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(GET_EMP_LOGIN);

            callStament.setString(1, userName);
            callStament.setString(2, password);

        
            rs = callStament.executeQuery();
            
            if (rs.next()) {
                emp.setFullName(rs.getString("EmpFullName"));
                emp.setStatue(rs.getInt("Statue"));
                emp.setAccRole(rs.getString("AccRole"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Login_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return emp;

    }

}
