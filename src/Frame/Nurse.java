package Frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Nurse extends JFrame implements ActionListener{
//    查看药品库存列表
    JButton DruginvButton  =new JButton("查看药品库存列表");
//    查看未处理的处方
    JButton UnpropresButton =new JButton("查看未处理的处方");
//    查看已处理的处方
    JButton PropresButton =new JButton("查看已处理的处方");
//    处理处方
    JButton DealpresButton =new JButton("处理处方");

    JPanel ButtonPannel = new JPanel();

    public Nurse(){
        this.setTitle("护士界面");
        this.setSize(800, 600);
        this.setLayout(new GridLayout(6, 1));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        ButtonPannel.add(PropresButton);
        ButtonPannel.add(UnpropresButton);
        ButtonPannel.add(DruginvButton);
        ButtonPannel.add(DealpresButton);
        this.add(ButtonPannel);
    }

    @Override
//    功能未实现(配药功能是开一个窗口还是把未配药的处方表单隐藏?)
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==UnpropresButton){


    }
}
    public static void main(String[] s) {
        Nurse Nurseop = new Nurse();
        Nurseop.setVisible(true);
    }

}
