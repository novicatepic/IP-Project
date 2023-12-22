<%@page import="org.unibl.etf.ip.bean.ConsultantBean"%>
<%@page import="org.unibl.etf.ip.bean.FitnessUserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	ArrayList<ConsultantBean> consultants = new ArrayList();
	if(session.getAttribute("consultants") != null) {
		consultants = (ArrayList<ConsultantBean>)session.getAttribute("consultants");
	}
%>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consultants</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
    
   <%@include file="./header.jsp" %>
    
    <button class="btn btn-success btn-lg" data-bs-toggle="modal" data-bs-target="#addconsultant">Add New Consultant</button>
    
    <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">User Name</th>
          </tr>
        </thead>
        <tbody>
          <%
          	for(ConsultantBean consultant : consultants) {
          		out.println("<tr>");
                
                out.println("<th scope=\"row\">"+consultant.getId()+"</th>");
          		out.println("<td>"+consultant.getName()+"</td>");
          		out.println("<td>"+consultant.getLastName()+"</td>");
          		out.println("<td>"+consultant.getUsername()+"</td>");	
          	}
          %>

        </tbody>
      </table>
      
      <div class="modal fade" id="addconsultant" tabindex="-1" aria-labelledby="enrollLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="enrollLabel">Add Consultant</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p class="lead">New Consultant</p>
          <form method="post" action="?action=addconsultant">
            <div class="mb-3">
                <label for="name" class="col-form-label">First name*</label>
                <input id="name" name="name" type="text" class="form-control" required="required">
            </div>
            <div class="mb-3">
                <label for="lastname" class="col-form-label">Last name*</label>
                <input id="lastname" name="lastname" type="text" class="form-control" required="required">             
            </div>
            <div class="mb-3">
                <label for="username" class="col-form-label">User Name*</label>
                <input id="username" name="username" type="text" class="form-control" required="required">
            </div>
            <div class="mb-3">
                <label for="password" class="col-form-label">Password*</label>
                <input id="password" name="password" type="password" class="form-control" required="required">
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
          </form>
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>