package UI;

import Service.AdminService;
import Service.NurseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Nurse extends JFrame implements ActionListener {
    String no =null;
    JTabbedPane ButtonPane = new JTabbedPane();//三个工具条共享一个空间
    //供应商工具条
    JToolBar SupplierInformationToolbar = new JToolBar("查看库存");

    JPanel SupplierInformationPanel = new JPanel();
    JPanel SP = new JPanel();


    //药品信息工具条
    JToolBar MedicationInformationToolbar = new JToolBar("查看处方");

    JPanel MedicationInformationPanel = new JPanel();

    //时间信息工具条
    JToolBar TimeInformationToolbar = new JToolBar("查看未处理处方");

    JPanel TimeInformationPanel = new JPanel();
    //标签页面


    //搜索组件一
    JPanel Seacherpannel1 = new JPanel();
    JPanel Seacherpannel2 = new JPanel();
    JPanel Seacherpannel3 = new JPanel();

    JLabel SearcherLable1 = new JLabel("搜索");
    JLabel SearcherLable2 = new JLabel("搜索");
    JLabel SearcherLable3 = new JLabel("搜索");

    JTextField SearcherField1 = new JTextField();
    JTextField SearcherField2 = new JTextField();
    JTextField SearcherField3 = new JTextField();
    JButton SureButton1 = new JButton("确定");
    JButton SureButton2 = new JButton("确定");
    JButton SureButton3 = new JButton("确定");

    JTable table1 = new JTable(10, 10);
    JTable table2 = new JTable(10, 10);
    JTable table3 = new JTable(10, 10);

    NurseService as = new NurseService();
    public Nurse() {
        this.setTitle("护士界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SureButton1.addActionListener(this);
        SureButton2.addActionListener(this);
        SureButton3.addActionListener(this);

        SupplierInformationToolbar.setLayout(new BoxLayout(SupplierInformationToolbar, BoxLayout.Y_AXIS));
        // 搜索组件

        SearcherField1.setColumns(50);
        Seacherpannel1.add(SearcherLable1);
        Seacherpannel1.add(SearcherField1);
        Seacherpannel1.add(SureButton1);
        Seacherpannel1.setPreferredSize(new Dimension(300, 100));

        SearcherField2.setColumns(50);
        Seacherpannel2.add(SearcherLable2);
        Seacherpannel2.add(SearcherField2);
        Seacherpannel2.add(SureButton2);
        Seacherpannel2.setPreferredSize(new Dimension(300, 100));

        SearcherField3.setColumns(50);
        Seacherpannel3.add(SearcherLable3);
        Seacherpannel3.add(SearcherField3);
        Seacherpannel3.add(SureButton3);
        Seacherpannel3.setPreferredSize(new Dimension(300, 100));

        // 表格实现
        table1.setPreferredScrollableViewportSize(new Dimension(10, 10));
        table2.setPreferredScrollableViewportSize(new Dimension(10, 10));
        table3.setPreferredScrollableViewportSize(new Dimension(10, 10));

        // 供应商界面设置布局方式,搜索在顶层，表格在中间
        SP.setLayout(new BorderLayout());
        SP.add(Seacherpannel1, BorderLayout.NORTH);
        SP.add(new JScrollPane(table1), BorderLayout.CENTER); // 使用JScrollPane将表格包装起来，以实现滚动
        SupplierInformationToolbar.add(SP);

        MedicationInformationPanel.setLayout(new BorderLayout());
        MedicationInformationPanel.add(Seacherpannel2, BorderLayout.NORTH);
        MedicationInformationPanel.add(new JScrollPane(table2), BorderLayout.CENTER); // 使用JScrollPane将表格包装起来，以实现滚动
        MedicationInformationToolbar.add(MedicationInformationPanel);

        TimeInformationPanel.setLayout(new BorderLayout());
        TimeInformationPanel.add(Seacherpannel3, BorderLayout.NORTH);
        TimeInformationPanel.add(new JScrollPane(table3), BorderLayout.CENTER); // 使用JScrollPane将表格包装起来，以实现滚动
        TimeInformationToolbar.add(TimeInformationPanel);

        // 三个工具条加入标签面板
        ButtonPane.addTab("查看处方", null, SupplierInformationToolbar);
        ButtonPane.addTab("查看库存", null, MedicationInformationToolbar);
        ButtonPane.addTab("查看未处理处方", null, TimeInformationToolbar);


        // 设置工具条在顶部
        this.add(ButtonPane, BorderLayout.CENTER);//没有其他了，直接填满
        this.setVisible(true);


    }

public Nurse(String no){
        this();
        this.no=no;
}
    public static void main(String[] s) {
        Nurse frame = new Nurse();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == SureButton1 && SupplierInformationToolbar.isEnabled()) {
            String searchtext = SearcherField1.getText();
           // NurseService as = new NurseService();
            Object[][] searchResult = as.searchPrescription(searchtext);
            String[] columnNames = {"处方编号", "病人身份证", "开出医生", "开出时间","处理护士编号","处理时间","状态"};

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setDataVector(searchResult, columnNames);

        }else if (e.getSource() == SureButton2 && SupplierInformationToolbar.isEnabled()) {
            String searchtext = SearcherField2.getText();
            //NurseService as = new NurseService();
            Object[][] searchResult = as.searchInventory(searchtext);
            String[] columnNames = {"药品编号", "批次", "数量", "供应商编号","入库管理员编号","入库时间"};
            DefaultTableModel model = (DefaultTableModel) table2.getModel();
            model.setDataVector(searchResult, columnNames);

        }else if (e.getSource() == SureButton3 && SupplierInformationToolbar.isEnabled()) {
            String searchtext = SearcherField3.getText();
            //NurseService as = new NurseService();
            Object[][] searchResult = as.searchPrescription(searchtext,false);
            String[] columnNames = {"处方编号", "病人身份证", "开出医生", "开出时间","处理护士编号","处理时间","状态"};
            DefaultTableModel model = (DefaultTableModel) table3.getModel();
            model.setDataVector(searchResult, columnNames);

        }

    }
}

