package Util;

import Entity.Prescription;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

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
    public Prescription getSelectedPrescriptionFromTable(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) { // 确保有选中的行
            // 通过选中的行索引获取行数据
            Object[] rowData = new Object[7]; // 假设有7列数据
            for (int i = 0; i < 7; i++) {
                rowData[i] = table.getModel().getValueAt(selectedRow, i);
            }

            // 将行数据封装到 Prescription 对象中
            Prescription prescription = new Prescription();
            prescription.Pno = (int) rowData[0];
            prescription.Pid = (String) rowData[1];
            prescription.Dno = (String) rowData[2];
            prescription.Ptime = (Date) rowData[3];
            prescription.Nno = (String) rowData[4];
            prescription.Htime = (Date) rowData[5];
            prescription.Pstate = (boolean) rowData[6];

            return prescription;
        } else {
            return null; // 如果没有选中的行，则返回 null
        }
    }
}
