<%@page import="org.unibl.etf.ip.bean.FitnessUserBean"%>
<%@page import="org.unibl.etf.ip.dao.MessageDAO"%>
<%@page import="org.unibl.etf.ip.bean.MessageBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="consultantBean" class="org.unibl.etf.ip.bean.ConsultantBean" scope="session"></jsp:useBean> 

<%
	if(!consultantBean.isLoggedIn()) {
		response.sendRedirect("login.jsp");
	}

	MessageBean m = null;
	if(request.getParameter("id") != null) {
		m = MessageDAO.selectOne(Integer.valueOf(request.getParameter("id")));
		
		if(m == null) {
			response.sendRedirect("messages.jsp");
		}
	}
	
	FitnessUserBean user = null;
	if(m != null) {
		user = MessageDAO.selectUserWhoWroteMessage(m.getUser_id());
		session.setAttribute("currentUser", user);
		if(request.getParameter("responseHidden") == null) {
			MessageDAO.updateOneRead(m.getId());
		}
	}
	
	
	if(request.getParameter("submit") != null) {
		String responseText = request.getParameter("responseText");
		System.out.println(responseText);
		String file = request.getParameter("file");
		System.out.println(file);
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

<%@include file="./header.jsp" %>

<div class="container" style="margin-top: 50px;">
  <div class="jumbotron">
    <h1 class="display-4">Title: <%= m.getTitle() %></h1>
    <p class="lead">Text: <%= m.getText() %></p>
    <hr class="my-4">
    <p>Writer: <%= user.getUsername() %></p>
    <p>Date: <%= m.getDate() %></p>
    
    
    <%
    	if(request.getParameter("responseHidden") != null) {   		
    		out.println("<form action=\"MessageServlet\" method=\"post\" enctype=\"multipart/form-data\">\r\n"
    				+ "<div class=\"form-group\">\r\n"
    				+ "        <label for=\"fileInput\">Attach File Or Image:</label>\r\n"
    				+ "        <input type=\"file\" name=\"file\" class=\"form-control-file\" id=\"fileInput\" name=\"fileInput\">\r\n"
    				+ "      </div>"
    				+ "<input type=\"hidden\" name=\"id\" value=\"" + m.getId() + "\" >\r\n"
    				+ "      <div class=\"form-group\">\r\n"
    				+ "        <label for=\"response\">Your Response:</label>\r\n"
    				+ "        <textarea class=\"form-control\" id=\"response\" name=\"responseText\" rows=\"3\" required></textarea>\r\n"
    				+ "      </div>\r\n"
    				+ "      <button type=\"submit\" name=\"submit\" class=\"btn btn-success\">Send Response</button>\r\n"
    				+ "    </form>");
    		
    		
    	} else {
    		out.println("<p class=\"lead\">Message updated as read!</p>");
    	}
    %>
    
    <!-- a class="btn btn-primary" href="messages.html">Go Back to Messages</a> -->
  </div>
</div>

<!-- Bootstrap JS and dependencies (optional, but required for some features) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>