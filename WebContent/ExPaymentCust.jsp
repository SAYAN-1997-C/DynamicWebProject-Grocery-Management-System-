<%@page import="master.DAO.CustomerDAO"%>
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
<div class="main">
		<form class="box" action="ExPaymentCheckDateCID" method="post" autocomplete="off">
		<span style="color: red; font-size: 15px">
		<%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
		<table>
			<tr>
				<td><span>Date :</span></td>
				<td><input class="date" type="date" name="cdate" placeholder="Date" required="required"></td>
			</tr>
			<tr>
				<td><span>Customer ID :</span></td>
				<td>
					<%
				CustomerDAO obj=new CustomerDAO();
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
				</td>
			</tr>
			<tr>
				<td><span>Total Amount :</span></td>
				<td><input type="text" name="total_bill" placeholder="Total Bill" value="0"></td>
			</tr>
			<tr>
				<td><span>Payment :</span></td>
				<td><input type="text" name="payment" placeholder="Payment of Customer" required="required"></td>
			</tr>
			<tr>
				<td><span>Remain Due :</span></td>
				<td><input type="text" name="due" placeholder="Due" value="0"> </td>
			</tr>
		</table>   
			<input	type="submit" value="SUBMIT AND EXIT"> 
		</form>
		</div>
</body>
</html>