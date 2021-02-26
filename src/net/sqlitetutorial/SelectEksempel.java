package net.sqlitetutorial;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author sqlitetutorial.net
 */
public class SelectEksempel {

    /**
     * Connect to the test.db database
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Kogebog_db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * select all rows in the warehouses table
     */
    public void selectAll(){
        String sql = "SELECT * FROM opskrifter";

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getInt("id") +  ". " +
                        rs.getString("navn") + "\n" +
                        rs.getString("opskrift") + "\n" +
                        rs.getString("ingredienser") + "\n\n"
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * select all rows in the warehouses table
     */
    public void selectOne(){
        String brugerInput;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Indtast et søgeord: ");
        brugerInput = scanner.nextLine();

        String sql = "SELECT * FROM opskrifter WHERE navn ='" + brugerInput + "'";
        // Eksempel på LIKE (slet kommentartegnet og søg på la --> Du vil nu finde både Gulash og Lasagne, fordi de indeholde "la")
        // sql = "SELECT * FROM opskrifter WHERE navn LIKE '%" + brugerInput + "%'";

        System.out.println(sql);

        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("navn"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        net.sqlitetutorial.SelectEksempel app = new net.sqlitetutorial.SelectEksempel();
        app.selectOne();
    }

}