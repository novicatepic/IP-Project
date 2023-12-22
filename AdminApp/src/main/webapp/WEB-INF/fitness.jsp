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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    
    
    
    <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Last Name</th>
            <th scope="col">City</th>
            <th scope="col">User Name</th>
            <th scope="col">Avatar</th>
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
          		
          		if(user.getAvatar() != null) {
          			out.println("<td>"+user.getAvatar()+"</td>");
          		} else {
          			out.println("<td>No avatar</td>");
          		}
          		
          		
          		out.println("<td>"+user.getMail()+"</td>");
          		
          		if(user.isActive()) {
          			out.println("<td>Active</td>");
          		} else {
          			out.println("<td>Inactive</td>");
          		}
          		
          		out.println("<td><form action=\"?action=updateuser\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-primary\">Update</button>\r\n"
        				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+user.getId()+"\">\r\n"
        				+ "            </form></td>\r\n"
        				+ "            <td><form action=\"?action=deleteuser\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-primary\">Delete</button>\r\n"
        				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+user.getId()+"\">\r\n"
        				+ "            </form></td>");
                
          		out.println("</tr>");
          	}
          %>

        </tbody>
      </table>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>