package UI;

import Service.AdminService;
import Util.InterfaceUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static DAO.SupplierDAO.insertSupplier;


public class Warehousekeeper extends JFrame implements ActionListener {
    JTabbedPane ButtonPane = new JTabbedPane();//个工具条共享一个空间
    //供应商工具条
    JToolBar SupplierInformationToolbar = new JToolBar("供应商信息");
    private DefaultTableModel supplierTableModel;

    JPanel SP = new JPanel();


    //药品信息工具条
    JToolBar MedicationInformationToolbar = new JToolBar("药品信息");

    JPanel MedicationInformationPanel = new JPanel();


    //搜索组件
    JPanel Seacherpannel1 = new JPanel();
    JPanel Seacherpannel2 = new JPanel();


    JLabel SearcherLable1 = new JLabel("搜索");
    JLabel SearcherLable2 = new JLabel("搜索");


    JTextField SearcherField1 = new JTextField();
    JTextField SearcherField2 = new JTextField();

    JButton SureButton1 = new JButton("确定");
    JButton SureButton2 = new JButton("确定");
    JButton AddButton1 = new JButton("添加");
    JButton AddButton2 = new JButton("添加");
    JButton DeleteButton1 = new JButton("删除");
    JButton DeleteButton2 = new JButton("删除");
    JButton ResetButton1 = new JButton("修改");
    JButton ResetButton2 = new JButton("修改");

    //
    JTable table1 = new JTable(25, 6);
    JTable table2 = new JTable(25, 6);


    public Warehousekeeper() {
        this.setTitle("仓库管理员界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SureButton1.addActionListener(this);
        SureButton2.addActionListener(this);
        AddButton1.addActionListener(this);
        AddButton2.addActionListener(this);
        DeleteButton1.addActionListener(this);
        DeleteButton2.addActionListener(this);
        ResetButton1.addActionListener(this);
        ResetButton2.addActionListener(this);


        SupplierInformationToolbar.setLayout(new BoxLayout(SupplierInformationToolbar, BoxLayout.Y_AXIS));
        // 搜索组件

        SearcherField1.setColumns(30);
        Seacherpannel1.add(SearcherLable1);
        Seacherpannel1.add(SearcherField1);
        Seacherpannel1.add(SureButton1);
        Seacherpannel1.add(AddButton1);
        Seacherpannel1.add(DeleteButton1);
        Seacherpannel1.add(ResetButton1);
        Seacherpannel1.setPreferredSize(new Dimension(300, 100));

        SearcherField2.setColumns(30);
        Seacherpannel2.add(SearcherLable2);
        Seacherpannel2.add(SearcherField2);
        Seacherpannel2.add(SureButton2);
        Seacherpannel2.add(AddButton2);
        Seacherpannel2.add(DeleteButton2);
        Seacherpannel2.add(ResetButton2);
        Seacherpannel2.setPreferredSize(new Dimension(300, 100));


        // 表格实现
        table1.setPreferredScrollableViewportSize(new Dimension(10, 10));
        table2.setPreferredScrollableViewportSize(new Dimension(10, 10));


        // 供应商界面设置布局方式,搜索在顶层，表格在中间
        SP.setLayout(new BorderLayout());
        SP.add(Seacherpannel1, BorderLayout.NORTH);
        SP.add(new JScrollPane(table1), BorderLayout.CENTER); // 使用JScrollPane将表格包装起来，以实现滚动
        SupplierInformationToolbar.add(SP);

        MedicationInformationPanel.setLayout(new BorderLayout());
        MedicationInformationPanel.add(Seacherpannel2, BorderLayout.NORTH);
        MedicationInformationPanel.add(new JScrollPane(table2), BorderLayout.CENTER); // 使用JScrollPane将表格包装起来，以实现滚动
        MedicationInformationToolbar.add(MedicationInformationPanel);


        // 两个工具条加入标签面板
        ButtonPane.addTab("供应商信息", null, SupplierInformationToolbar);
        ButtonPane.addTab("药品信息", null, MedicationInformationToolbar);

        // 设置工具条在顶部
        this.add(ButtonPane, BorderLayout.CENTER);//没有其他了，直接填满
        this.setVisible(true);
    }


    public static void main(String[] s) {
        Warehousekeeper frame = new Warehousekeeper();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SureButton1 && SupplierInformationToolbar.isEnabled()) {
            String searchtext = SearcherField1.getText();
            AdminService as = new AdminService();
            Object[][] searchResult = as.searchSupplier(searchtext);
            String[] columnNames = {"编号", "供应商名称", "供应商地址", "联系方式"};
            DefaultTableModel model = new DefaultTableModel(searchResult, columnNames);
            model.addRow(new Object[]{});
            table1.setModel(model);
        } else if (e.getSource() == SureButton2 && MedicationInformationToolbar.isEnabled()) {
            System.out.println("t2");
            String searchtext = SearcherField2.getText();
            AdminService as = new AdminService();
            Object[][] searchResult = as.searchDrug(searchtext);
            String[] columnNames = {"编号", "药品名称", "保质期（天）"};
            DefaultTableModel model = new DefaultTableModel(searchResult, columnNames);
            table2.setModel(model);
        } else if (e.getSource() == AddButton1 && SupplierInformationToolbar.isEnabled()) {
//            Object[][] data = InterfaceUtil.convertJTableToObjectArray(table1);
//            String[] columnNames = {"编号", "供应商名称", "供应商地址", "联系方式"};
//            DefaultTableModel model = new DefaultTableModel(data, columnNames);
//            table1.setModel(model);
            JOptionPane.showMessageDialog(null, "添加成功！");
//            model.addRow(new Object[]{});
        } else if (e.getSource() == AddButton2) {
            JOptionPane.showMessageDialog(null, "添加成功！");
        } else if (e.getSource() == DeleteButton1) {
            JOptionPane.showMessageDialog(null, "删除成功！");
        } else if (e.getSource() == DeleteButton2) {
            JOptionPane.showMessageDialog(null, "删除成功！");
        } else if (e.getSource() == ResetButton1) {
            JOptionPane.showMessageDialog(null, "修改成功！");
        } else if (e.getSource() == ResetButton2) {
            JOptionPane.showMessageDialog(null, "修改成功！");
        }
    }
}



