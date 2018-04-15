
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:directive.include file="/includes/header.jsp"/>
<jsp:directive.include file="/includes/site-navigation.jsp"/>
<div class='tm-container'>
<jsp:directive.include file="/includes/user-navigation.jsp"/>
            
<br><br>
            <p>Home > Catalog </p>
            <br><br>
            <h1>Current Movies</h1>
            <br>
           
            <c:forEach items="${movies}" var="movie">
            ${movie.name}<br><br>
            <img src="${movie.imageFilePath}" alt="transformers" height="324" width="216">
            <form class="catalog" action="catalog" method="post">
                <input class = "input"  type="submit" value="More Information">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="movieID" value ="${movie.itemID}">
            </form>            
            <br><br>
            </c:forEach>
            
        </div>
<jsp:directive.include file="/includes/footer.jsp" />
