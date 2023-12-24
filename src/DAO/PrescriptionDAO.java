package DAO;
import java.util.Date;

import Entity.Prescription;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PrescriptionDAO {

    // 插入处方信息
    public static boolean insertPrescription(Prescription prescription) {
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();

            // 创建预编译语句
            String query = "INSERT INTO Prescription (Pid, Dno, Ptime, Nno, Htime, Pstate) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, prescription.Pid);
            preparedStatement.setString(2, prescription.Dno);
            preparedStatement.setDate(3, new java.sql.Date(prescription.Ptime.getTime()));
            preparedStatement.setString(4, prescription.Nno);
            preparedStatement.setDate(5, new java.sql.Date(prescription.Htime.getTime()));
            preparedStatement.setBoolean(6, prescription.Pstate);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 更新处方信息
    public static boolean updatePrescription(int Pno, String Pid, String Dno, Date Ptime, String Nno, Date Htime, boolean Pstate) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE Prescription SET Pid = '" + Pid + "', Dno = '" + Dno + "', Ptime = '" + new java.sql.Date(Ptime.getTime()) + "', Nno = '" + Nno + "', Htime = '" + new java.sql.Date(Htime.getTime()) + "', Pstate = '" + Pstate + "' WHERE Pno = " + Pno;
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除处方信息
    public static boolean deletePrescription(int Pno) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Prescription WHERE Pno = " + Pno;
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 获取所有处方信息
    public static Prescription[] getAllPrescriptions() {
        Prescription[] prescriptions = null;
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();
            String query = "SELECT * FROM Prescription";
            // 创建带有可滚动结果集的预编译语句
            PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                // 创建处方数组
                prescriptions = new Prescription[rowCount];
                int index = 0;

                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    Prescription prescription = new Prescription();
                    prescription.Pno = resultSet.getInt("Pno");
                    prescription.Pid = resultSet.getString("Pid");
                    prescription.Dno = resultSet.getString("Dno");
                    prescription.Ptime = resultSet.getDate("Ptime");
                    prescription.Nno = resultSet.getString("Nno");
                    prescription.Htime = resultSet.getDate("Htime");
                    prescription.Pstate = resultSet.getBoolean("Pstate");
                    prescriptions[index] = prescription;
                    index++;
                }
            }

            // 关闭连接
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prescriptions;
    }

    // 测试方法
    public static void main(String[] args) {
        Prescription p = new Prescription();
        p.Pno = 1;
        p.Pid = "patient1";
        p.Dno = "doctor1";
        p.Ptime = new Date();
        p.Nno = "nurse1";
        p.Htime = new Date();
        p.Pstate = true;

        System.out.println(PrescriptionDAO.insertPrescription(p));
        System.out.println(PrescriptionDAO.getAllPrescriptions()[0]);
//        System.out.println(PrescriptionDAO.updatePrescription(1, "patient2", "doctor2", new Date(), "nurse2", new Date(), false));
//        System.out.println(PrescriptionDAO.deletePrescription(1));
    }
}