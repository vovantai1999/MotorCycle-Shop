package model.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionMySQL implements IConnectionMySQL {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/case_md3?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "19101999";

    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
