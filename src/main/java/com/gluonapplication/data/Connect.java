package com.gluonapplication.data;


import java.sql.*;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Connect {


     private static Connection connect() {
         String url = "jdbc:sqlite:src/main/java/com/gluonapplication/data/highscores.db";
         Connection conn = null;
         try {
             conn = DriverManager.getConnection(url);
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }

         return conn;
     }

     public static ResultWraper selectTopTen(){
         String sql = "SELECT * FROM Highscores ORDER BY Days DESC LIMIT 10";
         ArrayList<String> resultsCompany = new ArrayList<>();
         ArrayList<Integer> resultsDays = new ArrayList<>();

         try (Connection conn = connect();
              Statement stmt  = conn.createStatement();
              ResultSet rs    = stmt.executeQuery(sql)){
              while (rs.next()) {
                  resultsCompany.add(rs.getString("Company"));
                  resultsDays.add(rs.getInt("Days"));
              }
         } catch (SQLException e) {
              System.out.println(e.getMessage());
         }

         return new ResultWraper(resultsCompany, resultsDays);
        }

        public static void insert(String company, int days) {
            String sql = "INSERT INTO Highscores(Company, Days) VALUES(?, ?)";
            try (Connection conn = connect();
                 PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setString(1, company);
                preparedStatement.setInt(2, days);
                preparedStatement.executeUpdate();
                System.out.println("here");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
     }

    public static void main(String[] args) {

        ResultWraper resultWraper = Connect.selectTopTen();

        var companies = resultWraper.getCompanies();
        var days = resultWraper.getDays();

        for (int i = 0; i < companies.size() ; i++) {
            System.out.println(companies.get(i) + "   " + days.get(i));

        }
    }
}

