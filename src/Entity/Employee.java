/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author Huynh
 */
public class Employee {
    private String ID;
    private String fullName;
    private String PhoneNumber;
    private Date DOB;
    private String IDCard;
    private String EmpAddress;
    private String UserName;
    private int Statue;
    private String AccRole;
    private int IDInt;

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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getEmpAddress() {
        return EmpAddress;
    }

    public void setEmpAddress(String EmpAddress) {
        this.EmpAddress = EmpAddress;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public int getStatue() {
        return Statue;
    }

    public void setStatue(int Statue) {
        this.Statue = Statue;
    }

    public String getAccRole() {
        return AccRole;
    }

    public void setAccRole(String AccRole) {
        this.AccRole = AccRole;
    }
}
