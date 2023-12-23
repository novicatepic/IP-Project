<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <jsp:useBean id="consultantBean" class="org.unibl.etf.ip.bean.ConsultantBean" scope="session"></jsp:useBean>  
<%
	consultantBean.setLoggedIn(false);
	session.invalidate();
	response.sendRedirect("start.jsp");
%>  
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Logout</title>
</head>
<body>

</body>
</html>