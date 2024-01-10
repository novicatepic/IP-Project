<%@page import="org.unibl.etf.ip.bean.CategoryBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attribute To Category</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<style>
    #maindiv {
        height: 90vh;
    }
</style>

</head>
<body>
	<%@include file="./header.jsp" %>
	    
    <%
    	CategoryBean cb = (CategoryBean) session.getAttribute("catattr");
    %>
    
    <div id="maindiv" class="d-flex flex-column align-items-center justify-content-center">
    <h2 class="text-center mb-4">Add Attribute To Category</h2>
    <form method="post" action="?action=finishattributeadd" class="text-center">
        <div class="form-group">
            <label for="name">Category name</label>
            <input type="text" name="name" class="text-center form-control" id="name" readonly value="<%= cb.getName() %>">
        </div>
        <div class="form-group">
            <label for="attrname">Attribute Name</label>
            <input type="text" name="attrname" class="text-center form-control" required="required" id="attrname" placeholder="Name">
        </div>
        <div class="form-group">
            <label for="attrvalue">Attribute Value</label>
            <input type="text" required="required" name="attrvalue" class="text-center form-control" id="attrvalue" placeholder="Value">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <div style="margin-top: 10px;">
        <p><%= session.getAttribute("update-user-notification")!=null ? session.getAttribute("update-user-notification") : "" %></p>
    </div>
</div>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>