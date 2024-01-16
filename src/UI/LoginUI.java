package UI;

import Entity.Admin;
import Entity.Nurse;
import Entity.Doctor;
import Service.LoginListener;
import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginUI extends JFrame {

    // 使用复选框进行身份选择
    JLabel IdentityLable = new JLabel("  身 份:");
    public JCheckBox Identityadmin = new JCheckBox("仓库管理员");
    public JCheckBox Identitynurse = new JCheckBox("护士");
    public JCheckBox Identitydoctor = new JCheckBox("医生");

    JLabel UsernameLable = new JLabel(" 工 号:");
    public JTextField UsernameField = new JTextField();

    JLabel PasswordLable = new JLabel(" 密 码:");
    public JPasswordField PasswordField = new JPasswordField();

    public JButton SureButton = new JButton("确定");
    public JButton CancelButton = new JButton("取消");

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

        LoginListener ll = new LoginListener(this);
        SureButton.addActionListener(ll);
        CancelButton.addActionListener(ll);
        Identityadmin.addActionListener(ll);
        Identitynurse.addActionListener(ll);
        Identitydoctor.addActionListener(ll);
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


    public static void main(String[] s) {
        LoginUI frame = new LoginUI();
        frame.setVisible(true);
    }
}
