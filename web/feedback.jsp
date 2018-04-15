
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:directive.include file="/includes/header.jsp"/>
<jsp:directive.include file="/includes/site-navigation.jsp"/>
<div class='tm-container'>
<jsp:directive.include file="/includes/user-navigation.jsp"/>
<%@page import="model.*" %>
<br><br>
            <p>Home > Catalog > Item/Feedback  </p>
           


<%--  Item myItem = (Item) request.getAttribute("item");
    UserProfile currentUP = (UserProfile) session.getAttribute("currentProfile");
    String rating = "0";
    if(currentUP!=null){
        java.util.ArrayList<ItemRating> curList = currentUP.getItems();
        if(curList!=null){
            for(ItemRating item : curList){
                if(item.getItemID().equals(myItem.getItemID()))
                    rating = item.getRating();
            }
        }
    }
    --%>

    
<br><br>
<strong>Movie Name:</strong> ${movieFeedback.name} 
<br><br>
<strong>Movie Description:</strong> <br><br>
${movieFeedback.description}<br><br>

<br>
<strong>Average Rating:</strong> ${movieFeedback.rating} 
<br>
<br>
<form action="catalog" method="post">
    Please provide a rating (0-5 stars): <select name="rating">
        <c:forEach begin="0" end="5" varStatus="loop">
            <option value="${loop.index}" ${loop.index == movieFeedback.rating? 'selected="selected"' : ''}>${loop.index}</option>
        </c:forEach>
    </select>
    <br>
    <br>
                

                <br>
                <br>

    Have you viewed this movie? <select name="flag">
        <c:forEach begin="0" end="1" varStatus="loop">
            <option value="${loop.index}" ${Integer.toString(loop.index) == movieFeedback.watched ? 'selected="selected"' : ''}>${movieFeedback.getBoolValue(Integer.toString(loop.index))}</option>
        </c:forEach>

    </select>
        <br>
        <br>
<br>
<br>

            <br><br>

                <input class="input" type="submit" value="Save to My Movies">
                <input type="hidden" name="movieID" value ="${movieFeedback.itemID}">
                <input type='hidden' name="action" value ="save">
            </form>
            <a href="catalog?action=catalog" class='backlink'>Back to Catalog</a>
           <br><br>
        </div>
<jsp:directive.include file="/includes/footer.jsp" />
