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
 * Item JavaBean
 */

public class Item implements Serializable {
    
    private String itemID;
    private String name;
    private String imageFilePath;
    private String rating;
    private String watched;
    private String description;
  
    /**
     * Default item constructor
     */
    public Item() {
        
        this.itemID = "";
        this.name = "";
        this.imageFilePath = "";
        this.rating = "0";
        this.watched = "0";
        this.description = "";
       
    }

    /**
     * Constructor to add an item
     * @param itemID
     * @param name
     * @param imageFilePath
     * @param rating
     * @param watched
     * @param description
     */
    public Item(String itemID, String name, String imageFilePath,  String rating,  String watched, String description) {
        
        this.itemID = itemID;
        this.name = name;
        this.imageFilePath = imageFilePath;
        this.rating = rating;
        this.watched = watched;
        this.description = description;
        
    }

    // Getters and Setters for the Item JavaBean
    
    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getWatched() {
        return this.watched;
    }

    public void setWatched(String watched) {
        this.watched = watched;
    }
 
    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }


    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" + "itemID=" + itemID + ", name=" + name + ", imageFilePath=" + imageFilePath + ", rating=" + rating + ", watched=" + watched + ", description=" + description + '}';
    }
    
    /**
     * Helper method for flag updates
     * @param myInt
     * @return
     */
    public static String getBoolValue(String myInt){
            
            switch(myInt){
                case "1": return "true";
                default: return "false";
            }
         }

}

    
    

    
    
    

