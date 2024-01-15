package UI;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    final String[] columnNames = {"PDno", "PDname", "PDlife", "PDnum", "选择"};
    Object[][] data = {{" ", " ", " ", 0, false} };
    final Class[] columnClasses = {String.class, String.class, String.class, Integer.class, Boolean.class};

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        if (row < data.length && col < data[row].length) {
            return data[row][col];
        } else {
            return null; // Handle the case where the row or column index is out of bounds
        }
    }

    public Class getColumnClass(int c) {
        return columnClasses[c];
    }

    public boolean isCellEditable(int row, int col) { // 设置编辑单元格
        return col == 4; // 只允许编辑选择列
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public void setData(Object[][] newData) {
        data = newData;
        fireTableDataChanged(); // Notify the table model that the data has changed
    }
}
