package Frame;
import javax.swing.*;
import java.awt.*;
public class LoginFrame extends JFrame{
  //  JFrame LoginFrame = new JFrame();
    //������ǩ�����ѡ���û���������
    JLabel IdentityLable = new JLabel("���");
//    JTextField IdentityField = new JTextField();
    JComboBox<String> IdentityCombox = new JComboBox<>();
    JLabel  UsernameLable= new JLabel("�û���");
    JTextField UsernameField = new JTextField();
    JLabel  PasswordLable= new JLabel("����");
    JTextField PasswordField = new JTextField();
    //������������ť��ȷ����ȡ��
    JButton SureButton = new JButton("ȷ��");
    JButton CancelButton =new JButton("ȡ��");
    //�����Ǽ����м�����
    JPanel IdentityPannel = new JPanel();
    JPanel UsernamePannel = new JPanel();
    JPanel PasswordPannel = new JPanel();
    JPanel ButtonPannel = new JPanel();
    public LoginFrame(){
//        LoginFrame.setTitle("ҩ�����ϵͳ��¼����");
//        LoginFrame.setSize(800, 600);
//        LoginFrame.setLayout(new GridLayout(6, 1));
//        LoginFrame.setLocationRelativeTo(null);
//        LoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        LoginFrame.setVisible(true);
        IdentityCombox.addItem("����");
        IdentityCombox.addItem("ҽ��");
        IdentityCombox.addItem("��ʿ");
        IdentityCombox.addItem("�ֿ����Ա");

        this.setTitle("ҩ�����ϵͳ��¼����");
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
