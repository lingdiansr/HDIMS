package DAO;

import Entity.Drug;
import Entity.DrugDoctor;
import Entity.Supplier;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class DrugDAO {
        public static boolean insertDrug(Drug drug) {
//            public String PDno;
//            public String PDname;
//            public int PDlife;
            try {
                // Establish a connection
                Connection connection = DBUtil.getConnection();
                // Create a prepared statement
                String query = "INSERT INTO Drug (PDno, PDname,PDlife) VALUES (?,  ?, ?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, drug.PDno);
                preparedStatement.setString(2, drug.PDname);
                preparedStatement.setInt(3,drug.PDlife);
                int rowsAffected = preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                return rowsAffected > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public static boolean updateDrug(String PDno,String PDname,int PDlife) {
            try {
                Connection connection = DBUtil.getConnection();
                Statement statement = connection.createStatement();
                String query = "UPDATE Drug SET  PDname = '" + PDname + "', PDlife = '" + PDlife + "' WHERE PDno = '" + PDno + "'";
                int rowsAffected = statement.executeUpdate(query);
                statement.close();
                connection.close();
                return rowsAffected > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        public static boolean deleteDrug(String PDno) {
            try {
                Connection connection = DBUtil.getConnection();
                Statement statement = connection.createStatement();
                String query = "DELETE FROM Drug WHERE PDno = '" + PDno + "'";
                int rowsAffected = statement.executeUpdate(query);
                statement.close();
                connection.close();
                return rowsAffected > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public static Drug[] getAllDrug() {
            Drug[] drugs = null;
            try {
                // 建立连接
                Connection connection = DBUtil.getConnection();
                String query = "SELECT * FROM Drug";
                // 创建一个具有可滚动结果集的prepareStatement
                PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                // 执行查询
                ResultSet resultSet = preparedStatement.executeQuery();

                // 获取结果集中的行数
                if (resultSet.last()) {
                    int rowCount = resultSet.getRow();
                    resultSet.beforeFirst();

                    // 创建一个Supplier数组
                    drugs = new Drug[rowCount];
                    int index = 0;

                    // 遍历结果集并填充数组
                    while (resultSet.next()) {
                        Drug drug = new Drug();
                        drug.PDname = resultSet.getString("PDname");
                        drug.PDno = resultSet.getString("PDno");
                        drug.PDlife = resultSet.getInt("PDlife");
                        drugs[index] = drug;
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
            return drugs;
        }

        public static void main(String[] args) {
//            Drug s =new Drug("0001","huadun",12);
//           Drug s2 =new Drug("0002","hhhh",13);
//            System.out.println(DAO.DrugDAO.insertDrug(s));
//            System.out.println(DAO.DrugDAO.insertDrug(s2));
//          System.out.println(DAO.DrugDAO.getAllDrug()[1]);
//        System.out.println(DrugDAO.updateDrug("0001","huadun",14));
//        System.out.println(DrugDAO.getAllDrug());

        }

    public static Drug[] fuzzySelectDrugBy(String text) {
        Drug[] drugs = null;
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();
            String query = "SELECT * FROM Drug WHERE PDno LIKE N'%"+text+"%' OR PDname LIKE N'%"+text+"'";
            // 创建一个具有可滚动结果集的prepareStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                // 创建一个Drug数组
                drugs = new Drug[rowCount];
                int index = 0;

                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    Drug drug = new Drug();
                    drug.PDno = resultSet.getString("PDno");
                    drug.PDname = resultSet.getString("PDname");
                    drug.PDlife = resultSet.getInt("PDlife");
                    drugs[index] = drug;
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
        return drugs;
    }
    public static DrugDoctor[] fuzzySelectBy(String text) {
        DrugDoctor[] drugs = null;
        try {
            // 建立连接
            Connection connection = DBUtil.getConnection();
            String query = "SELECT * FROM DrugDoctorView WHERE PDno LIKE N'%"+text+"%' OR PDname LIKE N'%"+text+"'";
            // 创建一个具有可滚动结果集的prepareStatement
            PreparedStatement preparedStatement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();

            // 获取结果集中的行数
            if (resultSet.last()) {
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                // 创建一个Drug数组
                drugs = new DrugDoctor[rowCount];
                int index = 0;

                // 遍历结果集并填充数组
                while (resultSet.next()) {
                    DrugDoctor drug = new DrugDoctor();
                    drug.PDno = resultSet.getString("PDno");
                    drug.PDname = resultSet.getString("PDname");
                    drug.PDlife = resultSet.getInt("PDlife");
                    drug.PDnum = resultSet.getInt("PDnum");
                    drugs[index] = drug;
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
        return drugs;
    }
}
