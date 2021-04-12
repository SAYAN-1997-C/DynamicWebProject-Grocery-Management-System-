<%@page import="master.utilities.ConnectionFactory"%>
<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/dalBill_info.css">
</head>
<body>
	<div class="main">
		<div class="box" >
		<center>
			<table >
				<%	
					String cash_date=request.getParameter("cash_date");%>
					<tr>
						<td class="col">DATE</td>
						<td class="col">DAL ID</td>
						<td class="col">Quantity(TOTAL NO OF BAG)</td>
					</tr>
					<tr>
					<%
					PreparedStatement ps=null;
					ResultSet rs=null;
					String select_Sumbag="select cash_date,dal_id,sum(quantity) from cash_dal group by cash_date,dal_id having cash_date=? order by dal_id";
					String select_SumBill="select sum(total_bill) from dalcash_bill where cash_date=?";
					String select_TotalBag="select sum(quantity) from cash_dal where cash_date=?";
							
					ConnectionFactory obj=new ConnectionFactory();
					Connection cn=obj.getCon();
					
					try{
					ps=cn.prepareStatement(select_Sumbag);
					ps.setString(1, cash_date);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<td ><%=rs.getString(1)%></td>
					<td ><%=rs.getString(2)%></td>
					<td ><%=rs.getString(3)%></td>
					</tr>
					
				<% } 
					
					ps=cn.prepareStatement(select_TotalBag);
					ps.setString(1, cash_date);
					rs=ps.executeQuery();
					if(rs.next()){
				%>
					<tr>
					<td ></td>
					<td ><font color="red" size="5">Total bag :</font></td>
					<td ><font color="blue" size="5"><%=rs.getString(1)%>pcs</font></td>
					</tr>
					
				<% } %>
				</table>
					<br>
					<br>
				<% 
	
					ps=cn.prepareStatement(select_SumBill);
					ps.setString(1, cash_date);
					rs=ps.executeQuery();
					while(rs.next()){
				%>
					<h1>Total cash Amount : &#8377;<%=rs.getString(1)%></h1>
				<% }
					
					}
				catch(SQLException se){
					se.printStackTrace();
					}
				obj.releaseConnectionSourcePS(rs, ps, cn);
					%>
			<br>
			<br>
			<br>
			<a href="DalWelcomeCash.jsp">click Home</a>
			</center>
		</div>
	</div>
</body>
</html>