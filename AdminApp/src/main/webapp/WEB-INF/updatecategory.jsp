<%@page import="org.unibl.etf.ip.bean.CategoryBean"%>
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
</style>

<body>
    <%@include file="./header.jsp" %>
    <%
    	CategoryBean categoryBean = (CategoryBean) session.getAttribute("category");
    %>
    
    <div id="maindiv" class="d-flex flex-column align-items-center justify-content-center">
    <h2 class="text-center mb-4">Update Category</h2>
    <form method="post" action="?action=updatecategorypost" class="text-center">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" name="name" class="text-center form-control" required="required" id="name" value="<%= categoryBean.getName() %>">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <div style="margin-top: 10px;">
        <p><%= session.getAttribute("update-category-notification")!=null ? session.getAttribute("update-category-notification") : "" %></p>
    </div>
</div>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>