package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.Doctor;
import Util.DBUtil;
public class DoctorDAO {
    public static boolean insert(Doctor doctor) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "INSERT INTO Doctor (Dno, Dname, Dsex, Dage, Dpwd) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, doctor.getDno());
            preparedStatement.setString(2, doctor.getDname());
            preparedStatement.setBoolean(3, doctor.isDsex());
            preparedStatement.setInt(4, doctor.getDage());
            preparedStatement.setString(5, doctor.getDpwd());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Doctor selectByDno(String Dno) {
        Doctor doctor = null;
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "SELECT * FROM Doctor WHERE Dno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Dno);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setDno(resultSet.getString("Dno"));
                doctor.setDname(resultSet.getString("Dname"));
                doctor.setDsex(resultSet.getBoolean("Dsex"));
                doctor.setDage(resultSet.getInt("Dage"));
                doctor.setDpwd(resultSet.getString("Dpwd"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public static boolean update(Doctor doctor) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "UPDATE Doctor SET Dname = ?, Dsex = ?, Dage = ?, Dpwd = ? WHERE Dno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, doctor.getDname());
            preparedStatement.setBoolean(2, doctor.isDsex());
            preparedStatement.setInt(3, doctor.getDage());
            preparedStatement.setString(4, doctor.getDpwd());
            preparedStatement.setString(5, doctor.getDno());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteByDno(String Dno) {
        try (Connection connection = DBUtil.getConnection()) {
            String sql = "DELETE FROM Doctor WHERE Dno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Dno);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Doctor d = new Doctor("0001","bb",false,80,"555");
        System.out.println(insert(d));
        System.out.println(selectByDno("0001"));
        d.setDage(66);;
        System.out.println(update(d));
        System.out.println(selectByDno("0001"));
        System.out.println(deleteByDno("0001"));
    }
}
