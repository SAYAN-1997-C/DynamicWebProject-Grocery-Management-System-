<%@page import="master.DAO.DalMahajanDAO"%>
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
	DalMahajanDAO mdao=new DalMahajanDAO();
	String mid=request.getParameter("mid");
	int flag=0;
	
	flag=mdao.MidCheck(mid);
	if(flag==1)
	{
		request.setAttribute("error", "Mahajan ID is already exist!");
		RequestDispatcher rd = request.getRequestDispatcher("DalMahajanAdd.jsp");
		rd.forward(request, response);
	}
	else{
		RequestDispatcher rd=request.getRequestDispatcher("DalMahajanAddServe");
		rd.forward(request, response);
	}
%>

</body>
</html>