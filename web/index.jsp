
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:directive.include file="/includes/header.jsp"/>
<jsp:directive.include file="/includes/site-navigation.jsp"/>


<div class='tm-container'>  
<jsp:directive.include file="/includes/user-navigation.jsp"/>
            <br><br>
            <p>Home</p>
            <br><br>
            Welcome to the UNCC Movie Rating application. This is a simple application that allows users
            to keep up to date with the latest movies. On the <a href="catalog.jsp">catalog</a> page you
            will find a variety of movies - some old and some new. Feel free to leave a rating and save 
            movies you like or are interested in for future viewing. 
            <br><br>
            The application thus far (up to Assignment 4) contains a default and/or placeholder user. The user can 
            choose to rate/save/update for a specific movie of their choosing. Next step is 
            MySQL/JDBC integration. 
           <br>
           <br>
           <br>
           <br>
           <br>
           <br>
    
        </div>
<jsp:directive.include file="/includes/footer.jsp" />
