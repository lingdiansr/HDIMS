package Service;


import DAO.AdminDAO;
import Entity.Admin;

public class UserService {

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

    }

