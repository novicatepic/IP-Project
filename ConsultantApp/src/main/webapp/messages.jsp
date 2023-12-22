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

	ArrayList<MessageBean> messages = MessageDAO.selectAll();
	
	String filterText = request.getParameter("filterText");
    if (filterText != null && !filterText.isEmpty()) {
        messages = MessageDAO.filterByText(filterText); // Replace with your actual filtering method
    }

%>
    

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Category</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        
    <style>
    	body {
    		margin:0;
    	}
    </style>
    
    <script>
        // Function to submit the form with the filter text
        function submitFilterForm() {
            var filterText = document.getElementById('attribute-name').value;
            document.getElementById('filterText').value = filterText;
            document.getElementById('filterForm').submit();
        }
    </script>
    
</head>
<body>

	<div class="mb-3" style="margin-bottom: 10px;">
        <form id="filterForm" method="post" action="messages.jsp">
            <label for="attribute-name" class="form-label">Attribute Name</label>
            <input type="text" class="form-control" id="attribute-name" name="attributeName" placeholder="Enter attribute name" required>
            <input type="hidden" id="filterText" name="filterText" value="">
            <button type="button" class="btn btn-primary" onclick="submitFilterForm()">Filter</button>
        </form>
    </div>

    <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Text</th>
            <th scope="col">Date</th>
            <th scope="col">Writer</th>
            <th scope="col">Check out message</th>
            <th scope="col">Respond</th>
          </tr>
        </thead>
        <tbody>
          <%
          	for(MessageBean m : messages) {
          		
          		out.println("<tr>");
                
                out.println("<th scope=\"row\">"+m.getId()+"</th>");
          		out.println("<td>"+m.getText()+"</td>");
          		out.println("<td>"+m.getDate().toString()+"</td>");
          		
          		FitnessUserBean user = MessageDAO.selectUserWhoWroteMessage(m.getUser_id());
          		out.println("<td>"+user.getUsername()+"</td>");
          		
          		out.println("<td><form action=\"message.jsp\" method=\"POST\">\r\n"
        				+ "      <input type=\"hidden\" value=\"" + m.getId() + "\">\r\n"
        				+ "      <button type=\"submit\" class=\"btn btn-primary\">Check out message</button>\r\n"
        				+ "    </form></td>");
          		
          		out.println("<td><form action=\"respond.jsp\" method=\"POST\">\r\n"
        				+ "      <input type=\"hidden\" value=\"" + m.getId() + "\">\r\n"
        				+ "      <button type=\"submit\" class=\"btn btn-success\">Respond to a message</button>\r\n"
        				+ "    </form></td>");
          		
          	}
          %>

        </tbody>
      </table>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
        
</body>
</html>