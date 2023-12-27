package Frame;

import DAO.AdminDAO;
import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.IdentityHashMap;

public class LoginFrame extends JFrame implements ActionListener {

//使用复选框进行身份选择
    JLabel IdentityLable = new JLabel("  身   份:");
    JCheckBox Identityadmin = new JCheckBox("仓库管理员");
    JCheckBox Identitynurse = new JCheckBox("护士");
    JCheckBox Identitydoctor = new JCheckBox("医生");

    JLabel UsernameLable = new JLabel("用 户 名:");
    JTextField UsernameField = new JTextField();

    JLabel PasswordLable = new JLabel(" 密　 码:");
    JPasswordField PasswordField = new JPasswordField();

    JButton SureButton = new JButton("确定");
    JButton CancelButton = new JButton("取消");

    JPanel IdentityPannel = new JPanel();
    JPanel UsernamePannel = new JPanel();
    JPanel PasswordPannel = new JPanel();
    JPanel ButtonPannel = new JPanel();

    public LoginFrame() {

        //将三个身份的复选框逐个放进一个按钮里，同时使用一个对象来控制身份的选择，每次只能选择一个
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
        //使用空Label来调整按钮间的间距
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
        //设置字体大小
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
            UserService us = new UserService();
            if (Identitydoctor.isSelected()) {
                System.out.println("Selected Option 1");
                if (us.DoctorRight(id, password)) {
                    dispose();
                    new Doctor();
                }

            } else if (Identitynurse.isSelected()) {
                System.out.println("Selected Option 2");
                if (us.NurseRight(id, password)) {
                    dispose();
                    new Nurse();
                }

            } else if (Identityadmin.isSelected()) {
                System.out.println("Selected Option 3");
                if (us.AdminRight(id, password)) {
                    dispose();
                    new Warehousekeeper();
                }
            }
        } else if (e.getSource() == CancelButton) {
            System.exit(0);
        }
    }

    public static void main(String[] s) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
    }
}
