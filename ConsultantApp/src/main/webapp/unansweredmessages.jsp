<%@page import="org.unibl.etf.ip.bean.FitnessUserBean"%>
<%@page import="org.unibl.etf.ip.dao.MessageDAO"%>
<%@page import="org.unibl.etf.ip.bean.MessageBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="consultantBean" class="org.unibl.etf.ip.bean.ConsultantBean" scope="session"></jsp:useBean>  

<%
	if(!consultantBean.isLoggedIn()) {
		response.sendRedirect("login.jsp");
	}
	ArrayList<MessageBean> unasweredMessages = MessageDAO.selectAllUnanswered();
	
	String filterText = request.getParameter("filterText");
    if (filterText != null && !filterText.isEmpty()) {
    	unasweredMessages = MessageDAO.filterByText(filterText); // Replace with your actual filtering method
    }

%>
    

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        
    <style>
    	body {
    		margin:0;
    	}
    </style>
    
    <script>    
        function submitFilterUnasweredForm() {
            var filterText = document.getElementById('attribute-name2').value;
            document.getElementById('filterText2').value = filterText;
            document.getElementById('filterForm2').submit();
        }
    </script>
    
</head>
<body>
	
	<%@include file="./header.jsp" %>
	
	<div class="container">
		<p class="h2 mt-5">Unanswered messages</p>
	<div class="mb-3" style="margin-bottom: 10px;">
        <form id="filterForm2" method="post" action="messages.jsp">
            <input type="text" class="form-control" id="attribute-name2" name="attributeName" placeholder="Enter text to filter" required>
            <input type="hidden" id="filterText2" name="filterText" value="">
            <button  style="margin-top: 10px;" type="button" class="btn btn-primary" onclick="submitFilterUnansweredForm()">Filter</button>
        </form>
    </div>
	<table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Title</th>
            <th scope="col">Text</th>
            <th scope="col">Date</th>
            <th scope="col">Writer</th>
            <th scope="col">Respond</th>
          </tr>
        </thead>
        <tbody>
          <%
          	for(MessageBean m : unasweredMessages) {
          		
          		out.println("<tr>");
                
                out.println("<th scope=\"row\">"+m.getId()+"</th>");
                out.println("<td>"+m.getTitle()+"</td>");
          		out.println("<td>"+m.getText()+"</td>");
          		out.println("<td>"+m.getDate().toString()+"</td>");
          		
          		FitnessUserBean user = MessageDAO.selectUserWhoWroteMessage(m.getUser_id());
          		out.println("<td>"+user.getUsername()+"</td>");
          		
          		
          		out.println("<td><form action=\"message.jsp\" method=\"POST\">\r\n"
        				+ "      <input type=\"hidden\" name=\"id\" value=\"" + m.getId() + "\">\r\n"
        						+ "      <input type=\"hidden\" name=\"responseHidden\" value=\"responseHidden\">\r\n"
        				+ "      <button type=\"submit\" class=\"btn btn-success\">Respond to a message</button>\r\n"
        				+ "    </form></td>");
          		
          		
          		
          	}
          %>

        </tbody>
      </table>
	</div>
		 
	  <p class="lead"><%= session.getAttribute("email-notification")!=null ?session.getAttribute("email-notification") : "" %></p>	
	  
	  <%
	  	session.setAttribute("email-notification", null);
	  %>
		
   <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.0.7/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        
</body>
</html>