package Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




public class DBUtil {

    public static Connection getConnection() {
        //连接SqlServer数据库
//        String username = "LDSR";
//        String password = "admin123ldsr";
//        String url="jdbc:sqlserver://localhost:1433;DatabaseName=HDIMS";
//        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        //连接MySql数据库
//        String url="jdbc:mysql://;
//        String driver="com.mysql.jdbc.Driver";

        // for JDK11
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=HDIMS;"
                        + "user=LDSR;"
                        + "password=admin123ldsr;"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";
        try {

            return DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connection con = DBUtil.getConnection();
        assert con != null;
        System.out.println(con);
    }

}