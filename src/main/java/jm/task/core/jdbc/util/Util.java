package jm.task.core.jdbc.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String log = "postgres";
    private static final String pass = "123456789";


    public static Connection getConnectionDataBase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, log, pass);
           if (connection != null) {
                System.out.println("База данных подключена!");
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }

        return connection;
   }




}

    // реализуйте настройку соеденения с БД
