/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import database.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Daniel Christopher Hirt
 * ITIS 4166 Assignment 4
 * Item Database
 */

public class ItemDB {
    
    /**
     * Creates the item table in the database
     */
    public static void createItemTable() {
        
        Statement statement = DbConnection.getNewStatement();
        
        try {
            statement.execute("CREATE TABLE item("
                + "imageFilePath VARCHAR(50) DEFAULT NULL, itemID VARCHAR(50) NOT NULL DEFAULT '0',"
                + "userID VARCHAR(50) DEFAULT NULL, name VARCHAR(50) DEFAULT NULL,"
                + "description VARCHAR(255) DEFAULT NULL, PRIMARY KEY ('itemID'))");
            System.out.println("Created a new table: " + "ITEM");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState())) {
                // Exception thrown if table is already there
            } else {
                // In the case of an unexpected exception
                System.out.println("ERROR: Could not create ITEM table: " + se);
            }
        }
        
    }

    /**
     * Adds an item to the database
     * @param itemID
     * @param name
     * @param imageFilePath
     * @param rating
     * @param watched
     * @param description
     * @return
     */
    public static Item addItem(String itemID, String name, String imageFilePath,  String rating,  String watched, String description){
       
       Connection connection = DbConnection.getConnection();
       PreparedStatement ps;
       // Insert the new row into the table
       try {

           ps = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?,?,?)");
           ps.setString(1, itemID);
           ps.setString(2, name);
           ps.setString(3, imageFilePath);
           ps.setString(4, rating);
           ps.setString(5, watched);
           ps.setString(6, description);
           
           ps.executeUpdate();
       } catch (SQLException se) {
           if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into ITEM; dup primary key: " + itemID);
            } else {
                System.out.println("ERROR: Could not add row to ITEM table: " + itemID + " " + se.getCause());
            }
           return null;
       } catch (Exception e) {
           System.out.println("ERROR: Could not add row to ITEM table: " + itemID);
           return null;
       }
       System.out.println("Added item to ITEM table: " + itemID);
       
       return new Item(itemID,  name,  imageFilePath,   rating,   watched,  description);
   }
   
    /**
     * Adds an item to the database
     * @param item
     * @return
     */
    public static Item addItem(Item item) {
       
       Connection connection = DbConnection.getConnection();
       PreparedStatement ps;
       // Insert the new row into the table
       
       try {
           ps = connection.prepareStatement("INSERT INTO item VALUES(?,?,?,?,?,?)");
           ps.setString(1, item.getItemID());
           ps.setString(2, item.getName());
           ps.setString(3, item.getImageFilePath());
           ps.setString(4, item.getRating());
           ps.setString(5, item.getWatched());
           ps.setString(6, item.getDescription());
       } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into ITEM; dup primary key: " + item.getItemID());
            } else {
                System.out.println("ERROR: Could not add row to ITEM table: " + item.getItemID() + " " + se.getCause());
            }
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to ITEM table: " + item.getItemID());
            return null;
        }
        System.out.println("Added item to ITEM table: " + item.getItemID());

        // return the item object
        return item;
    
   }
   
    /**
     * Returns an item from the database
     * @param pcode
     * @return
     */
    public static Item getItem(String pcode) {

        Item item = new Item();
        item.setItemID(pcode);

        String query = "SELECT imageFilePath, itemID, watched, rating, name, description, "
                + " FROM item WHERE item.itemID =  " + pcode;
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        try {
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                
                item.setImageFilePath(resultSet.getString("imageFilePath"));
                item.setItemID(resultSet.getString("itemID"));
                item.setRating(resultSet.getString("rating"));
                item.setWatched(resultSet.getString("watched"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;

    }
    
    /**
     * Retrieves all items from the Item ArrayList
     * @return
     */
    public static ArrayList<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;
        
        String itemID = "";
        String name = "";
        String imageFilePath ="";
        String rating = "";
        String watched = "";
        String description ="";
        
        try {
            
            resultSet = statement.executeQuery(
                "SELECT itemID, name, imageFilePath,  rating,  watched, description FROM item ORDER BY itemID");
            while(resultSet.next()) {
               
                itemID = resultSet.getString("itemID");
                name = resultSet.getString("name");
                imageFilePath = resultSet.getString("imageFilePath");
                rating = resultSet.getString("rating");
                watched = resultSet.getString("watched");
                description = resultSet.getString("description");
                
                Item item = new Item(itemID,  name,  imageFilePath,   rating,   watched,  description);
                items.add(item);
                //System.out.println("Found item in Item Table: " + itemID);
                
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "ItemDB.getAllItems()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }
        
        return items;
        
    }
    
    /**
     * Updates an item in the database
     * @param item
     * @return
     */
    public static boolean updateItem(Item item){
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        
        //System.out.println("Obj: " + item.toString());
          try {
           String sql = "UPDATE item SET name = ?, rating = ?, watched=?, description=? WHERE itemID = ?";
           ps = connection.prepareStatement(sql);
           
           ps.setString(1, item.getName());
           ps.setString(2, item.getRating());
           ps.setString(3, item.getWatched());
           ps.setString(4, item.getDescription());
           ps.setString(5, item.getItemID());
           
           ps.executeUpdate();
           
           //System.out.println("Passed Update ID: " + item.getItemID());
       } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not update record into ITEM; dup primary key: " + item.getItemID());
            } else {
                System.out.println("ERROR: Could not update row to ITEM table: " + item.getItemID() + " Cause: " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not update row to ITEM table: " + item.getItemID() + " Cause: " + e.getCause());
            return false;
        }
          
          return true;
    }
    
    /**
     * Helper method to check if the item exists
     * @param itemID
     * @return
     */
    public static boolean exists(String itemID) {
        Item p = getItem(itemID);
        if (p != null) {
            return true;
        } else {
            return false;
                }
    
    }
 
}
