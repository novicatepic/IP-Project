<%@page import="org.unibl.etf.ip.bean.FitnessUserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<style>
    #maindiv {
        height: 90vh;
    }
    
    form {
    	width: 400px;
    }
    
</style>

<body>
    <%@include file="./header.jsp" %>
    <%
    	FitnessUserBean user = (FitnessUserBean) session.getAttribute("fitnessuser");
    %>
    
    <div id="maindiv" class="d-flex flex-column align-items-center justify-content-center">
    <h2 class="text-center mb-4" style="margin-top:60px;">Update Fitness User</h2>
        <form method="post" action="?action=updateuserpost" class="text-center">
    <div class="form-group">
        <label for="name">First Name</label>
        <input required="required" type="text" name="name" class="form-control text-center" id="name" value="<%= user.getName() %>">
    </div>
    <div class="form-group">
        <label for="lastname">Last Name</label>
        <input required="required" type="text" name="lastname" class="form-control text-center" id="lastname" value="<%= user.getLastName() %>">
    </div>
    <div class="form-group">
        <label for="city">City</label>
        <input required="required" type="text" name="city" class="form-control text-center" id="city" value="<%= user.getCity() %>">
    </div>
    <div class="form-group">
        <label for="username">User Name</label>
        <input required="required" type="text" name="username" class="form-control text-center" id="username" value="<%= user.getUsername() %>">
    </div>
    <div class="form-group">
        <label for="avatar">Avatar</label>
        <input type="text" name="avatar" class="form-control text-center" id="avatar" value="<%= user.getAvatar() != null ? user.getAvatar() : "" %>">
    </div>
    <div class="form-group">
        <label for="mail">Mail</label>
        <input required="required" type="text" name="mail" class="form-control text-center" id="mail" value="<%= user.getMail() %>">
    </div>
    <button type="submit" class="btn btn-primary">Submit</button>
</form>

          <div style="margin-top: 10px;"><p><%= session.getAttribute("update-user-notification")!=null ? session.getAttribute("update-user-notification") : "" %></p></div> 
          <div style="margin-top: 10px;"><p><%= session.getAttribute("update-user-bad")!=null ? session.getAttribute("update-user-bad") : "" %></p></div> 
          
          <% session.setAttribute("update-user-notification", null); 
          	session.setAttribute("update-user-bad", null);
          %>
          
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>