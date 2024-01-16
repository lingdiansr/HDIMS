package UI;

import Service.DoctorListener;


import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.event.*;
import java.awt.event.ActionListener;


public class Doctor extends JFrame {
    String no =null;
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
    public JTextField SearcherField = new JTextField();
    public JButton SureButton = new JButton("确定");
    //药单
    JPanel LT = new JPanel();
    JLabel MedicationListLable = new JLabel("药单");

    //表格
    public MyTableModel myTableModel = new MyTableModel();
    public JTable GiveMedicationTable = new JTable((TableModel) myTableModel);
    public JButton ADDMedicationButton = new JButton("添加药物至药单");

    public MyTableModel2 freeTableModel = new MyTableModel2();
    JTable freeTable = new JTable((TableModel) freeTableModel);
    public MyTableModel3 HavegivedMedicationModel = new MyTableModel3();
    JTable HaveTable = new JTable((TableModel) HavegivedMedicationModel);
    public JButton CreatMedicationButton = new JButton("生成药单");

    public Doctor() {
        this.setTitle("医生界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout()); // 使用BorderLayout布局管理器
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.add(ButtonPane, BorderLayout.CENTER);

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
        DoctorListener acl= new DoctorListener(this);
        SureButton.addActionListener(acl);
        ADDMedicationButton.addActionListener(acl);
        CreatMedicationButton.addActionListener(acl);

        LT.setLayout(new BorderLayout());
        LT.add(MedicationListLable, "North");
        LT.add(freeTable, "Center");
        LT.add(CreatMedicationButton, "South");


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
    public Doctor(String no){
        this();
        this.no=no;
    }

    public static void main (String[] args){
        Doctor frame = new Doctor();
        frame.setVisible(true);
    }

}
