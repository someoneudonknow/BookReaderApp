package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private final String URL = "jdbc:mysql://localhost:3306/project1";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";
    
    public DB() {
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection c) {
        try {
            if(c!=null){
                c.close();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
