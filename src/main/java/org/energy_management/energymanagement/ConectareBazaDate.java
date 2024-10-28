package org.energy_management.energymanagement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Clasa separata pentru conectarea bazei de date
public class ConectareBazaDate {
    private static final String URL = "jdbc:mysql://localhost:3306/energymanagement";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
