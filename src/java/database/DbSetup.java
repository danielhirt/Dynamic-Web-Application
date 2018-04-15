package database;

import java.sql.SQLException;
import model.ItemDB;
import model.User;
import model.UserDB;



/**
 *
 * @author Daniel Christopher Hirt
 * ITIS 4166 Assignment 4
 * DbSetup/main method
 * 
 */

public class DbSetup {

    // running this file creates the PRODUCT and USER tables
    // and adds a test entry in each table
    public static void main(String[] args) throws SQLException {

        ItemDB.createItemTable();
            
        //Item testItem = new Item("imageFilePath", "itemID", "userID",
        //         "name", "description");
        //ItemDB.addItem(testItem);

        UserDB uDB = new UserDB();
        uDB.createUserTable();

        User testUser = new User("1", "firstName", "lastName", "email",
                "address1", "address2", "city", "state",
                "zipcode", "country");
        uDB.addUser(testUser);

    }

}
