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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

/**
 *
 * @author Huynh
 */
public class myTable extends JTable {

    public myTable() {
        JTableHeader header = super.getTableHeader();
        header.setBackground(new Color(0, 62, 143));

        header.setForeground(Color.WHITE);
        header.setOpaque(false);
        header.setEnabled(false);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);
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

    public static void setTextCenter(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        TableModel tableModel = table.getModel();
        for (int columnIndex = 0; columnIndex < tableModel.getColumnCount(); columnIndex++) {
            table.getColumnModel().getColumn(columnIndex).setCellRenderer(centerRenderer);
        }
    }

    @Override
    public boolean isCellEditable(int col, int row) {
        return false;
    }

}
