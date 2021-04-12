<%@page import="master.DAO.StockDalDAO"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/Log.css">
<script type="text/javascript">
	
</script>
</head>
<body>
	<div class="mainDal">
		<form class="box"  action="CashOrderForm2CheckDaId" method="post" autocomplete="off">
		<%
			String cash_date=request.getParameter("cash_date");
			String billno=request.getParameter("billno");
		%>
		<span style="color:red;font-size: 15px">
		<%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
		<span style="color:green;font-size: 15px">
		<%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
		<table>
			<tr>
				<td>Date :</td>
				<td><input type="text" name="cash_date" value="<%=cash_date %>"></td>
			</tr>
			<tr>
				<td>Bill No :</td>
				<td><input type="text" name="billno" value="<%=billno %>"></td>
			</tr>
			<tr>
					<td><span>DAL ID :</span></td>
					<td><%
						StockDalDAO obj=new StockDalDAO();
						ArrayList arr=new ArrayList();
						arr=obj.getDalId();
						Iterator itr=arr.iterator();
							%>
							<select id="rice_id" name="dal_id"><%
							while(itr.hasNext()){
								String x=(String) itr.next();
							%>
						          <option value="<%=x %>"><%=x %></option>
							<%}%> </select>
					</td>
			</tr>
			<tr>
				<td>Quantity(No. of Bag):</td>
				<td><input type="text" name="quan" placeholder="No Of Bags" id="nofbag" required="required"></td>
			</tr>
			<tr>
				<td>Price :</td>
				<td><input type="text" name="price" placeholder="price" id="price" onclick="priceCal();" required="required"></td>
			</tr>
		</table>   
			<input	type="submit" value="ADD"> 
		</form>
		
		<form class="box2" action="DalCashOrderForm3.jsp" method="post">
			<input type="text" name="cash_date" value="<%=cash_date %>">
			<input type="text" name="billno" value="<%=billno %>">
			<input	type="submit" value="BILL INSERT"> 
		</form>
		
	</div>
</body>
</html>