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
        height: 100vh;
    }
    
</style>

<body>
    
    <div id="maindiv" class="d-flex flex-column align-items-center justify-content-center">
    <h2 class="text-center mb-4">Login Form</h2>
    <form method="post" action="?action=login" class="text-center">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" required="required" class="text-center form-control" id="username" placeholder="Username">
        </div>
        <div class="form-group">
            <label for="exampleInputPassword1">Password</label>
            <input type="password" required="required"  name="password" class="text-center form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <div style="margin-top: 10px;">
        <p><%= session.getAttribute("notification")!=null ? session.getAttribute("notification") : "" %></p>
    </div>
    <div style="margin-top: 10px;">
        <p><%= session.getAttribute("login-invalid")!=null ? session.getAttribute("login-invalid") : "" %></p>
    </div>
    <% session.setAttribute("notification", null); session.setAttribute("login-invalid", null); %>
</div>


    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>