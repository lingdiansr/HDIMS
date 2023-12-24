package Frame;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.*;
import java.awt.event.ActionListener;

public class Doctor extends JFrame {
    JTabbedPane ButtonPane = new JTabbedPane();
    //药品信息工具条
    JToolBar MedicationInformationToolbar = new JToolBar("药品信息");
//    JButton MedicationNameButton = new JButton("药品名称");
//    JButton MedicationNoButton = new JButton("药品编号");
//    JButton MedicationBatchesButton = new JButton("药品批次");
//    JButton MedicationNumberButton = new JButton("药品数量");
//    JButton MedicationPurchasingstaffButton = new JButton("药品采购人员");
//    JButton MedicationDestructionstaffButton = new JButton("药品销毁人员");

    //开处方工具条
    JToolBar GiveMedicationToolbar = new JToolBar("开处方");
    JPanel GiveMedicationPanel = new JPanel(); // 新增一个面板
    JLabel GiveMedicationNameLable = new JLabel("药名                                                                                     ");
    JLabel GiveMedicationLocateLable = new JLabel("产地");
    JLabel GiveMedicationSelectLable = new JLabel("                                                                                                       是否选择");

    //开处方中已经被选择的药物
    JLabel HaveSelectedMedicationLable = new JLabel("药单：");
    JPanel HaveSelectedMedicationPannel = new JPanel();

    //已开处方工具条
    JToolBar TimeInformationToolbar = new JToolBar("");
//    JButton ShelfLifeButton = new JButton("保质期");
//    JButton InboundTimeButton = new JButton("入库时间");
//    JButton OutboundTimeButton = new JButton("保质期");
//    JButton DestructionTimeButton = new JButton("销毁时间");

    JPanel SLT = new JPanel();

    //搜索组件
    JPanel Seacherpannel = new JPanel();
    JLabel SearcherLable = new JLabel("搜索");
    JTextField SearcherField = new JTextField();
    JButton SureButton = new JButton("确定");
    //药单
    JPanel MedicationListpannel = new JPanel();
    JLabel MedicationListLable = new JLabel("药单");
    //表格组件 两张表
    JTable table = new JTable();
    JScrollPane scrollPane = new JScrollPane(table);
    JPanel scrollpannel = new JPanel();
    JScrollPane HavescrollPane = new JScrollPane(table);

    public Doctor() {
        this.setTitle("医生界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout()); // 使用BorderLayout布局管理器
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(ButtonPane, BorderLayout.CENTER);


        GiveMedicationToolbar.setLayout(new BorderLayout());
        MedicationInformationToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        TimeInformationToolbar.setLayout(new FlowLayout(FlowLayout.LEFT));
        // 将标签添加到开处方新面板
        GiveMedicationPanel.add(GiveMedicationNameLable);
        GiveMedicationPanel.add(GiveMedicationLocateLable);
        GiveMedicationPanel.add(GiveMedicationSelectLable);


        scrollpannel.add(scrollPane);
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

        //将工具条加入总体的标签面板
        ButtonPane.addTab("药品信息", null, MedicationInformationToolbar);
        ButtonPane.addTab("开处方", null, GiveMedicationToolbar);
        ButtonPane.addTab("已开处方", null, TimeInformationToolbar);

        //搜索组件放入中间容器，放到工具条里才对,我之前都放在公共了！
        SearcherField.setColumns(19);
        Seacherpannel.add(SearcherLable);
        Seacherpannel.add(SearcherField);
        Seacherpannel.add(SureButton);
        Seacherpannel.setPreferredSize(new Dimension(400, 30));


        MedicationListpannel.add(MedicationListLable);

        SLT.setLayout(new BorderLayout());
        SLT.add(Seacherpannel, "North");
        SLT.add(HaveSelectedMedicationLable, "Center");
        SLT.add(HavescrollPane, "South");

        table.setPreferredScrollableViewportSize(new Dimension(350, 200));
        GiveMedicationToolbar.add(GiveMedicationPanel, "North");
        GiveMedicationToolbar.add(scrollpannel, "Center");
        GiveMedicationToolbar.add(SLT, "South");


        this.add(ButtonPane);
        //this.add(Seacherpannel);
        //this.add(scrollPane);

        this.setVisible(true);
    }

    public static void main(String[] s) {
        Doctor frame = new Doctor();
        frame.setVisible(true);
    }
}