package DAO;

import Entity.Supplier;
import Util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SupplierDAO {
    public boolean insertSupplier(String Sno, String Sname, String Saddr, String Sphone) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "INSERT INTO Supplier (Sno, Sname, Saddr, Sphone) VALUES ('" + Sno + "', '" + Sname + "', '" + Saddr + "', '" + Sphone + "')";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateSupplier(String Sno, String Sname, String Saddr, String Sphone) {
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "UPDATE Supplier SET Sname = '" + Sname + "', Saddr = '" + Saddr + "', Sphone = '" + Sphone + "' WHERE Sno = '" + Sno + "'";
            int rowsAffected = statement.executeUpdate(query);
            statement.close();
            connection.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteSupplier(String Sno) {
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

    public Supplier[] getAllSuppliers() {
        Supplier[] suppliers = null;
        try {
            Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Supplier";
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();
            suppliers = new Supplier[rowCount];
            int index = 0;
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.Sno = resultSet.getString("Sno");
                supplier.Sname = resultSet.getString("Sname");
                supplier.Saddr = resultSet.getString("Saddr");
                supplier.Sphone = resultSet.getString("Sphone");
                suppliers[index] = supplier;
                index++;
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suppliers;
    }
}
