/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;
/**
 *
 * @author Daniel Christopher Hirt
 * ITIS 4166 Assignment 4
 * Catalog Controller
 */
@WebServlet(name = "CatalogController", urlPatterns = {"/CatalogController"})
public class CatalogController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     *
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        // Get routing
        String action = request.getParameter("action");
        
        // Check session for list of movies
        HttpSession session = request.getSession();
        ArrayList<Item> items = (ArrayList) session.getAttribute("movies");
        if (items == null) {
            items = ItemDB.getAllItems();
            session.setAttribute("movies", items);
           
        }
        
        switch(action){
            case "catalog":
                request.getRequestDispatcher("catalog.jsp").forward(request, response);
                break;
            case "rating":
                request.getRequestDispatcher("feedback.jsp").forward(request, response);
                break;
        }

    }
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            //processRequest(request, response);
            HttpSession session = request.getSession();
            // Get routing
            String action = request.getParameter("action");
            String movieID = request.getParameter("movieID");
            Item movie = requestMovie(session, movieID);
            
            // Routing based on "action" parameter
            switch(action){
                case "edit":
                    session.setAttribute("movieFeedback", movie);
                    //response.sendRedirect("feedback.jsp");
                    request.getRequestDispatcher("feedback.jsp").forward(request, response);
                    break;
                case "save": 
                    movie.setRating(request.getParameter("rating"));
                    movie.setWatched(request.getParameter("flag"));
                    updateMovie(session, movie);
                    response.sendRedirect("profile?action=profile");
                    break;
                default: break;

            }
            
    }
    
    /**
     * Helper method to update the movie
     * @param session
     * @param movie
     */
    protected void updateMovie(HttpSession session, Item movie){
        ItemDB.updateItem(movie);
        session.setAttribute("movieFeedback", movie);  
        session.setAttribute("movies", ItemDB.getAllItems());
        
        UserProfile currentUP = (UserProfile) session.getAttribute("currentProfile");
         if(currentUP == null){
            currentUP = new UserProfile();
            currentUP.setUserID("9");
        }
         currentUP.updateData();
         session.setAttribute("currentProfile", currentUP);
        int ratingID = currentUP.getLastItemID()+1;
        currentUP.addItem(Integer.toString(ratingID), movie.getItemID(),movie.getName(), movie.getRating(), movie.getWatched());
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    /**
     * Helper method for requested movies/items
     * @param session
     * @param movieID
     * @return movie
     */
    public Item requestMovie(HttpSession session, String movieID){
        Item movie = new Item();
        for(Item search: (ArrayList<Item>) session.getAttribute("movies")){
                    if(search.getItemID().equals(movieID)){
                        movie = search;
                        break;
                    }
                }
        return movie;
    }
        
        
}

