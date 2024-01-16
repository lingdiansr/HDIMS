package DAO;

import Entity.Supplier;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupplierDAO {
    public static boolean insertSupplier(Supplier supplier) {
        try {
            // Establish a connection
            Connection connection = DBUtil.getConnection();

            // Create a prepared statement
            String query = "INSERT INTO Supplier (Sno, Sname, Saddr, Sphone) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, supplier.Sno);
            preparedStatement.setString(2, supplier.Sname);
            preparedStatement.setString(3, supplier.Saddr);
            preparedStatement.setString(4, supplier.Sphone);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateSupplier(Supplier s) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE Supplier SET Sname = '" + s.getSname() + "', Saddr = '" + s.getSaddr() + "', Sphone = '" + s.getSphone() + "' WHERE Sno = '" + s.getSno() + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteSupplier(String Sno) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM Supplier WHERE Sno = '" + Sno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Supplier[] selectAllSuppliers() {
        Supplier[] suppliers = null;
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();
            String query = "SELECT * FROM Supplier";
            // 创建一个具有可滚动结果集的prepareStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                // 创建一个Supplier数组
                suppliers = new Supplier[rowCount];
                int index = 0;

                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    Supplier supplier = new Supplier();
                    supplier.Sno = resultSet.getString("Sno");
                    supplier.Sname = resultSet.getString("Sname");
                    supplier.Saddr = resultSet.getString("Saddr");
                    supplier.Sphone = resultSet.getString("Sphone");
                    suppliers[index] = supplier;
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
        return suppliers;
    }

    public static void main(String[] args) {
        Supplier s = new Supplier("0001", "huadun", "shnaghai", "88888");
        Supplier s2 = new Supplier("0002", "hhhh", "shnaghai", "88888");
        System.out.println(SupplierDAO.insertSupplier(s));
        System.out.println(SupplierDAO.insertSupplier(s2));

//        System.out.println(SupplierDAO.getAllSuppliers()[0]);
//        System.out.println(SupplierDAO.updateSupplier("0001","huadun","jiangsu","8888"));
//        System.out.println(SupplierDAO.getAllSuppliers());
//        System.out.println(SupplierDAO.deleteSupplier("0001"));
    }

    public static Supplier[] fuzzySelectBy(String text) {
        Supplier[] suppliers = null;
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();
            String query = "SELECT * FROM Supplier WHERE Sno LIKE N'%" + text + "%' OR Sname LIKE N'%" + text + "%' OR Saddr LIKE N'%" + text + "%' OR Sphone LIKE N'%" + text + "%'";
            // 创建一个具有可滚动结果集的prepareStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                // 创建一个Supplier数组
                suppliers = new Supplier[rowCount];
                int index = 0;

                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    Supplier supplier = new Supplier();
                    supplier.Sno = resultSet.getString("Sno");
                    supplier.Sname = resultSet.getString("Sname");
                    supplier.Saddr = resultSet.getString("Saddr");
                    supplier.Sphone = resultSet.getString("Sphone");
                    suppliers[index] = supplier;
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
        return suppliers;
    }
}
