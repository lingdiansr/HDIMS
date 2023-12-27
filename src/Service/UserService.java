package Service;


import DAO.AdminDAO;
import DAO.DoctorDAO;
import DAO.NurseDAO;
import Entity.Admin;
import Entity.Doctor;
import Entity.Nurse;

public class UserService {
        //库房管理员登录匹配
        public boolean AdminRight(Admin a){
            //数据访问层的方法,获取管理员输入的姓名，和密码，数据库存在信息则返回true
            Admin admin = AdminDAO.selectByAno(a.getAno());
            if (admin!=null&&admin.getAno() !=null && a.getApwd().equals(admin.getApwd().trim())){
                return true;
            }else{
                return false;
            }

        }
//        护士登陆匹配
    public boolean NurseRight(Nurse n){
        //数据访问层的方法,获取护士输入的姓名，和密码，数据库存在信息则返回true
        Nurse nurse = NurseDAO.selectByNno(n.getNno());
        if (nurse!=null&&nurse.getNno() !=null && n.getNpwd().equals(nurse.getNpwd().trim())){
            return true;
        }else{
            return false;
        }

    }



    public boolean DoctorRight(Doctor d){
        //数据访问层的方法,获取护士输入的姓名，和密码，数据库存在信息则返回true
        Doctor doctor = DoctorDAO.selectByDno(d.getDno());
        if (doctor!=null&&doctor.getDno() !=null && d.getDpwd().equals(doctor.getDpwd().trim())){
            return true;
        }else{
            return false;
        }

    }
}

