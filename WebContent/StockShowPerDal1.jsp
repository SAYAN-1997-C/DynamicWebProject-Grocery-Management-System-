<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/AllDalOutput.css">
</head>
<body>
		<div class="navbar">
			<a href="DalWelcomeStock.jsp" class="logo">STOCK DETAILS</a>
	        <ul class="menu">
	            <li><a href="HomeDal.jsp" class="active">Home</a></li>
	        </ul>
		</div>
		<div class="box" >
			<table>
				<tr>
					<td class="col">DAL ID</td>
					<td class="col">QUANTITY</td>
				</tr>
				<%	
					String dal_id=request.getParameter("dal_id");	
				
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_stockRice="select * from stock_dal where dal_id=?";
					
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					
					try{
					ps=cn.prepareStatement(select_stockRice);
					ps.setString(1, dal_id);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<tr>
					<td ><%=rs.getString(1)%></td>
					<td ><%=rs.getString(2) %></td>
					</tr>
				<% } 
					}
				catch(SQLException se){
					se.printStackTrace();
					}
				obj.releaseConnectionSourcePS(rs, ps, cn);
					%>
			</table>	
			<div class="goHome">
				<a href="DalWelcomeStock.jsp"><button>Go Back &#x276F;</button></a>
			</div>
		</div>
		<div class="footer">
		  <p>&#xa9; Designed by: Souvik Dutta & Sayan Jashu</p>
		</div>
</body>
</html>