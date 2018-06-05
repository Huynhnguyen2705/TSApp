/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.math.BigDecimal;

/**
 *
 * @author Huynh
 */
public class Customer {

    String ID;
    String fullName;
    String phoneNumber;
    BigDecimal totalBill;
    String CusType;
    int CusInt;

    public int getCusInt() {
        return CusInt;
    }

    public void setCusInt(int CusInt) {
        this.CusInt = CusInt;
    }

    public BigDecimal getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(BigDecimal totalBill) {
        this.totalBill = totalBill;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCusType() {
        return CusType;
    }

    public void setCusType(String CusType) {
        this.CusType = CusType;
    }

}
