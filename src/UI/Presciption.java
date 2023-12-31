package UI;

import Service.AdminService;
import Service.NurseService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Presciption extends JFrame implements ActionListener {
    JTable drugtable = new JTable(10, 3);
    JLabel Pres = new JLabel("处方药品信息");
    JButton CancelButton = new JButton("取消");
    JButton FinishButton = new JButton("完成");
    DefaultTableModel drugmodel = (DefaultTableModel) drugtable.getModel();
    String[] columnNames1 = {"处方编号", "药品编号", "药品数量"};

    public Presciption() {
        this.setTitle("处方界面");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Font font =new Font("微软雅黑", Font.PLAIN, 36);

        Pres.setFont(font);
        CancelButton.setFont(font);
        FinishButton.setFont(font);
        Pres.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel presPanel = new JPanel(new BorderLayout());

        CancelButton.addActionListener(this);
        FinishButton.addActionListener(this);

        drugtable.setPreferredScrollableViewportSize(new Dimension(400, 200));

        this.add(presPanel, BorderLayout.CENTER);
        presPanel.add(Pres, BorderLayout.NORTH);
        presPanel.add(new JScrollPane(drugtable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(CancelButton);
        buttonPanel.add(FinishButton);
        presPanel.add(buttonPanel, BorderLayout.SOUTH);

        this.setVisible(true);

        CancelButton.addActionListener(this);
        FinishButton.addActionListener(this);
        drugtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(drugtable.getSelectedRow()==0)
                    JOptionPane.showMessageDialog(null, "配药完成");
            }
        });
    }

    public static void main(String[] s) {
        Presciption frame = new Presciption();
        frame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == CancelButton) {
            dispose();
        }
        if (e.getSource() == FinishButton) {
            dispose();
            JOptionPane.showMessageDialog(null, "配药完成");
        }
    }
}
