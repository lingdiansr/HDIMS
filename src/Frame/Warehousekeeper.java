package Frame;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.awt.event.ActionListener;

//任务一：按钮没调整好大小，左右很空
//任务一：工具条的高度没调整好，上下很空
//任务三：按钮对应的表格没实现响应
//任务四：表格边上的滚动还没实现
public class Warehousekeeper extends JFrame {

    //供应商工具条
    JToolBar SupplierInformationToolbar = new JToolBar("供应商信息");
    JButton SupplierNameButton = new JButton("名字");
    JButton SupplierAddressButton = new JButton("地址");
    JButton SupplierPhoneButton = new JButton("电话");

    //药品信息工具条
    JToolBar MedicationInformationToolbar = new JToolBar("药品信息");
    JButton MedicationNameButton = new JButton("药品名称");
    JButton MedicationNoButton = new JButton("药品编号");
    JButton MedicationBatchesButton = new JButton("药品批次");
    JButton MedicationNumberButton = new JButton("药品数量");
    JButton MedicationPurchasingstaffButton = new JButton("药品采购人员");
    JButton MedicationDestructionstaffButton = new JButton("药品销毁人员");

    //时间信息工具条
    JToolBar TimeInformationToolbar = new JToolBar("时间信息");
    JButton ShelfLifeButton = new JButton("保质期");
    JButton InboundTimeButton = new JButton("入库时间");
    JButton OutboundTimeButton = new JButton("保质期");
    JButton DestructionTimeButton = new JButton("销毁时间");
    JTabbedPane ButtonPane = new JTabbedPane();

    //搜索组件
    JPanel Seacherpannel = new JPanel();
    JLabel SearcherLable = new JLabel("搜索");
    JTextField SearcherField = new JTextField();
    JButton SureButton = new JButton("确定");

    JTable table = new JTable(10, 10);
    JScrollPane scrollPane = new JScrollPane(table);

    public Warehousekeeper() {
        this.setTitle("仓库管理员界面");
        this.setSize(800, 600);
        //this.setLayout(new BorderLayout());
        this.setLayout(new GridLayout(4, 1));//第一排是选择按钮，第二排是具体信息按钮，第三排是搜索框，第四排是各种具体信息（很多）
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //以下是对工具条大小的调整
//        SupplierInformationToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
//        SupplierInformationToolbar.setFloatable(false); // 禁止浮动
//        //SupplierNameButton.setPreferredSize(new Dimension(50,50));
//        int buttonHeight = SupplierNameButton.getPreferredSize().height; // 获取按钮的高度
//        SupplierInformationToolbar.setPreferredSize(new Dimension(100, buttonHeight)); // 设置工具条的大小

//将按钮加入供应商工具条
        SupplierInformationToolbar.add(SupplierNameButton);
        SupplierInformationToolbar.add(SupplierAddressButton);
        SupplierInformationToolbar.add(SupplierPhoneButton);

//将按钮加入药物信息工具条
        MedicationInformationToolbar.add(MedicationNameButton);
        MedicationInformationToolbar.add(MedicationNoButton);
        MedicationInformationToolbar.add(MedicationBatchesButton);
        MedicationInformationToolbar.add(MedicationNumberButton);
        MedicationInformationToolbar.add(MedicationPurchasingstaffButton);
        MedicationInformationToolbar.add(MedicationDestructionstaffButton);

//将按钮加入时间信息工具条
        TimeInformationToolbar.add(ShelfLifeButton);
        TimeInformationToolbar.add(InboundTimeButton);
        TimeInformationToolbar.add(OutboundTimeButton);
        TimeInformationToolbar.add(DestructionTimeButton);


//三个工具条加入标签面板
        ButtonPane.addTab("供应商信息", null, SupplierInformationToolbar);
        ButtonPane.addTab("药品信息", null, MedicationInformationToolbar);
        ButtonPane.addTab("时间信息", null, TimeInformationToolbar);

//搜索组件放入中间容器
        SearcherField.setColumns(19);
        Seacherpannel.add(SearcherLable);
        Seacherpannel.add(SearcherField);
        Seacherpannel.add(SureButton);
        Seacherpannel.setPreferredSize(new Dimension(300, 20));
//表格实现

        table.setPreferredScrollableViewportSize(new Dimension(10, 30));
        // getContentPane().add(scrollPane,BorderLayout.CENTER);
//        this.add(ButtonPanel);
        this.add(ButtonPane);
        this.add(Seacherpannel);
        this.add(table);


    }

    public static void main(String[] s) {
        Warehousekeeper frame = new Warehousekeeper();
        frame.setVisible(true);
    }
}

