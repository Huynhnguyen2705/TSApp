/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsapp.component;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Huynh
 */
public class myTable extends JTable{
    
    public myTable() {
        JTableHeader header = super.getTableHeader();
        header.setBackground(new Color(25, 104, 192));
        header.setForeground(Color.BLACK);
        header.setEnabled(false);
//        TableRowSorter<TableModel> rowSorter
//        rowSorter = new TableRowSorter<>(super.getModel());
//        super.setRowSorter(rowSorter);
//        rowSorter.sort();       
        super.setFont(new Font("Helvetica Neue", Font.PLAIN, 15));
        super.getTableHeader().setFont(new Font("Helvetica Neue", Font.BOLD, 16));
        super.setShowGrid(true);
        super.setGridColor(Color.BLACK);
        super.setRowHeight(25);
        super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        super.setDragEnabled(false);
    }

    @Override
    public boolean isCellEditable(int col, int row) {
        return false;
    }

}
