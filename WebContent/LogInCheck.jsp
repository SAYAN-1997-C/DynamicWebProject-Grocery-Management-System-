<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 

String gmail=request.getParameter("gmail");
String pass =request.getParameter("pass");

if(gmail.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("admin"))
{
	response.sendRedirect("HomeMain.jsp");
}
else
{
	request.setAttribute("error", "You've entered wrong username/password");
	RequestDispatcher rd = request.getRequestDispatcher("LogIn.jsp");
	rd.forward(request, response);
}

%>

</body>
</html>