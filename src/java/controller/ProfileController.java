/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;
import model.UserDB;
import model.UserProfile;
/**
 *
 * @author Daniel Christopher Hirt
 * ITIS 4166 Assignment 4
 * Profile Controller
 */

@WebServlet(name = "ProfileController", urlPatterns = {"/profile"})
public class ProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        ArrayList<User> theUser;
        theUser = (ArrayList) session.getAttribute("theUser");
        UserProfile currentUP;
        currentUP = (UserProfile) session.getAttribute("currentProfile");
        String action = request.getParameter("action");

        switch(action){
            case "profile":
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                break;
        }
        
       
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
       
        
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        
        // Handles different routing based on "action" parameter
        switch(action){
            // Routs to the profile displaying user info (if exists) and saved items
            case "profile":
                UserProfile currentUP = (UserProfile) session.getAttribute("currentProfile");
                currentUP.updateData();
                session.setAttribute("ratings", currentUP.getUserRatings());
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                break;
            // Loads in the user from the database
            case "signin":
                // Get user from DB
                User user = UserDB.getUser("1");
                // Save user to session
                session.setAttribute("user", user);
                // Assign userID to UserProfile
                UserProfile userProf = new UserProfile();
                userProf.setUserID(user.getUserID());
                // Save userProfile to session
                session.setAttribute("currentProfile", userProf);
                // Redirect
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                break;
            case "signout":
                 session.setAttribute("currentProfile", null);
                 request.getRequestDispatcher("/index.jsp").forward(request, response);
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
      
        
        HttpSession session = request.getSession();
         
        UserProfile currentUP = (UserProfile) session.getAttribute("currentProfile");
        String action = request.getParameter("action");
        String movieID = request.getParameter("movieID");
        
        // Handles different routing/posting based on "action" parameter
        switch(action){
            // Redirects user to feedback.jsp to update rating/seen it
            case "Update":
                System.out.println("Redirecting to Update");
                request.getRequestDispatcher("feedback.jsp").forward(request, response);
                response.sendRedirect("catalog?action=edit&movieID="+movieID);
                break;
            // Deletes the item and refreshes profile page
            case "Delete":
                System.out.println("Reached Delete Item ID: "+ movieID);
                currentUP.removeItem(movieID);
                session.setAttribute("currentProfile", currentUP);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
                break;
        }
        
        
        
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

}
