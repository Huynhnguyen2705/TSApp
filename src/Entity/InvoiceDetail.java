/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Huynh
 */
public class InvoiceDetail {
    String ID;
    String InvoiceID;
    String ProdID;
    int Quantity;
    int IDInt;

    public InvoiceDetail(String ID, String InvoiceID, String ProdID, int Quantity) {
        this.ID = ID;
        this.InvoiceID = InvoiceID;
        this.ProdID = ProdID;
        this.Quantity = Quantity;
    }

    public InvoiceDetail() {}
    
    

    public int getIDInt() {
        return IDInt;
    }

    public void setIDInt(int IDInt) {
        this.IDInt = IDInt;
    }

    
    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(String InvoiceID) {
        this.InvoiceID = InvoiceID;
    }

    public String getProdID() {
        return ProdID;
    }

    public void setProdID(String ProdID) {
        this.ProdID = ProdID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }
    
    
}
