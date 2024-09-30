package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection() {
        // Connection string for SQL Server Express with Windows Authentication
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS01;databaseName=db;integratedSecurity=true;";

        try {
            // Load the SQL Server JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            
            // Establish the connection
            databaseLink = DriverManager.getConnection(url);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return databaseLink;
    }
}
