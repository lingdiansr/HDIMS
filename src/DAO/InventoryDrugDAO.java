package DAO;

import Entity.InventoryDrug;
import Util.DBUtil;

import java.sql.*;

public class InventoryDrugDAO {

    public static boolean insertInventoryDrug(InventoryDrug inventoryDrug) {
        //插入库存信息
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DBUtil.getConnection();
            String query = "INSERT INTO InventoryDrug (PDno, PDbatch, PDnum,Sno,SAno,Stime) VALUES (?, ?, ?, ?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, inventoryDrug.PDno);
            preparedStatement.setDate(2, (Date) inventoryDrug.PDbatch);
            preparedStatement.setInt(3, inventoryDrug.PDnum);
            preparedStatement.setString(4, inventoryDrug.Sno);
            preparedStatement.setString(5, inventoryDrug.SAno);
            preparedStatement.setDate(6, (Date) inventoryDrug.Stime);
            int rowsAffected = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //根据库存药品编号更新药品库存数量
    //PDno药品编号,PDnum更新后的药品数量
    public static boolean updateInventoryDrug(String PDno, int PDnum) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            String query = "UPDATE Supplier SET PDnum = '" + PDnum + "'WHERE PDno = '" + PDno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //删除药品编号为PDno的药品库存信息
    public static boolean deleteInventoryDrug(String PDno) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DBUtil.getConnection();
            statement = connection.createStatement();
            String query = "DELETE FROM InventoryDrug WHERE PDno = '" + PDno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //查询整个库存药品的信息，返回InventoryDrug[]类型
    public static InventoryDrug[] getallInventoryDrug() {
        InventoryDrug[] inventoryDrugs = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 建立连接
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM InventoryDrug";
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();
            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();
                // 创建一个inventoryDrug数组
                inventoryDrugs = new InventoryDrug[rowCount];
                int index = 0;
                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    InventoryDrug inventoryDrug = new InventoryDrug();
                    inventoryDrug.PDno = resultSet.getString("PDno");
                    inventoryDrug.PDbatch = resultSet.getDate("PDbatch");
                    inventoryDrug.PDnum = resultSet.getInt("PDnum");
                    inventoryDrug.Sno = resultSet.getString("Sno");
                    inventoryDrug.SAno = resultSet.getString("SAno");
                    inventoryDrug.Stime = resultSet.getDate("Stime");
                    inventoryDrugs[index] = inventoryDrug;
                    index++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inventoryDrugs;

    }

    public static InventoryDrug[] fuzzySelectBy(String text) {
        InventoryDrug[] inventoryDrugs = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 建立连接
            connection = DBUtil.getConnection();
            String query = "SELECT * FROM InventoryDrug WHERE PDno LIKE N'%" + text + "%' OR PDbatch LIKE N'%" + text + "%' OR PDnum LIKE N'%" + text + "%' OR Sno LIKE N'%" + text + "%'OR SAno LIKE N'%" + text + "%'OR Stime LIKE N'%" + text + "%'";
            // 创建一个具有可滚动结果集的prepareStatement
            preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                // 创建一个Supplier数组
                inventoryDrugs = new InventoryDrug[rowCount];
                int index = 0;

                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    InventoryDrug inventoryDrug = new InventoryDrug();
                    inventoryDrug.PDno = resultSet.getString("PDno");
                    inventoryDrug.PDbatch = resultSet.getDate("PDbatch");
                    inventoryDrug.PDnum = resultSet.getInt("PDnum");
                    inventoryDrug.Sno = resultSet.getString("Sno");
                    inventoryDrug.SAno = resultSet.getString("SAno");
                    inventoryDrug.Stime = resultSet.getDate("Stime");
                    inventoryDrugs[index] = inventoryDrug;
                    index++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inventoryDrugs;
    }
}
