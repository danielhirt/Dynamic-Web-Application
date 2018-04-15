package database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nanajjar ITIS 4166 Assignment 4 
 * Class to establish the database connection
 */
public class DbConnection {

    private static String dbURL = "jdbc:mysql://localhost:3306/";
    private static String schemaName = "daniel";
    private static String dbUsername = "root";
    private static String dbPassword = "8235";
    private static Connection connection;

    public DbConnection() {
        try {
            // load the DB driver
            Class.forName("com.mysql.jdbc.Driver");
            // get a DB connection
            connection = DriverManager.getConnection(dbURL + schemaName, dbUsername, dbPassword);
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Driver not found");
            connection = null;

        } catch (SQLException ex) {
            System.out.println("ERROR: Could not create DB connection");
        }

    }

    public static Statement getNewStatement() {
        Statement statement;
        try {
            if (connection == null) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, "ERROR: Problem with DB connection: ");
                System.out.println("ERROR: Problem with DB connection:");
                 if (connection == null) {
                     new DbConnection();
                     //return statement;
                 }
                 else 
                     return null;
            }
            /* Creating a statement object that we can use for running
             * SQL statements commands against the database.*/
            statement = connection.createStatement();
        } catch (SQLException e) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("ERROR: Could not create SQL statement object: " + e);
            statement = null;
        }
        return statement;
    }

    public static Connection getConnection() {
        if (connection == null) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, "ERROR: Problem with DB connection: ");
            System.out.println("ERROR: Problem with DB connection:");
            return null;
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
