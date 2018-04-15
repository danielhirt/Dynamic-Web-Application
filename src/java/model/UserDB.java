package model;

import database.DbConnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author Daniel Christopher Hirt 
 * ITIS 4166 Assignment 4 
 * User Database
 */
public class UserDB {

    /**
     * Creates the user table
     */
    public static void createUserTable() {

        Statement statement = DbConnection.getNewStatement();

        try {
            statement.execute("CREATE TABLE users("
                    + "userID VARCHAR(50) NOT NULL AUTO_INCREMENT,lastName VARCHAR(50) DEFAULT NULL,"
                    + "firstName VARCHAR(50) DEFAULT NULL, emailAddr VARCHAR(50) DEFAULT NULL,"
                    + "address1 VARCHAR(50) DEFAULT NULL, address2 VARCHAR(50) NOT NULL,"
                    + "city VARCHAR(50) DEFAULT NOT NULL, state VARCHAR(50) DEFAULT NULL, zipcode VARCHAR(50) DEFAULT NULL,"
                    + "country VARCHAR(50) DEFUALT NULL,"
                    + "PRIMARY KEY ('userID'))");

            System.out.println("Created a new table: " + "USER");
        } catch (SQLException se) {
            if (se.getErrorCode() == 30000 && "X0Y32".equals(se.getSQLState())) {
                // we got the expected exception when the table is already there
            } else {
                // if the error code or SQLState is different, we have an unexpected exception
                System.out.println("ERROR: Could not create USER table: " + se);
            }
        }
    }

