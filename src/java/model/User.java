package model;

import java.io.Serializable;

/**
 *
 * @author Daniel Christopher Hirt
 * ITIS 4166 Assignment 4
 * User JavaBean
 */

public class User implements Serializable {

    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipcode;
    private String country;

    /**
     * Default constructor
     */
    public User() {
        this.userID = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.address1 = "";
        this.address2 = "";
        this.city = "";
        this.state = "";
        this.zipcode = "";
        this.country = "";

    }

    /**
     *
     * @param userID
     * @param lastName
     * @param firstName
     * @param email
     * @param address1
     * @param address2
     * @param city
     * @param state
     * @param zipcode
     * @param country
     */
    public User(String userID, String lastName, String firstName, String email,
            String address1, String address2, String city, String state,
            String zipcode, String country) {

        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;

    }

// Getters and setters for User JavaBean
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", state=" + state + ", zipcode=" + zipcode + ", country=" + country + '}';
    }
 
}

