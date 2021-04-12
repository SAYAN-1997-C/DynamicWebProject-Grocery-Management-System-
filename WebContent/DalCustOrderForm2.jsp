<%@page import="master.DAO.DalCustomerDAO"%>
<%@page import="master.DAO.StockDalDAO"%>
<%@ page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/Log.css">
<script type="text/javascript">
		
</script>
</head>
<body>
	<div class="mainDal">
		<form class="box"  action="CustOrderForm2CheckDalId" method="post" autocomplete="off">
		<%
			String cdate=request.getParameter("cdate");
			String cid=request.getParameter("cid");
			
			DalCustomerDAO cdao=new DalCustomerDAO();
			String name=cdao.getCname(cid);
		%>
		<span style="color:red;font-size: 15px">
		<%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
		<span style="color:green;font-size: 15px">
		<%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
		<table>
			<tr>
				<td>Date :</td>
				<td><input type="text" name="cdate" value="<%=cdate %>"></td>
			</tr>
			<tr>
				<td>Customer ID :</td>
				<td><input type="text" name="cid" value="<%=cid %>"></td>
			</tr>
			<tr>
					<td><span>Dal ID :</span></td>
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
				<td>Quantity(No. of Bag) :</td>
				<td><input type="text" name="quan" placeholder="No Of Bags" id="nofbag" required="required"></td>
			</tr>
			<tr>
				<td>Price :</td>
				<td><input type="text" name="price" placeholder="price" id="price" onclick="priceCal();" required="required"></td>
			</tr>
		</table>   
			<input	type="submit" value="ADD"> 
		</form>
		
		<form class="box2" action="DalCustOrderForm3.jsp" method="post">
			<input type="text" value="<%=name %>">
			<input type="text" name="cdate" value="<%=cdate %>">
			<input type="text" name="cid" value="<%=cid %>">
			<input	type="submit" value="BILL INSERT"> 
		</form>
		
	</div>
</body>
</html>