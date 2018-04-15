/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
/**
 *
 * @author Daniel Christopher Hirt
 * ITIS 4166 Assignment 4
 * ItemRating JavaBean
 */

public class ItemRating implements Serializable{
    private String ratingID;
    private String itemID;
    private String userID;
    private String name;
    private String rating; 
    private String madeIt; 

    /**
     *
     * @param ratingID
     * @param itemID
     * @param userID
     * @param name
     * @param rating
     * @param madeIt
     */
    public ItemRating( String ratingID, String itemID, String userID,String name, String rating, String madeIt) {
        this.ratingID = ratingID;
        this.itemID = itemID;
        this.userID = userID;
        this.name = name;
        this.rating = rating;
        this.madeIt = madeIt;
    }
    
    /**
     * Default ItemRating constructor
     */
    public ItemRating() {

        this.itemID = "Default";
        this.rating = "0";
        this.madeIt = "0";
        this.userID = "0";
    }

    // Getters and Setters for ItemRating JavaBean
    
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getMadeIt() {
        return madeIt;
    }

    public void setMadeIt(String madeIt) {
        this.madeIt = madeIt;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getRatingID() {
        return ratingID;
    }

    public void setRatingID(String ratingID) {
        this.ratingID = ratingID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
