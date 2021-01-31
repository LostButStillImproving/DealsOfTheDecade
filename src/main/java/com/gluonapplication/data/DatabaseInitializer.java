package com.gluonapplication.data;

import java.sql.*;

public class DatabaseInitializer {

    public static void createNewDatabase() {
        String url = "jdbc:sqlite:src/main/java/com/gluonapplication/data/highscores.db" ;
        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:src/main/java/com/gluonapplication/data/highscores.db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS Highscores (\n"
                + " Company text NOT NULL\n, " +
                " Days integer NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        DatabaseInitializer.createNewDatabase();
        DatabaseInitializer.createNewTable();
    }
}
