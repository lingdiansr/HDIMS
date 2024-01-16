package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Nurse;
import Util.DBUtil;

public class NurseDAO {

    public static boolean update(Nurse nurse) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "UPDATE Nurse SET Nname = ?, Nsex = ?, Nage = ?, Npwd = ? WHERE Nno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nurse.getNname());
            preparedStatement.setBoolean(2, nurse.isNsex());
            preparedStatement.setInt(3, nurse.getNage());
            preparedStatement.setString(4, nurse.getNpwd());
            preparedStatement.setString(5, nurse.getNno());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateNpwdByNname(String name, String pwd) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "UPDATE Nurse SET Npwd = ? WHERE Nname = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, pwd);
            preparedStatement.setString(2, name);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean insert(Nurse nurse) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "INSERT INTO Nurse (Nno, Nname, Nsex, Nage, Npwd) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nurse.getNno());
            preparedStatement.setString(2, nurse.getNname());
            preparedStatement.setBoolean(3, nurse.isNsex());
            preparedStatement.setInt(4, nurse.getNage());
            preparedStatement.setString(5, nurse.getNpwd());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Nurse selectByNno(String Nno) {
        Nurse nurse = null;
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "SELECT * FROM Nurse WHERE Nno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Nno);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nurse = new Nurse();
                nurse.setNno(resultSet.getString("Nno"));
                nurse.setNname(resultSet.getString("Nname"));
                nurse.setNsex(resultSet.getBoolean("Nsex"));
                nurse.setNage(resultSet.getInt("Nage"));
                nurse.setNpwd(resultSet.getString("Npwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nurse;
    }

    public static boolean deleteByNno(String Nno) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "DELETE FROM Nurse WHERE Nno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Nno);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Nurse nurse = new Nurse();
        nurse.setNno("0001");
        nurse.setNname("NurseName");
        nurse.setNsex(true);
        nurse.setNage(25);
        nurse.setNpwd("password");
        NurseDAO.insert(nurse);
        System.out.println(NurseDAO.selectByNno("0001"));
        System.out.println(NurseDAO.updateNpwdByNname("NurseName", "newpassword"));
        System.out.println(NurseDAO.selectByNno("0001"));
        System.out.println(NurseDAO.deleteByNno("0001"));
    }
}