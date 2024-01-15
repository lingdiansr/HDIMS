package UI;

import javax.swing.table.AbstractTableModel;

public class MyTableModel3 extends AbstractTableModel {
    final String[] columnNames = {"药名", "数量", "服用方法"};
    final Object[][] data = {{"", "", ""}, {"", "", ""}, {"", "", ""}};

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) { // 修改这个方法名称
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void resetRowCount() {
        for (int row = 0; row < data.length; row++) {
            data[row][0] = ""; // 或 data[row][0] = null;
        }
        fireTableDataChanged();
    }

    public boolean isCellEditable(int row, int col) {
        return true; // 设置所有单元格都可编辑
    }

}