<%@page import="master.DAO.DalCustomerDAO"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body> 
	<div class="mainDal">
		<form class="box" action="DalOutputCustPerDate.jsp" method="post">
		<input class="date" type="date" name="cdate" placeholder="Date" required="required">
			<%
			DalCustomerDAO obj=new DalCustomerDAO();
			ArrayList arr=new ArrayList();
			arr=obj.getCid();
			Iterator itr=arr.iterator();
				%>
				<select id="rice_id" name="cid"><%
				while(itr.hasNext()){
					String x=(String) itr.next();
				%>
			          <option value="<%=x %>"><%=x %></option>
				<%}%> </select>
		 <input	type="submit" value="PROCEED">
		</form>
	</div>
</body>
</html>
