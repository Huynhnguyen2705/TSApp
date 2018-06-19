/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Huynh
 */
public class Invoice {
    String ID;
    Date createdDate;
    String EmpID;
    String EmpAccount;
    String CusID;
    String CusName;
    String Note;
    int IDInt;
    BigDecimal totalBill;
    int Statue;
    int billNumbers;

    public int getBillNumbers() {
        return billNumbers;
    }

    public void setBillNumbers(int billNumbers) {
        this.billNumbers = billNumbers;
    }

    
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    
    
    public int getStatue() {
        return Statue;
    }

    public void setStatue(int Statue) {
        this.Statue = Statue;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    
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


    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String EmpID) {
        this.EmpID = EmpID;
    }

    public String getEmpAccount() {
        return EmpAccount;
    }

    public void setEmpAccount(String EmpAccount) {
        this.EmpAccount = EmpAccount;
    }

    public String getCusID() {
        return CusID;
    }

    public void setCusID(String CusID) {
        this.CusID = CusID;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String CusName) {
        this.CusName = CusName;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }
    
    
    
}
