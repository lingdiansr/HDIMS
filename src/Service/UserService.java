package Service;


import DAO.AdminDAO;
import DAO.DoctorDAO;
import DAO.NurseDAO;
import Entity.Admin;
import Entity.Doctor;
import Entity.Nurse;

public class UserService {
        //库房管理员登录匹配
        public boolean AdminRight(String Aname,String Apwd){
            //数据访问层的方法,获取管理员输入的姓名，和密码，数据库存在信息则返回true
            AdminDAO adminDAO = new AdminDAO();
            Admin admin = adminDAO.selectByAno(Aname);
            if (admin.getAname() !=null && Apwd.equals(admin.getApwd().trim())){
                return true;
            }else{
                return false;
            }

        }
//        护士登陆匹配
    public boolean NurseRight(String Nname,String Npwd){
        //数据访问层的方法,获取护士输入的姓名，和密码，数据库存在信息则返回true
        NurseDAO nDAO = new NurseDAO();
        Nurse nurse = NurseDAO.selectByNno(Nname);
        if (nurse.getNname() !=null && Npwd.equals(nurse.getNpwd().trim())){
            return true;
        }else{
            return false;
        }

    }


//     医生登录匹配
    public boolean DoctorRight(String Dname,String Dpwd){
        //数据访问层的方法,获取护士输入的姓名，和密码，数据库存在信息则返回true
        DoctorDAO DDAO = new DoctorDAO();
        Doctor doctor = DoctorDAO.selectByDno(Dname);
        if (doctor.getDname() !=null && Dpwd.equals(doctor.getDpwd().trim())){
            return true;
        }else{
            return false;
        }

    }
}