    /**
     * Constructor to add a user
     *
     * @param lastName
     * @param firstName
     * @param email
     * @param address1
     * @param address2
     * @param city
     * @param state
     * @param zipcode
     * @param country
     * @return
     */
    public static boolean addUser(String lastName, String firstName, String email,
            String address1, String address2, String city, String state,
            String zipcode, String country) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
            insertRow = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            insertRow.setString(1, lastName);
            insertRow.setString(2, firstName);
            insertRow.setString(3, email);
            insertRow.setString(4, address1);
            insertRow.setString(5, address2);
            insertRow.setString(6, city);
            insertRow.setString(7, state);
            insertRow.setString(8, zipcode);
            insertRow.setString(9, country);
            insertRow.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key");
            } else {
                System.out.println("ERROR: Could not add row to USER table: " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table");
            return false;
        }
        System.out.println("Added user to USER table");

        // user added successfully 
        return true;
    }

    /**
     * Adds a user to the database
     *
     * @param user
     * @return
     */
    public static boolean addUser(User user) {

        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        // insert the new row into the table
        try {
            ps = connection.prepareStatement("INSERT INTO user VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, user.getLastName());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getAddress1());
            ps.setString(5, user.getAddress2());
            ps.setString(6, user.getCity());
            ps.setString(7, user.getState());
            ps.setString(8, user.getZipcode());
            ps.setString(9, user.getCountry());
            ps.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into USER; dup primary key: " + user.getUserID());
            } else {
                System.out.println("ERROR: Could not add row to USER table: " + user.getUserID() + " " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to USER table: " + user.getUserID());
            return false;
        }
        System.out.println("Added user to USER table: " + user.getUserID());

        // user added successfully 
        return true;
    }

    /**
     * Returns the current user
     *
     * @param userID
     * @return
     */
    public static User getUser(String userID) {

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        User myUser = new User();

        String firstName = "";
        String lastName = "";
        String email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";

        String query = "SELECT lastName, firstName, email, address1, address2, city, state, zipcode, country FROM user WHERE userID=" + userID;
        System.out.println("Query: " + query);
        try {

            // Find the speciic row in the table
            System.out.println("before: " + myUser.toString());

            resultSet = statement.executeQuery(query);
            if (resultSet == null) {
                System.out.println("WARNING: Could not find user in USER table: " + userID);
                return null;
            } else {
                while (resultSet.next()) {
                    lastName = resultSet.getString("lastName");
                    firstName = resultSet.getString("firstName");
                    email = resultSet.getString("email");
                    address1 = resultSet.getString("address1");
                    address2 = resultSet.getString("address2");
                    city = resultSet.getString("city");
                    state = resultSet.getString("state");
                    zipcode = (resultSet.getString("zipcode"));
                    country = resultSet.getString("country");

                    myUser.setLastName(lastName);
                    myUser.setFirstName(firstName);
                    myUser.setEmail(email);
                    myUser.setAddress1(address1);
                    myUser.setAddress2(address2);
                    myUser.setCity(city);
                    myUser.setState(state);
                    myUser.setZipcode(zipcode);
                    myUser.setCountry(country);
                }
                //System.out.println("Found user in user table: " + userID);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement: " + query);
            System.out.println("SQL error: " + se);
            return null;
        }

        myUser.setUserID(userID);
        System.out.println("after: " + myUser.toString());
        return myUser;
    }

    /**
     * Returns all the users from the User ArrayList
     *
     * @return users
     */
    public static ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList();

        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        String userID = "";
        String firstName = "";
        String lastName = "";
        String email = "";
        String address1 = "";
        String address2 = "";
        String city = "";
        String state = "";
        String zipcode = "";
        String country = "";
        try {
            // Find the speciic row in the table
            resultSet = statement.executeQuery(
                    "SELECT userName, lastName, firstName, email, address1, address2, city, state, zipcode, country FROM user ORDER BY userName");
            while (resultSet.next()) {
                userID = resultSet.getString("userID");
                lastName = resultSet.getString("lastName");
                firstName = resultSet.getString("firstName");
                email = resultSet.getString("email");
                address1 = resultSet.getString("address1");
                address2 = resultSet.getString("address2");
                city = resultSet.getString("city");
                state = resultSet.getString("state");
                zipcode = resultSet.getString("zipcode");
                country = resultSet.getString("country");
                User user = new User(userID, lastName, firstName, email, address1, address2, city, state, zipcode, country);
                users.add(user);
                System.out.println("Found user in USER table: " + userID);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "UserDB.getAllUsers()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return users;
    }

    //update users ratings
    public static void updateUserItemRating(String ratingID, String rating, String madeIt) {
        Connection connection = DbConnection.getConnection();
        PreparedStatement ps;
        //System.out.println("Update user item rating: "+ ratingID + " "+ rating);
        try {
            String sql = "UPDATE rating SET rating=?, madeIt=? WHERE ratingID=?";
            ps = connection.prepareStatement(sql);

            ps.setString(1, rating);
            ps.setString(2, madeIt);
            ps.setString(3, ratingID);

            ps.executeUpdate();

            //System.out.println("Passed Update ID: " + item.getItemID());
        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not update record into ITEM; dup primary key: ");
            } else {
                System.out.println("ERROR: Could not update row to ITEM table: " + " Cause: " + se.getCause());
            }

        } catch (Exception e) {
            System.out.println("ERROR: Could not update row to ITEM table: " + " Cause: " + e.getCause());

        }

    }

    /**
     * Returns the current user profile
     *
     * @param userID
     * @return
     */
    public static ArrayList<ItemRating> getUserProfile(String userID) {
        UserProfile profile = new UserProfile();

        ArrayList<ItemRating> ratings = new ArrayList();
        Statement statement = DbConnection.getNewStatement();
        ResultSet resultSet = null;

        String ratingID = "";
        String itemID = "";
        String name = "";
        String rating = "";
        String madeIt = "";

        try {
            String sql = "SELECT ratingID, itemID, name, rating, madeIt FROM rating WHERE userID = '" + userID + "' ORDER BY itemID";

            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                ratingID = resultSet.getString("ratingID");
                itemID = resultSet.getString("itemID");
                name = resultSet.getString("name");
                rating = resultSet.getString("rating");
                madeIt = resultSet.getString("madeIt");
                ratings.add(new ItemRating(ratingID, itemID, userID, name, rating, madeIt));

                //System.out.println("Found user in rating table: " + ratingID);
            }
        } catch (SQLException se) {
            System.out.println("ERROR: Could not exicute SQL statement in: " + "UserDB.getAllUsers()");
            System.out.println("ERROR: Could not exicute SQL statement: " + se);
            return null;
        }

        return ratings;
    }

    /**
     * Updates the user ratings
     *
     * @param userID
     * @param itemID
     * @param name
     * @param rating
     * @param madeIt
     * @return
     */
    public static boolean addUserItemRating(String userID, String itemID, String name, String rating, String madeIt) {
        //System.out.println("Insert Into User Item Rating: "+ userID + " " + itemID + " " + name + " " + rating + " " + madeIt + " ");
        Connection connection = DbConnection.getConnection();
        PreparedStatement insertRow;
        // insert the new row into the table
        try {
            insertRow = connection.prepareStatement("INSERT INTO rating (userID, itemID, name, rating, madeIt) VALUES (?, ?, ?, ?, ?)");

            insertRow.setString(1, userID);
            insertRow.setString(2, itemID);
            insertRow.setString(3, name);
            insertRow.setString(4, rating);
            insertRow.setString(5, madeIt);
            insertRow.executeUpdate();

        } catch (SQLException se) {
            if (((se.getErrorCode() == 30000) && ("23505".equals(se.getSQLState())))) {
                System.out.println("ERROR: Could not insert record into RATING; dup primary key");
            } else {
                System.out.println("ERROR: Could not add row to RATING table: " + se.getCause());
            }
            return false;
        } catch (Exception e) {
            System.out.println("ERROR: Could not add row to RATING table");
            return false;
        }
        //System.out.println("Added user to RATING table");

        // user added successfully 
        return true;
    }

    /**
     * Deletes the user rating
     *
     * @param ratingID
     */
    public static void deleteUserItemRating(String ratingID) {

        System.out.println("Rating ID coming in: " + ratingID);
        Connection connection = DbConnection.getConnection();
        PreparedStatement deleteRating;

        try {
            deleteRating = connection.prepareStatement("DELETE FROM rating WHERE ratingID=?");
            deleteRating.setString(1, ratingID);
            deleteRating.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

