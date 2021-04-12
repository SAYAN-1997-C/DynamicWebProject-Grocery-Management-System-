<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Stock Details</title>
<link rel="stylesheet" href="css/AllDalOutput.css">
</head>
<body>
		<div class="navbar">
			<a href="DalWelcomeStock.jsp" class="logo">STOCK DETAILS (DAL)</a>
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
	
					Statement st=null;
					ResultSet rs=null;
					String select_StockRice="select * from stock_dal order by dal_id";
					
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					
					try{
					st=cn.createStatement();
					rs=st.executeQuery(select_StockRice);
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
					obj.releaseConnectionSourceST(rs, st, cn);
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