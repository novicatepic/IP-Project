<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meni</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<style>
    #maindiv {
        height: 80vh;
    }
    
    form div {
    	width: 300px;
    }
    
</style>

<body>
    <%@include file="./header.jsp" %>

   <div id="maindiv" class="d-flex flex-column align-items-center justify-content-center">
        <form action="?action=showcategory" method="post">
            <div class="mb-3"><button class="btn btn-primary w-100">Categories</button></div>
        </form>

        <form action="?action=showfitnessusers" method="post">
            <div class="mb-3"><button class="btn btn-primary w-100">Fitness users</button></div>
        </form>

        <form action="?action=showstats" method="post">
            <div class="mb-3"><button class="btn btn-primary w-100">Statistics</button></div>
        </form>
        
        <form action="?action=newconsultant" method="post">
            <div class="mb-3"><button class="btn btn-primary w-100">Consultants</button></div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html> 