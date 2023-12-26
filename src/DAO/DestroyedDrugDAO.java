package DAO;

import Entity.DestroyedDrug;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class DestroyedDrugDAO {
    public static boolean insertDestroyedDrug(DestroyedDrug destroyedDrug) {
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();

            // 创建一个prepareStatement
            String query = "INSERT INTO DestroyedDrug (PDno, PDbatch, PDnum, Sno, SAno, Stime, DAno, Dtime) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, destroyedDrug.PDno);
            preparedStatement.setDate(2, new java.sql.Date(destroyedDrug.PDbatch.getTime()));
            preparedStatement.setInt(3, destroyedDrug.PDnum);
            preparedStatement.setString(4, destroyedDrug.Sno);
            preparedStatement.setString(5, destroyedDrug.SAno);
            preparedStatement.setDate(6, new java.sql.Date(destroyedDrug.Stime.getTime()));
            preparedStatement.setString(7, destroyedDrug.DAno);
            preparedStatement.setDate(8, new java.sql.Date(destroyedDrug.Dtime.getTime()));
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateDestroyedDrug(String PDno, Date PDbatch, int PDnum, String Sno, String SAno, Date Stime, String DAno, Date Dtime) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE DestroyedDrug SET PDbatch = '" + new java.sql.Date(PDbatch.getTime()) + "', PDnum = " + PDnum + ", Sno = '" + Sno + "', SAno = '" + SAno + "', Stime = '" + new java.sql.Date(Stime.getTime()) + "', DAno = '" + DAno + "', Dtime = '" + new java.sql.Date(Dtime.getTime()) + "' WHERE PDno = '" + PDno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteDestroyedDrug(String PDno) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM DestroyedDrug WHERE PDno = '" + PDno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DestroyedDrug[] getAllDestroyedDrugs() {
        DestroyedDrug[] destroyedDrugs = null;
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();
            String query = "SELECT * FROM DestroyedDrug";
            // 创建一个具有可滚动结果集的prepareStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                // 创建一个DestroyedDrug数组
                destroyedDrugs = new DestroyedDrug[rowCount];
                int index = 0;

                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    DestroyedDrug destroyedDrug = new DestroyedDrug();
                    destroyedDrug.PDno = resultSet.getString("PDno");
                    destroyedDrug.PDbatch = resultSet.getDate("PDbatch");
                    destroyedDrug.PDnum = resultSet.getInt("PDnum");
                    destroyedDrug.Sno = resultSet.getString("Sno");
                    destroyedDrug.SAno = resultSet.getString("SAno");
                    destroyedDrug.Stime = resultSet.getDate("Stime");
                    destroyedDrug.DAno = resultSet.getString("DAno");
                    destroyedDrug.Dtime = resultSet.getDate("Dtime");
                    destroyedDrugs[index] = destroyedDrug;
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
        return destroyedDrugs;
    }

    public static void main(String[] args) {
        DestroyedDrug d = new DestroyedDrug();
        d.PDno = "001";
        d.PDbatch = new Date();
        d.PDnum = 10;
        d.Sno = "S001";
        d.SAno = "SA001";
        d.Stime = new Date();
        d.DAno = "DA001";
        d.Dtime = new Date();

        System.out.println(insertDestroyedDrug(d));
        System.out.println(updateDestroyedDrug("001", new Date(), 20, "S002", "SA002", new Date(), "DA002", new Date()));
        System.out.println(deleteDestroyedDrug("001"));
        DestroyedDrug[] destroyedDrugs = getAllDestroyedDrugs();
        for (DestroyedDrug destroyedDrug : destroyedDrugs) {
            System.out.println(destroyedDrug.PDno + " " + destroyedDrug.PDbatch + " " + destroyedDrug.PDnum + " " + destroyedDrug.Sno + " " + destroyedDrug.SAno + " " + destroyedDrug.Stime + " " + destroyedDrug.DAno + " " + destroyedDrug.Dtime);
        }
    }
}