package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Admin;
import Util.DBUtil;

public class AdminDAO {

    /**
     * @param admin 管理员表的一条记录，通过Ano索引更新
     * @return 是否成功更新
     */
    public static boolean update(Admin admin) {

        try (Connection connection = DBUtil.getConnection()) {
            // 创建 SQL 更新语句
            String sql = "UPDATE Admin SET Aname = ?, Asex = ?, Aage = ?, Apwd = ? WHERE Ano = ?";

            // 创建 PreparedStatement 对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            preparedStatement.setString(1, admin.getAname());
            preparedStatement.setBoolean(2, admin.getAsex());
            preparedStatement.setInt(3, admin.getAage());
            preparedStatement.setString(4, admin.getApwd());
            preparedStatement.setString(5, admin.getAno());

            // 执行更新操作
            int rowsAffected = preparedStatement.executeUpdate();
//                System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param name 管理员的姓名
     * @param pwd 管理员的密码
     * @return 是否成功更新
     */
    public static boolean updateApwdByAname(String name, String pwd) {
        try (Connection connection = DBUtil.getConnection()) {
            // 创建 SQL 更新语句
            String sql = "UPDATE Admin SET Apwd = ? WHERE Aname = ?";

            // 创建 PreparedStatement 对象
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            // 设置参数
            preparedStatement.setString(1, pwd);
            preparedStatement.setString(2, name);

            // 执行更新操作
            int rowsAffected = preparedStatement.executeUpdate();
                System.out.println("Rows affected: " + rowsAffected);
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param admin 管理员表的一条记录
     * @return 是否成功插入
     */
    public static boolean insert(Admin admin){
        try(Connection connection = DBUtil.getConnection()){
            String sql = "INSERT INTO Admin (Ano, Aname, Asex, Aage, Apwd) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getAno());
            preparedStatement.setString(2, admin.getAname());
            preparedStatement.setBoolean(3, admin.getAsex());
            preparedStatement.setInt(4, admin.getAage());
            preparedStatement.setString(5, admin.getApwd());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }catch (SQLException e){
            e.printStackTrace();;
        }
        return false;
    }
    public static Admin selectByAno(String Ano) {
        Admin admin = null;
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "SELECT * FROM Admin WHERE Ano = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Ano);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                admin = new Admin();
                admin.setAno(resultSet.getString("Ano"));
                admin.setAname(resultSet.getString("Aname"));
                admin.setAsex(resultSet.getBoolean("Asex"));
                admin.setAage(resultSet.getInt("Aage"));
                admin.setApwd(resultSet.getString("Apwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
    /**
     * @param Ano 管理员的编号
     * @return 是否成功删除
     */
    public static boolean deleteByAno(String Ano) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "DELETE FROM Admin WHERE Ano = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Ano);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Admin a = new Admin("0001","aa",true,30,"123");
        AdminDAO.insert(a);
        System.out.println(AdminDAO.selectByAno("0001"));
        System.out.println(AdminDAO.updateApwdByAname("aa", "123456"));
        System.out.println(AdminDAO.selectByAno("0001"));
        System.out.println(AdminDAO.deleteByAno("0001"));
    }
}