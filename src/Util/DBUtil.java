package Util;
import java.lang.reflect.Field;
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
                "jdbc:sqlserver://lingdianshiren.xyz:1433;"
                        + "database=HDIMS;"
                        + "user=HDIMS_admin;"
                        + "password=hdims123;"
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

    public static Object[][] convertTo2DArray(Object[] objects) {
        if (objects.length == 0) {
            return new Object[0][0];
        }

        Class<?> clazz = objects[0].getClass();
        Field[] fields = clazz.getDeclaredFields();

        // 获取父类字段
        Class<?> superClass = clazz.getSuperclass();
        Field[] superFields = superClass.getDeclaredFields();

        Object[][] result = new Object[objects.length][fields.length + superFields.length];
        for (int i = 0; i < objects.length; i++) {
            int fieldIndex = 0;
            for (Field field : superFields) {
                try {
                    field.setAccessible(true);
                    result[i][fieldIndex] = field.get(objects[i]);
                    fieldIndex++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    result[i][fieldIndex] = field.get(objects[i]);
                    fieldIndex++;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connection con = DBUtil.getConnection();
        assert con != null;
        System.out.println(con);
    }

}
