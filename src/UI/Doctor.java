package UI;

import Service.DoctorService;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class Doctor extends JFrame implements ActionListener {
    JTabbedPane ButtonPane = new JTabbedPane();//让两个工具条共享同一屏幕区域

    //开处方工具条
    JToolBar GiveMedicationToolbar = new JToolBar("开处方");
    JPanel GiveMedicationPanel = new JPanel(); // 新增一个中间容器


    //-------------------------------------------------------------------------------
    //已开处方工具条
    JToolBar HavegivedMedicationToolbar = new JToolBar("");
    JPanel HavegivedMedicationPanel = new JPanel(); // 新增一个中间容器

    //搜索组件
    JPanel Seacherpannel = new JPanel();
    JLabel SearcherLable = new JLabel("搜索");
    JTextField SearcherField = new JTextField();
    JButton SureButton = new JButton("确定");
    //药单
    JPanel LT = new JPanel();
    JLabel MedicationListLable = new JLabel("药单");

    //表格
    MyTableModel myTableModel = new MyTableModel();
    JTable GiveMedicationTable = new JTable(myTableModel);
    JButton ADDMedicationButton = new JButton("添加药物至药单");

    MyTableModel2 freeTableModel = new MyTableModel2();
    JTable freeTable = new JTable(freeTableModel);
    MyTableModel3 HavegivedMedicationModel = new MyTableModel3();
    JTable HaveTable = new JTable(HavegivedMedicationModel);
    JButton CreatMedicationButton = new JButton("生成药单");

    public Doctor() {
        this.setTitle("医生界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout()); // 使用BorderLayout布局管理器
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(ButtonPane, BorderLayout.CENTER);

        //开处方界面药表
        GiveMedicationTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
        freeTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
        HaveTable.setPreferredScrollableViewportSize(new Dimension(700, 100));

        //开处方界面的工具条布局
        GiveMedicationToolbar.setLayout(new BoxLayout(GiveMedicationToolbar, BoxLayout.Y_AXIS));
        //已开处方界面的工具条布局
        HavegivedMedicationToolbar.setLayout(new BoxLayout(HavegivedMedicationToolbar, BoxLayout.Y_AXIS));


        //开处方界面搜索组件放入中间容器，放到工具条里才对,我之前都放在公共了！
        SearcherField.setColumns(19);
        Seacherpannel.add(SearcherLable);
        Seacherpannel.add(SearcherField);
        Seacherpannel.add(SureButton);
        Seacherpannel.add(ADDMedicationButton);
        Seacherpannel.setPreferredSize(new Dimension(400, 30));
        //按钮响应实现
        SureButton.addActionListener(this);
        ADDMedicationButton.addActionListener(this);
        CreatMedicationButton.addActionListener(this);

        LT.setLayout(new BorderLayout());
        LT.add(MedicationListLable, "North");
        LT.add(freeTable, "Center");
        LT.add(CreatMedicationButton, "South");

        //table.setPreferredScrollableViewportSize(new Dimension(350, 200));
        GiveMedicationToolbar.add(GiveMedicationPanel, "North");
        GiveMedicationToolbar.add(Seacherpannel, "Center");
        GiveMedicationToolbar.add(LT, "South");
        //已开处方中间容器加入工具条中(表格放入滚动容器，滚动容器放入中间容器，中间容器放入工具条)
        JScrollPane scrollPane3 = new JScrollPane(HaveTable);
        HavegivedMedicationPanel.add(scrollPane3);
        HavegivedMedicationToolbar.add(HavegivedMedicationPanel);

        JScrollPane scrollPane = new JScrollPane(GiveMedicationTable);
        JScrollPane scrollPane2 = new JScrollPane(freeTable);


        GiveMedicationPanel.add(scrollPane);
        LT.add(scrollPane2);

        //将两个工具条加入总体的标签面板
        ButtonPane.addTab("开处方", null, GiveMedicationToolbar);
        ButtonPane.addTab("已开处方", null, HavegivedMedicationToolbar);
        this.add(ButtonPane);
    }

    @Override//为了避免取消后还在表格中的问题，用到每次更新前清除的思想
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == ADDMedicationButton) {
            freeTableModel.resetRowCount();
            int k = 0;
            int rowCount = GiveMedicationTable.getRowCount();
            int selectedColumn = 4; // 第三列索引
            for (int row = 0; row < rowCount; row++) {
                boolean isSelected = (boolean) GiveMedicationTable.getValueAt(row, selectedColumn);
                if (isSelected) { // 如果药物被选中
                    String medicationName = (String) GiveMedicationTable.getValueAt(row, 0); // 获取药品名
                    freeTableModel.setValueAt(medicationName, k, 0); // 更新药品名单元格
                    k++;
                }
            }
            JOptionPane.showMessageDialog(null, "已添加");
        } else if (event.getSource() == CreatMedicationButton) {
            // 在已开处方工具条中添加药物数据的逻辑
            int rowCount = freeTableModel.getRowCount();
            for (int row = 0; row < rowCount; row++) {
                String medicationName = (String) freeTableModel.getValueAt(row, 0);
                String quantity = (String) freeTableModel.getValueAt(row, 1);
                String usage = (String) freeTableModel.getValueAt(row, 2);
                // 在这里将药物数据添加到已开处方工具条的表格中
                HavegivedMedicationModel.setValueAt(medicationName, row, 0);
                HavegivedMedicationModel.setValueAt(quantity, row, 1);
                HavegivedMedicationModel.setValueAt(usage, row, 2);
            }
            // 通知已开处方工具条的表格模型更新数据
            JOptionPane.showMessageDialog(null, "药单创建成功！请去已开处方查看");
        } else if (event.getSource() == SureButton) {
            String searchText = SearcherField.getText();
            DoctorService ds = new DoctorService();
            Object[][] searchResult = ds.Search(searchText);
            Object[][] searchResultWithCheck = new Object[searchResult.length][searchResult[0].length + 1];
            for (int i = 0; i < searchResult.length; i++) {
                System.arraycopy(searchResult[i], 0, searchResultWithCheck[i], 0, searchResult[i].length);
                searchResultWithCheck[i][searchResult[i].length] = false;
            }
//            System.out.println("数组测试");
//            for (int i = 0; i < searchResult.length; i++) {
//                for (int j = 0; j < searchResult[i].length; j++) {
//                    System.out.print(searchResult[i][j].toString() + " ");
//                }
//                System.out.println(); // 在每行结束时换行
//            }
            myTableModel.setData(searchResultWithCheck);
        }


    }
    public static void main (String[] args){
        Doctor frame = new Doctor();
        frame.setVisible(true);
    }

    public class MyTableModel extends AbstractTableModel {
        final String[] columnNames = {"PDno", "PDname", "PDlife", "PDnum", "选择"};
        Object[][] data = {{" ", " ", " ", 0, false} };
        final Class[] columnClasses = {String.class, String.class, String.class, Integer.class, Boolean.class};
        @Override
        public int getRowCount() {
            return data.length;
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
//            System.out.println("R:"+rowIndex+"C:"+columnIndex);
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnClasses[columnIndex];
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return true;
        }

        @Override
        public void setValueAt(Object value, int rowIndex, int columnIndex) {
            data[rowIndex][columnIndex] = value;
            fireTableCellUpdated(rowIndex, columnIndex);
        }
        public void setData(Object[][] newData) {
            data = newData;
            fireTableDataChanged();
        }
    }

    class MyTableModel2 extends AbstractTableModel {
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

    class MyTableModel3 extends AbstractTableModel {
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

}