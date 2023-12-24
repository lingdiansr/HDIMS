package DAO;

import Entity.InventoryDrug;
import Entity.Nurse;
import Entity.Supplier;
import Util.DBUtil;

import java.sql.*;

public class InventoryDrugDAO {
    //查询整个库存药品的信息，返回InventoryDrug[]类型
    public static boolean insertInventoryDrug(InventoryDrug inventoryDrug) {
        try {
            // Establish a connection
            Connection connection = DBUtil.getConnection();
//            public String PDno;
//            public Date PDbatch;
//            public int PDnum;
//            public String Sno;
//            public String SAno;
//            public Date Stime;
            // Create a prepared statement
            String query = "INSERT INTO Inventory (PDno, PDbatch, PDnum,Sno,SAno,Stime) VALUES (?, ?, ?, ?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
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
        }
    }
        public static InventoryDrug[] selectInventoryDrug() {
            InventoryDrug[] inventoryDrugs = null;
            try {
                // 建立连接
                Connection connection = DBUtil.getConnection();

                // 创建一个具有可滚动结果集的语句
                Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // 执行查询语句
                ResultSet resultSet = statement.executeQuery("SELECT * FROM InventoryDrug");

                // 获取结果集中的行数
                if (resultSet.last()) {
                    int rowCount = resultSet.getRow();
                    resultSet.beforeFirst();
                    // 创建一个Supplier数组
                    inventoryDrugs = new InventoryDrug[rowCount];
                    int index = 0;
                    // 遍历结果集并填充数组
                    while (resultSet.next()) {
//                public int PDnum;
//                public String Sno;
//                public String SAno;
//                public Date Stime;
                        InventoryDrug inventoryDrug = new InventoryDrug();
                        inventoryDrug.PDno = resultSet.getString("PDno");
                        inventoryDrug.PDbatch = resultSet.getDate("PDbatch");
                        inventoryDrug.PDnum = resultSet.getInt("PDnum");
                        inventoryDrug.Sno = resultSet.getString("Sno");
                        inventoryDrug.SAno = resultSet.getString("SAno");
                        inventoryDrug.Stime = resultSet.getDate("Stime");
                        inventoryDrugs[index] = inventoryDrug;
                        index++;
                        // 关闭连接
                        resultSet.close();
                        statement.close();
                        connection.close();
                }
                }
                } catch(SQLException e){
                    e.printStackTrace();
                }
            return inventoryDrugs;

        }
            public static void main (String[]args){
                InventoryDrug s =new InventoryDrug("0001",,2,"shnaghai","",);
               // System.out.println(InventoryDrugDAO.insertInventoryDrug(s));
                System.out.println(InventoryDrugDAO.selectInventoryDrug()[0]);
                //System.out.print(inventoryDrugs[1]);
            }
        }
