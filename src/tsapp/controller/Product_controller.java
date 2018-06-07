/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.controller;

import Entity.Customer;
import Entity.Product;
import Entity.ProductSize;
import Entity.ProductType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import tsapp.model.Product_model;

/**
 *
 * @author Huynh
 */
public class Product_controller {

    Product_model model = new Product_model();

    public ArrayList<Product> searchProduct(String keyword) {
        ArrayList<Product> rs = new ArrayList<>();
        try {
            rs = model.searchProduct(keyword);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Product_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public ArrayList<Product> searchProduct_Status(String keyword, int status) {
        ArrayList<Product> rs = new ArrayList<>();
        try {
            rs = model.searchProductStatus(keyword, status);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Product_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public Product searchProductID(String ID){
        try {
            return model.searchProductID(ID);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Product_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // Lấy danh sách tên loại sản phẩm
    public String[] getTypes() throws ClassNotFoundException, SQLException {
        ArrayList<ProductType> listLSP = model.getType();
        String[] lsp = new String[listLSP.size()];
        for (int i = 0; i < lsp.length; ++i) {
            lsp[i] = listLSP.get(i).getTypeName();
        }

        return lsp;
    }

    // Lấy danh sách size  sản phẩm
    public String[] getSizes() throws ClassNotFoundException, SQLException {
        ArrayList<ProductSize> listLSP = model.getSize();
        String[] lsp = new String[listLSP.size()];
        for (int i = 0; i < lsp.length; ++i) {
            lsp[i] = listLSP.get(i).getSizeName();
        }

        return lsp;
    }
    // Lấy mã của 1 size dựa trên tên size

    public final String getMaSize(String size) {
        try {
            ArrayList<ProductSize> listSize = model.getSize();
            for (ProductSize s : listSize) {
                if (s.getSizeName().equals(size)) {
                    return s.getID();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    // Lấy mã của 1 loại sản phẩm dựa trên tên sản phẩm
    public String getMaLSP(String tenSP) {
        try {
            ArrayList<ProductType> listLSP = model.getType();
            for (ProductType item : listLSP) {
                if (item.getTypeName().equals(tenSP)) {
                    return item.getID();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Product_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public boolean insertCustomer(Product cus) {
        return model.insertProduct(cus);
    }

    public boolean updateProduct(Product cus) {
        return model.updateProduct(cus);
    }

}
