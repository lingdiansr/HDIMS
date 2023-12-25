package Service;


import DAO.AdminDAO;
import Entity.Admin;

public class UserService {

        public boolean AdminRight(Admin a){
            //数据访问层的方法,获取管理员输入的姓名，和密码，数据库存在信息则返回true
            Admin admin = AdminDAO.selectByAno(a.getAno());
            if (admin.getAname() !=null && a.getApwd().equals(admin.getApwd().trim())){
                return true;
            }else{
                return false;
            }
        }
    }

