package Frame;
import javax.swing.*;
import java.awt.*;
public class LoginFrame extends JFrame{
  //  JFrame LoginFrame = new JFrame();
    //三个标签：身份选择，用户名，密码
    JLabel IdentityLable = new JLabel("身份");
//    JTextField IdentityField = new JTextField();
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
//        LoginFrame.setTitle("药物管理系统登录界面");
//        LoginFrame.setSize(800, 600);
//        LoginFrame.setLayout(new GridLayout(6, 1));
//        LoginFrame.setLocationRelativeTo(null);
//        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        LoginFrame.setVisible(true);
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


    }

    public static void main(String s[]) {
        LoginFrame frame = new LoginFrame();
        frame.setVisible(true);
    }

}
