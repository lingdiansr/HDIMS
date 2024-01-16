package Service;

import Entity.Admin;
import Entity.Doctor;
import Entity.Nurse;
import UI.LoginUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginListener implements ActionListener {
    private LoginUI login;

    public LoginListener(LoginUI login) {
        this.login = login;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String id = login.UsernameField.getText();
        String password = new String(login.PasswordField.getPassword());

        if (e.getSource() == login.SureButton) {
            if (id.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(login, "请正确输入用户名和密码", "错误", JOptionPane.ERROR_MESSAGE);
            } else {
                UserService us = new UserService();
                Nurse nurse = null;
                Doctor doctor = null;
                if (!login.Identitydoctor.isSelected() && !login.Identitynurse.isSelected() && !login.Identityadmin.isSelected()) {
                    JOptionPane.showMessageDialog(login, "请选择身份", "错误", JOptionPane.ERROR_MESSAGE);
                } else if (login.Identitydoctor.isSelected()) {
                    System.out.println("Selected Option 1");
                    doctor = new Doctor();
                    doctor.setDno(id);
                    doctor.setDpwd(password);
                    if (us.DoctorRight(doctor)) {
                        login.dispose();
                        new UI.Doctor(id);
                    }
                } else if (login.Identitynurse.isSelected()) {
                    System.out.println("Selected Option 2");
                    nurse = new Nurse();
                    nurse.setNno(id);
                    nurse.setNpwd(password);
                    if (us.NurseRight(nurse)) {
                        login.dispose();
                        new UI.Nurse(id);
                    }
                } else if (login.Identityadmin.isSelected()) {
                    System.out.println("Selected Option 3");
                    Admin admin = new Admin();
                    admin.setAno(id);
                    admin.setApwd(password);
                    if (us.AdminRight(admin)) {
                        login.dispose();
                        new UI.Warehousekeeper(id);
                    } else {
                        JOptionPane.showMessageDialog(login, "工号或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else if (e.getSource() == login.CancelButton) {
            System.exit(0);
        }
    }

}
