import Entity.Drug;
import Entity.Supplier;

import java.lang.reflect.Field;

public class Main{
    public static Object[][] convertTo2DArray(Object[] objects) {
        if (objects.length == 0) {
            return new Object[0][0];
        }

        Class<?> clazz = objects[0].getClass();
        Field[] fields = clazz.getDeclaredFields();

        Object[][] result = new Object[objects.length][fields.length];
        for (int i = 0; i < objects.length; i++) {
            for (int j = 0; j < fields.length; j++) {
                try {
                    fields[j].setAccessible(true);
                    result[i][j] = fields[j].get(objects[i]);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example usage
        String[] columnNames = {"PDno", "PDname", "PDlife", "PDnum", "选择"};
        System.out.println(columnNames.length);
    }
}