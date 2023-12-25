package Frame;
import DAO.AdminDAO;
import Service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//任务一：还有一个进度条没实现JProgressbar
//任务二：想加入一个背景图片书上262页似乎可以实现
//任务三：监听器由于其他界面没实现，只有模子
public class LoginFrame extends JFrame implements ActionListener {

    //三个标签：身份选择，用户名，密码
    JLabel IdentityLable = new JLabel("身份");

    JComboBox<String> IdentityCombox = new JComboBox<>();
    JLabel  UsernameLable= new JLabel("用户名");
    JTextField UsernameField = new JTextField();
    JLabel  PasswordLable= new JLabel("密码");
    JTextField PasswordField = new JTextField();
    //以下是两个按钮：确定，取消
    JButton SureButton = new JButton("确定");
    JButton CancelButton =new JButton("取消");
    //以下是几个中间容器
    JPanel IdentityPannel = new JPanel();
    JPanel UsernamePannel = new JPanel();
    JPanel PasswordPannel = new JPanel();
    JPanel ButtonPannel = new JPanel();
    public LoginFrame(){

        IdentityCombox.addItem("患者");
        IdentityCombox.addItem("医生");
        IdentityCombox.addItem("护士");
        IdentityCombox.addItem("仓库管理员");

        this.setTitle("药物管理系统登录界面");
        this.setSize(800, 600);
        this.setLayout(new GridLayout(6, 1));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        UsernameField.setColumns(19);
        PasswordField.setColumns(20);

        IdentityPannel.add(IdentityLable);
        IdentityPannel.add(IdentityCombox);

        UsernamePannel.add(UsernameLable);
        UsernamePannel.add(UsernameField);

        PasswordPannel.add(PasswordLable);
        PasswordPannel.add(PasswordField);

        ButtonPannel.add(SureButton);
        ButtonPannel.add(CancelButton);

        this.add(new JPanel());
        this.add(IdentityPannel);
        this.add(UsernamePannel);
        this.add(PasswordPannel);
        this.add(ButtonPannel);
        this.add(new JPanel());

        SureButton.addActionListener(this);
        CancelButton.addActionListener(this);
        IdentityCombox.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String selectedValue = null;


        if (e.getSource() == SureButton) {
            UserService us =new UserService();
            //打开另一个界面
            //有三个打开哪个,再做一次判断
            selectedValue = (String) IdentityCombox.getSelectedItem();
            if (selectedValue.equals("患者")) {
                JOptionPane.showMessageDialog(null, "你打开了患者界面（目前没实现）");

            }
            //打开患者窗口
            else if (selectedValue.equals("医生")) {
                System.out.println("Selected Option 2");

            } else if (selectedValue.equals("护士")) {
                System.out.println("Selected Option 3");
                if(us.NurseRight(UsernameField.getText(), PasswordField.getText())){
                    dispose();
                    new Nurse();
                }


            } else if (selectedValue.equals("仓库管理员")) {
                System.out.println("Selected Option 4");

                if(us.AdminRight(UsernameField.getText(), PasswordField.getText())){
                    dispose();
                    new Warehousekeeper();
                }



            }
        } else if (e.getSource() == CancelButton) {//点取消退出系统
            System.exit(0);
        }
    }

    public static void main(String[] s) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
    }

}
