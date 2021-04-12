<%@page import="master.DAO.DalCustomerDAO"%>
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
	DalCustomerDAO cdao=new DalCustomerDAO();
	
	String cid=request.getParameter("cid");
	int flag=0;
	flag=cdao.CheckCid(cid);
	
	if(flag==1)
	{
		request.setAttribute("error", "Customer ID is already exist!");
		RequestDispatcher rd = request.getRequestDispatcher("DalCustomerAdd.jsp");
		rd.forward(request, response);
	}
	else{
		RequestDispatcher rd=request.getRequestDispatcher("DalCustomerAddServe");
		rd.forward(request, response);
	}

%>

</body>
</html>