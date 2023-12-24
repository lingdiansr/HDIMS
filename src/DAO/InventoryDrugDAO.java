package DAO;

import Entity.InventoryDrug;
import Entity.Nurse;
import Entity.Supplier;
import Util.DBUtil;

import java.sql.*;

public class InventoryDrugDAO {

    public static boolean insertInventoryDrug(InventoryDrug inventoryDrug) {
        //插入库存信息
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
            String query = "INSERT INTO InventoryDrug (PDno, PDbatch, PDnum,Sno,SAno,Stime) VALUES (?, ?, ?, ?,?,?)";
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

    //根据库存药品编号更新药品库存数量
    //PDno药品编号,PDnum更新后的药品数量
    public static boolean updateInventoryDrug(String PDno, int PDnum) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE Supplier SET PDnum = '" + PDnum + "'WHERE PDno = '" + PDno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //删除药品编号为PDno的药品库存信息
    public static boolean deleteInventoryDrug(String PDno) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "DELETE FROM InventoryDrug WHERE PDno = '" + PDno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    //查询整个库存药品的信息，返回InventoryDrug[]类型
        public static InventoryDrug[] getallInventoryDrug() {
            InventoryDrug[] inventoryDrugs = null;
            try {
                // 建立连接
                Connection connection = DBUtil.getConnection();
                String query = "SELECT * FROM InventoryDrug";
                PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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

                        connection.close();
                }
                }
                } catch(SQLException e){
                    e.printStackTrace();
                }
            return inventoryDrugs;

        }
            public static void main (String[]args){
//                InventoryDrug s =new InventoryDrug("0001",new Date(2022/12/2),2,"shnaghai","",new Date(2022/12/28));
//                System.out.println(InventoryDrugDAO.insertInventoryDrug(s));
//                System.out.println(InventoryDrugDAO.getallInventoryDrug()[0]);
//                //System.out.print(inventoryDrugs[1]);
   }
}
