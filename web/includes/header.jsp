
<%@page import="java.util.Random"%>
<!DOCTYPE html> 
<%@page contentType="text/html" pageEncoding="utf-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="model.*" %>
<html>
    <head>
        <title>UNCC Movie Rating System</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="main.css">
    </head>
    <body>
        <div class='head-container'>
            <img src="images/uncc.png" alt="uncc" class="headerimg">
            <h1 class="hcenteralign"><br>UNCC Movie Rating System</h1>
            
            <div class="floatme"> Welcome, ${user.firstName}  
   
            </div>
            <br>
        </div>
        <div class ="clearfix"></div>
        
    