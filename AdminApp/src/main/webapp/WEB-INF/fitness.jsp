<%@page import="org.unibl.etf.ip.bean.FitnessUserBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<FitnessUserBean> users = new ArrayList();
	if(session.getAttribute("users") != null) {
		users = (ArrayList<FitnessUserBean>)session.getAttribute("users");
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
    	
    </style>
</head>
<body>
    
   <%@include file="./header.jsp" %>
    
    <div class="container" style="margin-top: 20px;">
    <h1 class="text-center mb-4">Fitness Users</h1>
    	    <button class="btn btn-success btn-lg" data-bs-toggle="modal" data-bs-target="#adduser">Add New Fitness User</button>
    
    <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">City</th>
            <th scope="col">User Name</th>
            <!-- <th scope="col">Avatar</th> -->
            <th scope="col">Mail</th>
            <th scope="col">Active</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
          </tr>
        </thead>
        <tbody>
          <%
          	for(FitnessUserBean user : users) {
          		out.println("<tr>");
                
                out.println("<th scope=\"row\">"+user.getId()+"</th>");
          		out.println("<td>"+user.getName()+"</td>");
          		out.println("<td>"+user.getLastName()+"</td>");
          		out.println("<td>"+user.getCity()+"</td>");
          		out.println("<td>"+user.getUsername()+"</td>");
          		
          		/*if(user.getAvatar() != null) {
          			out.println("<td>"+user.getAvatar()+"</td>");
          		} else {
          			out.println("<td>No avatar</td>");
          		}*/
          		
          		
          		out.println("<td>"+user.getMail()+"</td>");
          		
          		if(user.isActive()) {
          			out.println("<td>Active</td>");
          		} else {
          			out.println("<td>Inactive</td>");
          		}
          		
          		out.println("<td><form action=\"?action=updateuser\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-primary\">Update</button>\r\n"
        				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+user.getId()+"\">\r\n"
        				+ "            </form></td>");
          		
          		if(!user.isTerminated()) {
          			out.println("<td><form action=\"?action=deleteuser\" method=\"post\">\r\n"
            				+ "                <button class=\"btn btn-primary\">Terminate</button>\r\n"
            				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+user.getId()+"\">\r\n"
            				+ "            </form></td>");
          		} else {
          			out.println("<td><form action=\"?action=undeleteuser\" method=\"post\">\r\n"
            				+ "                <button class=\"btn btn-primary\">Determinate</button>\r\n"
            				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+user.getId()+"\">\r\n"
            				+ "            </form></td>");
          		}
          		/*out.println("<td><form action=\"?action=updateuser\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-primary\">Update</button>\r\n"
        				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+user.getId()+"\">\r\n"
        				+ "            </form></td>\r\n"
        				+ "            <td><form action=\"?action=deleteuser\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-primary\">Terminate</button>\r\n"
        				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+user.getId()+"\">\r\n"
        				+ "            </form></td>");*/
          		
          		
                
          		out.println("</tr>");
          	}
          %>

        </tbody>
      </table>
      
      <div class="modal fade" id="adduser" tabindex="-1" aria-labelledby="enrollLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="enrollLabel">Add Fitness User</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p class="lead">New Fitness User</p>
          <form method="post" action="?action=addfitnessuser">
<div class="mb-3 text-center">
    <label for="name" class="col-form-label">First name*</label>
    <input id="name" name="name" type="text" class="text-center form-control" required="required">
</div>
<div class="mb-3 text-center">
    <label for="lastname" class="col-form-label">Last name*</label>
    <input id="lastname" name="lastname" type="text" class="text-center form-control" required="required">             
</div>
<div class="mb-3 text-center">
    <label for="city" class="col-form-label">City*</label>
    <input id="city" name="city" type="text" class="text-center form-control" required="required">
</div>
<div class="mb-3 text-center">
    <label for="username" class="col-form-label">User Name*</label>
    <input id="username" name="username" type="text" class="text-center form-control" required="required">
</div>
<div class="mb-3 text-center">
    <label for="password" class="col-form-label">Password*</label>
    <input id="password" name="password" type="password" class="text-center form-control" required="required">
</div>
<div class="mb-3 text-center">
    <label for="avatar" class="col-form-label">Avatar</label>
    <input id="avatar" name="avatar" type="text" class="text-center form-control">
</div>
<div class="mb-3 text-center">
    <label for="mail" class="col-form-label">Mail*</label>
    <input id="mail" name="mail" type="email" class="text-center form-control" required="required">
</div>

            <div class="d-flex justify-content-start">
                        <button type="submit" class="btn btn-primary me-2">Submit</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    </div>
          </form>
        </div>
      </div>
    </div>
  </div>
    </div>
    
    


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>