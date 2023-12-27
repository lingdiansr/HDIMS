package UI;

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
    JButton CreatMedicationButton = new JButton("生成药单");
    public Doctor() {
        this.setTitle("医生界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout()); // 使用BorderLayout布局管理器
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(ButtonPane, BorderLayout.CENTER);

        //药表
        GiveMedicationTable.setPreferredScrollableViewportSize(new Dimension(700, 100));
        freeTable.setPreferredScrollableViewportSize(new Dimension(700, 100));

        GiveMedicationToolbar.setLayout(new BoxLayout(GiveMedicationToolbar, BoxLayout.Y_AXIS));
        HavegivedMedicationToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));





        //将药单标签加入中间容器，中间容器还没放到大框架里面，要新开一个布局的中间容器，放到搜索下面（包括搜索）
        //HaveSelectedMedicationPannel.add(HaveSelectedMedicationLable);

        //将按钮加入已开处方工具条


        ButtonPane.addTab("已开处方", null, HavegivedMedicationToolbar);

        //搜索组件放入中间容器，放到工具条里才对,我之前都放在公共了！
        SearcherField.setColumns(19);
        Seacherpannel.add(SearcherLable);
        Seacherpannel.add(SearcherField);
        Seacherpannel.add(SureButton);
        Seacherpannel.add(ADDMedicationButton);
        Seacherpannel.setPreferredSize(new Dimension(400, 30));

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

        JScrollPane scrollPane = new JScrollPane(GiveMedicationTable);
        JScrollPane scrollPane2 = new JScrollPane(freeTable);
        GiveMedicationPanel.add(scrollPane);
        LT.add(scrollPane2);



        //将工具条加入总体的标签面板
        ButtonPane.addTab("开处方", null, GiveMedicationToolbar);
        ButtonPane.addTab("已开处方", null, HavegivedMedicationToolbar);
        this.add(ButtonPane);
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


    @Override//为了避免取消后还在表格中的问题，用到每次更新前清除的思想
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == ADDMedicationButton) {
            freeTableModel.resetRowCount();
            int k = 0;
            int rowCount = GiveMedicationTable.getRowCount();
            int selectedColumn = 2; // 第三列索引
            for (int row = 0; row < rowCount; row++) {
                boolean isSelected = (boolean) GiveMedicationTable.getValueAt(row, selectedColumn);
                if (isSelected) { // 如果药物被选中
                    String medicationName = (String) GiveMedicationTable.getValueAt(row, 0); // 获取药品名
                    freeTableModel.setValueAt(medicationName, k, 0); // 更新对应行的药品名单元格的值,应该不是对应行数
                    k++;
                }
            }
            JOptionPane.showMessageDialog(null, "已添加");
        } else if (event.getSource() == CreatMedicationButton) {
            JOptionPane.showMessageDialog(null, "处方创建成功！请去已开处方查看");
        }
    }


    public static void main(String[] args) {
        Doctor frame = new Doctor();
        frame.setVisible(true);
    }
}

