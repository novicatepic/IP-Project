<%@page import="org.unibl.etf.ip.bean.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ArrayList<CategoryBean> categories = new ArrayList();
	if(session.getAttribute("categories") != null) {
		categories = (ArrayList<CategoryBean>)session.getAttribute("categories");
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
</head>
<body>
    
    <button class="btn btn-success btn-lg" data-bs-toggle="modal" data-bs-target="#enroll">Start The Enrollment</button>
    
    <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
          </tr>
        </thead>
        <tbody>
          <%
          	for(CategoryBean cb : categories) {
          		out.println("<tr>");
                
                out.println("<th scope=\"row\">"+cb.getId()+"</th>");
          		out.println("<td>"+cb.getName()+"</td>");
          		
          		out.println("<td><form action=\"?action=updatecategory\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-primary\">Update</button>\r\n"
        				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+cb.getId()+"\">\r\n"
        				+ "            </form></td>\r\n"
        				+ "            <td><form action=\"?action=deletecategory\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-primary\">Delete</button>\r\n"
        				+ "                <input type=\"text\" style=\"display: none;\" name=\"id\" value=\""+cb.getId()+"\">\r\n"
        				+ "            </form></td>");
                
          		out.println("</tr>");
          	}
          %>

        </tbody>
      </table>

	<div class="modal fade" id="enroll" tabindex="-1" aria-labelledby="enrollLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="enrollLabel">Modal title</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p class="lead">Fill out this form and we will get back to you</p>
          <form>
            <div class="mb-3">
                <label for="first-name" class="col-form-label">First name</label>
                <input id="first-name" type="text" class="form-control">
            </div>
            <div class="mb-3">
                <label for="first-name" class="col-form-label">First name</label>
                <input id="first-name" type="text" class="form-control">
            </div>
            <div class="mb-3">
                <label for="first-name" class="col-form-label">First name</label>
                <input id="first-name" type="text" class="form-control">
            </div>
            <div class="mb-3">
                <label for="first-name" class="col-form-label">First name</label>
                <input id="first-name" type="text" class="form-control">
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Submit</button>
        </div>
      </div>
    </div>
  </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</body>
</html>