
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.include file="/includes/header.jsp"/>
<jsp:directive.include file="/includes/site-navigation.jsp"/>
<div class='tm-container'>
<jsp:directive.include file="/includes/user-navigation.jsp"/>
<%@page import="model.*" %>
<br><br>
            <p>Home > Catalog > My Movies (Profile) </p>
            
            <br><br>
            <h2>Your List of Movies</h2><br>
            <p>This page displays all of the movies, along with their rating/flag, that you
                have saved. Click on "Delete Movie" to remove the current movie from the list, 
                otherwise click "Update" to return and change the values.
                <br><br>
            <p><strong>User Information</strong></p>
            <br>
            Name: ${user.firstName} ${user.lastName}
            <br>
            Email: ${user.email}
            <br>
            Address Line 1: ${user.address1} <br>
            Address Line 2: ${user.address2} <br>
            City: ${user.city} <br>
            State: ${user.state} <br>
            Zip Code: ${user.zipcode} <br>
            Country: ${user.country} <br>
            <br>
            <br>
            Saved Items:<br><br>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Rating</th>
                    <th>Seen It?</th>   
                </tr>
       <c:forEach items="${ratings}" var="rating">
            <form  action='profile' method='post'>
               
                <tr>
                    <td>${rating.getItemID()}</td>
                    <td>${rating.getName()}</td>
                    <td>${rating.getRating()}<td>
                    <td>${rating.getMadeIt()}</td>
                    <td><input class='deletebtn' type='submit' name='action' value='Update'></td>
                    <td><input class='deletebtn' name='action' type='submit' value='Delete'></td>
                </tr>
              
                <input type='hidden' name='movieID' value='${rating.getItemID()}'>
            </form>
        </c:forEach>
            
    </table>  
<br><br>

     <br>
     <a href="catalog?action=catalog" class='backlink'>Back to Catalog</a>
     <br>
     <br>
            <br><br>
        </div><jsp:directive.include file="/includes/footer.jsp" />
