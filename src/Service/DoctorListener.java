package Service;

import UI.Doctor;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class DoctorListener implements ActionListener {
    private Doctor doctor;

    public DoctorListener(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doctor.SureButton) {
            String searchText = doctor.SearcherField.getText();
            DoctorService ds = new DoctorService();
            Object[][] searchResult = ds.Search(searchText);
            Object[][] searchResultWithCheck = new Object[searchResult.length][searchResult[0].length + 1];
            for (int i = 0; i < searchResult.length; i++) {
                System.arraycopy(searchResult[i], 0, searchResultWithCheck[i], 0, searchResult[i].length);
                searchResultWithCheck[i][searchResult[i].length] = false;
            }
            doctor.myTableModel.setData(searchResultWithCheck);
        } else if (e.getSource() == doctor.ADDMedicationButton) {
            doctor.freeTableModel.resetRowCount();
            int k = 0;
            int rowCount = doctor.GiveMedicationTable.getRowCount();
            int selectedColumn = 4; // 第三列索引
            for (int row = 0; row < rowCount; row++) {
                boolean isSelected = (boolean) doctor.GiveMedicationTable.getValueAt(row, selectedColumn);
                if (isSelected) {
                    String medicationName = (String) doctor.GiveMedicationTable.getValueAt(row, 1);
                    doctor.freeTableModel.setValueAt(medicationName, k, 0);
                    k++;
                }
            }
            JOptionPane.showMessageDialog(doctor, "已添加");
        } else if (e.getSource() == doctor.CreatMedicationButton) {
            int rowCount = doctor.freeTableModel.getRowCount();
            for (int row = 0; row < rowCount; row++) {
                String medicationName = (String) doctor.freeTableModel.getValueAt(row, 0);
                String quantity = (String) doctor.freeTableModel.getValueAt(row, 1);
                String usage = (String) doctor.freeTableModel.getValueAt(row, 2);
                doctor.HavegivedMedicationModel.setValueAt(medicationName, row, 0);
                doctor.HavegivedMedicationModel.setValueAt(quantity, row, 1);
                doctor.HavegivedMedicationModel.setValueAt(usage, row, 2);
            }
            JOptionPane.showMessageDialog(doctor, "药单创建成功！请去已开处方查看");
        }
    }
}
