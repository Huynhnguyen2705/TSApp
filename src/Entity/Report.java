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

import java.awt.Container;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
import tsapp.model.myConnection;

public class Report extends JFrame{
    
    HashMap hm = null;
    Connection con = null;
    String reportName;
    int v_maHD;
 
    public Report() {
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 
    }
 
    public Report(HashMap map) {
        this.hm = map;
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
       
 
    }
 
    public Report(HashMap map ,Connection con) {
        this.hm = map;
        this.con = con;
        
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Report Viewer");
       
 
    }
 
    public void setReportName(String rptName) {
        this.reportName = rptName;
    }
 
    public void callReport() {
        JasperPrint jasperPrint = generateReport();
        JRViewer viewer = new JRViewer(jasperPrint);
        Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
    }
 
    public void callConnectionLessReport() {
        JasperPrint jasperPrint = generateEmptyDataSourceReport();
        JRViewer viewer = new JRViewer(jasperPrint);
        Container c = getContentPane();
        c.add(viewer);
        this.setVisible(true);
    }
 
    public void closeReport() {
        //jasperViewer.setVisible(false);
    }
 
    /** this method will call the report from data sourc
     * @return e*/
    public JasperPrint generateReport() {
        try {
            if (con == null) {
                try {
                    con = myConnection.getSQLServerConnection();
 
                } catch (ClassNotFoundException | SQLException ex) {
                }
            }
            JasperPrint jasperPrint = null;
            if (hm == null) {
                hm = new HashMap();
                //hm.put("v_maHD", v_maHD);
            }
            try {
                /**You can also test this line if you want to display 
                 * report from any absolute path other than the project root path*/
                //jasperPrint = JasperFillManager.fillReport("F:/testreport/"+reportName+".jasper",hm, con);
                jasperPrint = JasperFillManager.fillReport(reportName + ".jasper", hm, con);
            } catch (JRException e) {
            }
            return jasperPrint;
        } catch (Exception ex) {
            return null;
        }
 
 
    }
 
    /** call this method when your report has an empty data sourc
     * @return e*/
    public JasperPrint generateEmptyDataSourceReport() {
        try {
            JasperPrint jasperPrint = null;
            if (hm == null) {
                hm = new HashMap();
               // hm.put("v_maHD", v_maHD);
                
            }
            try {
                jasperPrint = JasperFillManager.fillReport(reportName + ".jasper", hm, new JREmptyDataSource());
            } catch (JRException e) {
            }
            return jasperPrint;
        } catch (Exception ex) {
            return null;
        }
 
    }
}
