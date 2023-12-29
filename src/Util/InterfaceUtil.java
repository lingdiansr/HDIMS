package Util;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class InterfaceUtil {
    public static Object[][] convertJTableToObjectArray(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int numRows = model.getRowCount();
        int numCols = model.getColumnCount();
        Object[][] data = new Object[numRows][numCols];

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                data[i][j] = model.getValueAt(i, j);
            }
        }

        return data;
    }
}
