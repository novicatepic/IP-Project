<%@page import="org.unibl.etf.ip.dao.CategoryDAO"%>
<%@page import="org.unibl.etf.ip.dao.AttributeDAO"%>
<%@page import="org.unibl.etf.ip.bean.AttributeBean"%>
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
        
    <style>
    	body {
    		margin:0;
    	}
    </style>
    
</head>
<body>
    
    <%@include file="./header.jsp" %>
    
    <button class="btn btn-success btn-lg" data-bs-toggle="modal" data-bs-target="#addcategory">Add Category</button>

    
    <table class="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Attributes</th>
            <th scope="col">Add Attribute</th>
            <th scope="col">Update</th>
            <th scope="col">Delete</th>
          </tr>
        </thead>
        <tbody>
          <%
          	for(CategoryBean cb : categories) {
          		
          		ArrayList<AttributeBean> attributes = CategoryDAO.selectAttributes(cb.getId());
          		
          		out.println("<tr>");
                
                out.println("<th scope=\"row\">"+cb.getId()+"</th>");
          		out.println("<td>"+cb.getName()+"</td>");
          		
          		out.println("<td><ul>");
          		
         			for(AttributeBean attr : attributes) {
         				out.println("<li>"+attr.getName()+" - " + attr.getValue() + "</li>");
         				out.println("<form action=\"?action=removeattributefromcategory\" method=\"post\">\r\n"
                				+ "                <button class=\"btn btn-success\">Remove</button>\r\n"
                				+ "                <input type=\"text\" name=\"attrId\" style=\"display: none;\" value=\""+attr.getId()+"\">\r\n"
                				+ "            </form>\r\n");
         			}
          		
          		out.println("</td></ul>");
          		
          		out.println("<td><form action=\"?action=addattributetocategory\" method=\"post\">\r\n"
        				+ "                <button class=\"btn btn-success\">Add Attribute</button>\r\n"
        				+ "                <input type=\"text\" name=\"id\" style=\"display: none;\" value=\""+cb.getId()+"\">\r\n"
        				+ "            </form></td>\r\n");
          		
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

	<div class="modal fade" id="addcategory" tabindex="-1" aria-labelledby="enrollLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h1 class="modal-title fs-5" id="enrollLabel">Add Category</h1>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <p class="lead">New category</p>
          <form method="post" action="?action=addcategory">
            <div class="mb-3">
                <label for="category-name" class="col-form-label">Category name</label>
                <input id="category-name" name="categoryName" type="text" class="form-control">
                
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