<%@page import="master.DAO.DalMahajanDAO"%>
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
		<form class="box" action="DalMahajanBillCheckServe" method="post">
		<span style="color: red; font-size: 15px"> <%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			<input type="date" class="date" name="sdate" placeholder="Date" required="required">
			<%
				DalMahajanDAO obj=new DalMahajanDAO();
				ArrayList arr=new ArrayList();
				arr=obj.getMid();
				Iterator itr=arr.iterator();
				%>
				<select id="rice_id" name="mid"><%
				while(itr.hasNext()){
					
					String x=(String) itr.next();
				%>
			          <option value="<%=x %>"><%=x %></option>
				<%}%> </select>
			 <input	type="submit" value="DELETE">
		</form>
	</div>
</body>
</html>