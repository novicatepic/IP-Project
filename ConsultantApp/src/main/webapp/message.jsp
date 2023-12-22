<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="consultantBean" class="org.unibl.etf.ip.bean.ConsultantBean" scope="session"></jsp:useBean> 

<%
	if(!consultantBean.isLoggedIn()) {
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Responsive Message</title>
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      padding: 20px;
    }
  </style>
</head>
<body>

<div class="container">
  <div class="jumbotron">
    <h1 class="display-4">Message Title</h1>
    <p class="lead">Message Text goes here. You can add more details and information in this section.</p>
    <hr class="my-4">
    <p>Author: John Doe</p>
    <p>Date: December 22, 2023</p>
    <p class="lead">This page is nice and responsive!</p>
    <a class="btn btn-primary" href="messages.html">Go Back to Messages</a>
  </div>
</div>

<!-- Bootstrap JS and dependencies (optional, but required for some features) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>