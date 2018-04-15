/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author Daniel Christopher Hirt
 * ITIS 4166 Assignment 4
 * UserProfile
 */

public class UserProfile implements Serializable {
    private String userID;
    private ArrayList<ItemRating> userRatings;

    /**
     * UserProfile constructor
     * @param UserID
     * @param userRatings
     */
    public UserProfile(String UserID, ArrayList<ItemRating> userRatings) {
        this.userID = UserID;
        this.userRatings = userRatings;
    }
    
    /**
     * UserProfile default constructor
     */
    public UserProfile() {
        this.userID = "";
        this.userRatings = new ArrayList<>();
    }
    
    /**
     * Adds an item based on user rating to the DB
     * @param ratingID
     * @param ratedItemID
     * @param name
     * @param inRating
     * @param inMadeIt
     */
    public void addItem(String ratingID, String ratedItemID, String name, String inRating, String inMadeIt){
        ItemRating adding = new ItemRating(ratingID, ratedItemID, this.userID, name, inRating, inMadeIt);
        if(containsItem(ratedItemID)){ 
             replaceItem(adding);
        } else {
             userRatings.add(adding);
             UserDB.addUserItemRating(this.userID, ratedItemID, name, inRating,inMadeIt);
        }
        
    }
    
    /**
     * Returns the last item based on rating ID
     * @return id
     */
    public int getLastItemID(){
        int id = 0;
        if(userRatings.size() != 0)
            return Integer.parseInt(userRatings.get(userRatings.size() - 1).getRatingID());
        else
            return id;
    }

    /**
     * Checks if an a list contains the current item based on item ID
     * @param itemID
     * @return
     */
    public boolean containsItem(String itemID){
        boolean userList = false;
        for(ItemRating existingItem : userRatings){
            if(existingItem.getItemID().equals(itemID)){
                userList=true;
            }
        }
        return userList;
    }
    
    /**
     * Helper method to update the user rating in the DB
     * @param itemID
     * @param inRating
     * @param madeIt
     */
    public void updateRating(String itemID, String inRating, String madeIt){
       ItemRating working = new ItemRating();
        if(containsItem(itemID)){
            for(ItemRating existingItem : userRatings){
                if(existingItem.getItemID().equals(itemID)){
                    existingItem.setRating(inRating);
                    UserDB.updateUserItemRating(existingItem.getRating(), inRating, madeIt);
                    break;
                }
            }
        }
    }
    
    /**
     * Helper method to update the flag
     * @param itemID
     * @param inFlag
     */
    public void updateMadeIt(String itemID, String inFlag){
        if(containsItem(itemID)){
            for(ItemRating existingItem : userRatings){
                if(existingItem.getItemID().equals(itemID)){
                    existingItem.setMadeIt(inFlag);
                }
            }
        }
    }
    
    /**
     * Updates the user's profile
     */
    public void updateData(){
        this.userRatings = UserDB.getUserProfile(this.userID);
    }
    
     /**
     * Helper method to replace an item based on rating
     * @param newRating
     * 
     */
    private void replaceItem(ItemRating newRating){
        for(ItemRating existingItem : userRatings){
            if(existingItem.getItemID().equals(newRating.getItemID())){
                existingItem = newRating;
                UserDB.updateUserItemRating(newRating.getItemID(), newRating.getRating(), newRating.getMadeIt());
            }
        }
    }
    
    /**
     * Returns an item rating based on item ID
     * @param itemID
     * @return
     */
    public ItemRating getItem(String itemID){
        ItemRating userList = new ItemRating();
        if(containsItem(itemID)){
            for(ItemRating existingItem : userRatings){
                if(existingItem.getItemID().equals(itemID)){
                    return existingItem;
                }
            }
        }
        return userList;
    }

    /**
     * Helper method to remove an item 
     * @param removeID
     */
    public void removeItem(String removeID) {
        ItemRating working = new ItemRating();
        for (ItemRating existingItem : userRatings) {
            if (existingItem.getItemID().equals(removeID)) {
                working = existingItem;
                // System.out.println("Existing Item ratingID: "+ existingItem.getRatingID());
                UserDB.deleteUserItemRating(existingItem.getRatingID());
            }
        }
        userRatings.remove(working);
    }
    
// Getters and Setters for UserProfile class
    
    public ArrayList<ItemRating> getItems(){
        return userRatings;
    }
    
    public void emptyProfile(){
        userID = "default";
        userRatings = new ArrayList<>();
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public ArrayList<ItemRating> getUserRatings() {
        return userRatings;
    }

    public void setUserRatings(ArrayList<ItemRating> userRatings) {
        this.userRatings = userRatings;
    }
    
    
}
