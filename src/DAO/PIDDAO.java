package DAO;
import Entity.PID;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PIDDAO {
    // 插入 PID 信息
    public static boolean insertPID(PID pid) {
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();

            // 创建预编译语句
            String query = "INSERT INTO PID (Pno, PDno, PDnum) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, pid.Pno);
            preparedStatement.setString(2, pid.PDno);
            preparedStatement.setShort(3, pid.PDnum);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static PID[] getAllPID() {
        PID[] pidArray = null;
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();
            String query = "SELECT * FROM PID";
            // 创建带有可滚动结果集的预编译语句
            PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集的行数
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            // 创建数组
            pidArray = new PID[rowCount];

            // 遍历结果集并填充数组
            int index = 0;
            while (resultSet.next()) {
                PID pid = new PID();
                pid.Pno = resultSet.getInt("Pno");
                pid.PDno = resultSet.getString("PDno");
                pid.PDnum = resultSet.getShort("PDnum");
                pidArray[index] = pid;
                index++;
            }

            // 关闭连接
            resultSet.close();
            preparedStatement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pidArray;
    }


    // 更新 PID 信息
    public static boolean updatePID(int Pno, String newPDno, short newPDnum) {
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();

            // 创建预编译语句
            String query = "UPDATE PID SET PDno = ?, PDnum = ? WHERE Pno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, newPDno);
            preparedStatement.setShort(2, newPDnum);
            preparedStatement.setInt(3, Pno);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 删除 PID 信息
    public static boolean deletePID(int Pno) {
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();

            // 创建预编译语句
            String query = "DELETE FROM PID WHERE Pno = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Pno);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 测试方法
    public static void main(String[] args) {
        PID pid = new PID();
        pid.Pno = 1;
        pid.PDno = "doctor1";
        pid.PDnum = 2;

        System.out.println(PIDDAO.insertPID(pid));
        PID[] pidArray = PIDDAO.getAllPID();
        for (PID p : pidArray) {
            System.out.println("Pno: " + p.Pno + ", PDno: " + p.PDno + ", PDnum: " + p.PDnum);
        }

        System.out.println(PIDDAO.updatePID(1, "newDoctor", (short) 3));
        pidArray = PIDDAO.getAllPID();
        for (PID p : pidArray) {
            System.out.println("Pno: " + p.Pno + ", PDno: " + p.PDno + ", PDnum: " + p.PDnum);
        }

        System.out.println(PIDDAO.deletePID(1));
        pidArray = PIDDAO.getAllPID();
        for (PID p : pidArray) {
            System.out.println("Pno: " + p.Pno + ", PDno: " + p.PDno + ", PDnum: " + p.PDnum);
        }
    }
}