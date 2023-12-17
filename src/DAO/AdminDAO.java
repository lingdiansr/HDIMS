package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Entity.Admin;
import Util.DBUtil;
public class AdminDAO {

    public static boolean update (Admin admin){

        try (Connection connection = DBUtil.getConnection()) {
            // 创建 SQL 更新语句
            String sql = "UPDATE Admin SET Aname = ?, Asex = ?, Aage = ?, Apwd = ? WHERE Ano = ?";

            // 创建 PreparedStatement 对象
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // 设置参数
                preparedStatement.setString(1, admin.getAname());
                preparedStatement.setBoolean(2, admin.getAsex());
                preparedStatement.setInt(3, admin.getAage());
                preparedStatement.setString(4, admin.getApwd());
                preparedStatement.setString(5, admin.getAno());

                // 执行更新操作
                int rowsAffected = preparedStatement.executeUpdate();
//                System.out.println("Rows affected: " + rowsAffected);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
