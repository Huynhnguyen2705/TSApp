/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.model;

import Entity.Product;
import Entity.ProductSize;
import Entity.ProductType;
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
public class Product_model {

    private final String SEARCH_PRODUCT = "{call searchProduct(?)}";
    private final String SEARCH_PRODUCT_ID = "{call searchProd_ID(?)}";
    private final String SEARCH_PRODUCT_STATUS = "{call searchProduct_Status(?,?)}";
    private final String GET_TYPE = "{call getProductType()}";
    private final String GET_SIZE = "{call getProductSize()}";
    private final String INSERT_PRODUCT = "{call insertProduct (?,?,?,?,?,?)}";
    private final String UPDATE_PRODUCT = "{call updateProduct(?,?,?,?,?,?)}";
    Connection connection;
    CallableStatement callStament;
    ResultSet rs;

        public final ArrayList<ProductType> getType() throws SQLException {
        ArrayList<ProductType> listType = new ArrayList<>();
        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(GET_TYPE);
            rs = callStament.executeQuery();
            while (rs.next()) {
                ProductType item = new ProductType();
                item.setID(rs.getString("ID"));
                item.setTypeName(rs.getString("TypeName"));
                item.setStatus(rs.getInt("Statue"));
                listType.add(item);
            }
            return listType;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Product_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listType;
    }
    
    public final ArrayList<ProductSize> getSize() throws SQLException {
        ArrayList<ProductSize> listSize = new ArrayList<>();
        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(GET_SIZE);
            rs = callStament.executeQuery();
            while (rs.next()) {
                ProductSize item = new ProductSize();
                item.setID(rs.getString("ID"));
                item.setSizeName(rs.getString("SizeName"));
                listSize.add(item);
            }
            return listSize;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Product_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listSize;
    }    
    
    public ArrayList<Product> searchProduct(String keyword) throws ClassNotFoundException, SQLException {
        ArrayList<Product> listProd = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_PRODUCT);

            callStament.setString(1, keyword);
            rs = callStament.executeQuery();
            while (rs.next()) {
                Product item = new Product();
                item.setID(rs.getString("ProdID"));
                item.setProdName(rs.getString("ProductName"));
                item.setPrice(rs.getBigDecimal("Price"));
                item.setTypeName(rs.getString("TypeName"));
                item.setSizeName(rs.getString("SizeName"));
                item.setStatus(rs.getInt("ProdStatue"));
                item.setSizeID(rs.getString("SizeID"));
                item.setTypeID(rs.getString("TypeID"));
                item.setTypeStatus(rs.getInt("TypeStatus"));
                item.setIDInt(rs.getInt("IDInt"));
                listProd.add(item);
            }

            return listProd;

        } catch (SQLException ex) {
            Logger.getLogger(Product_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listProd;
    }
    
    public Product searchProductID(String keyword) throws ClassNotFoundException, SQLException {
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_PRODUCT_ID);

            callStament.setString(1, keyword);
            rs = callStament.executeQuery();
            if (rs.next()) {
                Product item = new Product();
                item.setID(rs.getString("ProdID"));
                item.setProdName(rs.getString("ProductName"));
                item.setPrice(rs.getBigDecimal("Price"));
                item.setTypeName(rs.getString("TypeName"));
                item.setSizeName(rs.getString("SizeName"));
                item.setStatus(rs.getInt("ProdStatue"));
                item.setSizeID(rs.getString("SizeID"));
                item.setTypeID(rs.getString("TypeID"));
                item.setTypeStatus(rs.getInt("TypeStatus"));
                item.setIDInt(rs.getInt("IDInt"));
                return item;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Product_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return null;
    }
    
    
    public ArrayList<Product> searchProductStatus(String keyword, int status) throws ClassNotFoundException, SQLException {
        ArrayList<Product> listProd = new ArrayList<>();
        try {

            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(SEARCH_PRODUCT_STATUS);

            callStament.setString(1, keyword);
            callStament.setInt(2, status);
            rs = callStament.executeQuery();
            while (rs.next()) {
                Product item = new Product();
                item.setID(rs.getString("ProdID"));
                item.setProdName(rs.getString("ProductName"));
                item.setPrice(rs.getBigDecimal("Price"));
                item.setTypeName(rs.getString("TypeName"));
                item.setSizeName(rs.getString("SizeName"));
                item.setStatus(rs.getInt("ProdStatue"));
                item.setSizeID(rs.getString("SizeID"));
                item.setTypeID(rs.getString("TypeID"));
                item.setTypeStatus(rs.getInt("TypeStatus"));
                item.setIDInt(rs.getInt("IDInt"));
                listProd.add(item);
            }

            return listProd;

        } catch (SQLException ex) {
            Logger.getLogger(Product_model.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            connection.close();
        }
        return listProd;
    }

    public boolean insertProduct(Product prod) {

        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(INSERT_PRODUCT);

            callStament.setString(1, prod.getID());
            callStament.setString(2, prod.getProdName());
            callStament.setBigDecimal(3, prod.getPrice());
            callStament.setString(4, prod.getSizeID());
            callStament.setString(5, prod.getTypeID());
            callStament.setInt(6, prod.getStatus());

            int result = callStament.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Product_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateProduct(Product cus) {
        try {
            connection = myConnection.getSQLServerConnection();

            callStament = connection.prepareCall(UPDATE_PRODUCT);

            callStament.setString(1, cus.getID());
            callStament.setString(2, cus.getProdName());
            callStament.setBigDecimal(3, cus.getPrice());
            callStament.setString(4, cus.getSizeID());
            callStament.setString(5, cus.getTypeID());
            callStament.setInt(6, cus.getStatus());
            
            int result = callStament.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Product_model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
