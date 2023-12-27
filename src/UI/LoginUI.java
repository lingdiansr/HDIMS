package UI;

import Entity.Admin;
import Entity.Nurse;
import Entity.Doctor;
import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame implements ActionListener {

    // 使用复选框进行身份选择
    JLabel IdentityLable = new JLabel("  身 份:");
    JCheckBox Identityadmin = new JCheckBox("仓库管理员");
    JCheckBox Identitynurse = new JCheckBox("护士");
    JCheckBox Identitydoctor = new JCheckBox("医生");

    JLabel UsernameLable = new JLabel(" 工 号:");
    JTextField UsernameField = new JTextField();

    JLabel PasswordLable = new JLabel(" 密 码:");
    JPasswordField PasswordField = new JPasswordField();

    JButton SureButton = new JButton("确定");
    JButton CancelButton = new JButton("取消");

    JPanel IdentityPannel = new JPanel();
    JPanel UsernamePannel = new JPanel();
    JPanel PasswordPannel = new JPanel();
    JPanel ButtonPannel = new JPanel();

    public LoginUI() {

        // 将三个身份的复选框逐个放进一个按钮里，同时使用一个对象来控制身份的选择，每次只能选择一个
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(Identityadmin);
        buttonGroup.add(Identitynurse);
        buttonGroup.add(Identitydoctor);

        this.setTitle("药物管理系统登录界面");
        this.setSize(800, 600);
        this.setLayout(new GridLayout(6, 2));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        UsernameField.setColumns(19);
        PasswordField.setColumns(20);

        IdentityPannel.add(IdentityLable);
        IdentityPannel.add(Identityadmin);
        IdentityPannel.add(Identitynurse);
        IdentityPannel.add(Identitydoctor);

        UsernamePannel.add(UsernameLable);
        UsernamePannel.add(UsernameField);

        PasswordPannel.add(PasswordLable);
        PasswordPannel.add(PasswordField);
        // 使用空Label来调整按钮间的间距
        ButtonPannel.add(SureButton);
        ButtonPannel.add(new JLabel("    "));
        ButtonPannel.add(new JLabel("    "));
        ButtonPannel.add(new JLabel("    "));
        ButtonPannel.add(CancelButton);

        this.add(new JPanel());
        this.add(IdentityPannel);
        this.add(UsernamePannel);
        this.add(PasswordPannel);
        this.add(ButtonPannel);
        this.add(new JPanel());

        SureButton.addActionListener(this);
        CancelButton.addActionListener(this);
        Identityadmin.addActionListener(this);
        Identitynurse.addActionListener(this);
        Identitydoctor.addActionListener(this);
        // 设置字体大小
        Font font = new Font("微软雅黑", Font.PLAIN, 40);
        Font font1 = new Font("微软雅黑", Font.PLAIN, 36);
        Identityadmin.setFont(font1);
        Identitynurse.setFont(font1);
        Identitydoctor.setFont(font1);
        IdentityLable.setFont(font);
        UsernameLable.setFont(font);
        PasswordLable.setFont(font);
        UsernameField.setFont(font);
        PasswordField.setFont(font);
        UsernameField.setColumns(10);
        PasswordField.setColumns(10);
        SureButton.setFont(font);
        SureButton.setFocusPainted(false);

        CancelButton.setFocusPainted(false);
        CancelButton.setFont(font);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = UsernameField.getText();
        String password = new String(PasswordField.getPassword());

        if (e.getSource() == SureButton) {
            if (id.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请正确输入用户名和密码", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                UserService us = new UserService();
                Nurse nurse = null;
                Doctor doctor = null;
                if (!Identitydoctor.isSelected() && !Identitynurse.isSelected() && !Identityadmin.isSelected()) {
                    JOptionPane.showMessageDialog(this, "请选择身份", "错误", JOptionPane.ERROR_MESSAGE);
                } else if (Identitydoctor.isSelected()) {
                    System.out.println("Selected Option 1");
                    doctor = new Doctor();
                    doctor.setDno(id);
                    doctor.setDpwd(password);
                    if (us.DoctorRight(doctor)) {
                        dispose();
                        new Doctor();
                    }
                } else if (Identitynurse.isSelected()) {
                    System.out.println("Selected Option 2");
                    nurse = new Nurse();
                    nurse.setNno(id);
                    nurse.setNpwd(password);
                    if (us.NurseRight(nurse)) {
                        dispose();
                        new Nurse();
                    }
                } else if (Identityadmin.isSelected()) {
                    System.out.println("Selected Option 3");
                    Admin admin = new Admin();
                    admin.setAno(id);
                    admin.setApwd(password);
                    if (us.AdminRight(admin)) {
                        dispose();
                        new Warehousekeeper();
                    } else {
                        JOptionPane.showMessageDialog(this, "工号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == CancelButton) {
            System.exit(0);
        }
    }

    public static void main(String[] s) {
        LoginUI frame = new LoginUI();
        frame.setVisible(true);
    }
}
