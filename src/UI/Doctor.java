package UI;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class Doctor extends JFrame implements ActionListener {
    JTabbedPane ButtonPane = new JTabbedPane();//让三个工具条共享同一屏幕区域

    //开处方工具条
    JToolBar GiveMedicationToolbar = new JToolBar("开处方");
    JPanel GiveMedicationPanel = new JPanel(); // 新增一个中间容器



    //开处方中已经被选择的药物
    JLabel HaveSelectedMedicationLable = new JLabel("药单：");
    JPanel HaveSelectedMedicationPannel = new JPanel();

    //-------------------------------------------------------------------------------
    //已开处方工具条
    JToolBar HavegivedMedicationToolbar = new JToolBar("");


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
    JButton CreatMedicationButton = new JButton("生成药单");

    MyTableModel2 freeTableModel = new MyTableModel2();
    JTable freeTable = new JTable(freeTableModel);

    public Doctor() {
        this.setTitle("医生界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout()); // 使用BorderLayout布局管理器
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(ButtonPane, BorderLayout.CENTER);

        //开药表
        GiveMedicationTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
        freeTable.setPreferredScrollableViewportSize(new Dimension(700, 100));

        GiveMedicationToolbar.setLayout(new BoxLayout(GiveMedicationToolbar, BoxLayout.Y_AXIS));
        HavegivedMedicationToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        // 将标签文字提示添加到开处方新面板




        //将药单标签加入中间容器，中间容器还没放到大框架里面，要新开一个布局的中间容器，放到搜索下面（包括搜索）
        HaveSelectedMedicationPannel.add(HaveSelectedMedicationLable);

        //将按钮加入已开处方工具条
//        MedicationInformationToolbar.add(MedicationNameButton);
//        MedicationInformationToolbar.add(MedicationNoButton);
//        MedicationInformationToolbar.add(MedicationBatchesButton);
//        MedicationInformationToolbar.add(MedicationNumberButton);
//        MedicationInformationToolbar.add(MedicationPurchasingstaffButton);
//        MedicationInformationToolbar.add(MedicationDestructionstaffButton);


        //将按钮加入时间信息工具条
//        TimeInformationToolbar.add(ShelfLifeButton);
//        TimeInformationToolbar.add(InboundTimeButton);
//        TimeInformationToolbar.add(OutboundTimeButton);
//        TimeInformationToolbar.add(DestructionTimeButton);


        // ButtonPane.addTab("已开处方", null, TimeInformationToolbar);

        //搜索组件放入中间容器，放到工具条里才对,我之前都放在公共了！
        SearcherField.setColumns(19);
        Seacherpannel.add(SearcherLable);
        Seacherpannel.add(SearcherField);
        Seacherpannel.add(SureButton);
        Seacherpannel.add(CreatMedicationButton);
        Seacherpannel.setPreferredSize(new Dimension(400, 30));

        SureButton.addActionListener(this);
        CreatMedicationButton.addActionListener(this);
        LT.setLayout(new BorderLayout());
        LT.add(MedicationListLable, "North");
        LT.add(freeTable, "South");

        //table.setPreferredScrollableViewportSize(new Dimension(350, 200));
        GiveMedicationToolbar.add(GiveMedicationPanel, "North");
        GiveMedicationToolbar.add(Seacherpannel, "Center");
        GiveMedicationToolbar.add(LT, "South");

        JScrollPane scrollPane = new JScrollPane(GiveMedicationTable);
        JScrollPane scrollPane2 = new JScrollPane(freeTable);
        GiveMedicationPanel.add(scrollPane);
        LT.add(scrollPane2);




        this.add(ButtonPane);
        //this.add(Seacherpannel);
        //this.add(scrollPane);
        //将工具条加入总体的标签面板
        ButtonPane.addTab("开处方", null, GiveMedicationToolbar);

    }


    class MyTableModel extends AbstractTableModel {
        final String[] columnNames = {"药名", "产地", "选择"};
        final Object[][] data = {
                {"阿司匹林", "china", false},
                {"蒙脱石散", "china", false}
        };
        final Class[] columnClasses = {String.class, String.class, Boolean.class};

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
            return data[row][col];
        }

        public Class getColumnClass(int c) {
            return columnClasses[c];
        }

        public boolean isCellEditable(int row, int col) { // 设置编辑单元格
            return col == 2; // 只允许编辑选择列
        }

        public void setValueAt(Object value, int row, int col) {
            data[row][col] = value;
            fireTableCellUpdated(row, col);
        }
    }

    class MyTableModel2 extends AbstractTableModel {
        final String[] columnNames = {"药名", "数量", "服用方法"};
        final Object[][] data = {
                {"", "", ""},
                {"", "", ""},
                {"", "", ""}
        };

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

        public void setRowCount() {
            for (int row = 0; row < data.length; row++) {
                data[row][0] = ""; // 或 data[row][0] = null;
            }
            fireTableDataChanged();
        }



    }


    @Override//为了避免取消后还在表格中的问题，用到每次更新前清除的思想
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == CreatMedicationButton) {
            int selectedRow = GiveMedicationTable.getSelectedRow();
            int selectedColumn = GiveMedicationTable.getSelectedColumn();
            int RowCount = GiveMedicationTable.getRowCount();
            for (int k = 0; k <= RowCount; k++) {
                if (selectedColumn == 2) { // 第三列
                    GiveMedicationTable.setValueAt(true, selectedRow, selectedColumn); // 修改为true

                    // 更新选中的药品名到freeTable
                    String medicationName = (String) GiveMedicationTable.getValueAt(k, 0); // 获取药品名
                    freeTableModel.setValueAt(medicationName, k, 0); // 更新freeTable中对应行的药品名单元格的值
                    freeTable.repaint(); // 刷新freeTable显示


                }
            }
            JOptionPane.showMessageDialog(null, "已生成");
        }
    }



    public static void main(String[] args) {
        Doctor frame = new Doctor();
        frame.setVisible(true);
    }
}
