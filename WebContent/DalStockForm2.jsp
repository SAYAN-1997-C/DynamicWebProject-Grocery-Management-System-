<%@page import="master.DAO.DalMahajanDAO"%>
<%@page import="master.DAO.StockDalDAO"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Login Page</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainDal">
		<form class="box" action="StockForm2CheckDalId" method="post"
			autocomplete="off">
			<%
				String sdate = request.getParameter("sdate");
				String mid = request.getParameter("mid");
				
				DalMahajanDAO mdao=new DalMahajanDAO();
				String name=mdao.getMname(mid);
			%>
			<span style="color: red; font-size: 15px"> <%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			<span style="color: green; font-size: 15px"> <%=(request.getAttribute("success") == null) ? "" : request.getAttribute("success")%></span>
			<table>
				<tr>
					<td><span>Date :</span></td>
					<td><input type="text" name="sdate" value="<%=sdate%>"></td>
				</tr>
				<tr>
					<td><span>Mahajan ID :</span></td>
					<td><input type="text" name="mid" value="<%=mid%>"></td>
				</tr>
				<tr>
					<td><span>Dal ID :</span></td>
					<td>
						<%
							StockDalDAO obj=new StockDalDAO();
							ArrayList arr = new ArrayList();
							arr = obj.getDalId();
							Iterator itr = arr.iterator();
						%> <select id="rice_id" name="dal_id">
							<%
								while (itr.hasNext()) {
									String x=(String) itr.next();
							%>
							<option value="<%=x%>"><%=x%></option>
							<%
								}
							%>
					</select>
					</td>
				</tr>
				<tr>
					<td><span>Quantity(No. of bags) :</span></td>
					<td><input type="text" name="quan" placeholder="No Of Bags" required="required"></td>
				</tr>
				<tr>
					<td><span>Price :</span></td>
					<td><input type="text" name="price" placeholder="price" required="required"></td>
				</tr>
			</table>
			<input type="submit" value="ADD">
		</form>

		<form class="box2" action="DalStockForm3.jsp" method="post">
			<input type="text" value="<%=name%>">
			<input type="text" name="sdate" value="<%=sdate%>"> <input
				type="text" name="mid" value="<%=mid%>"> <input
				type="submit" value="BILL INSERT">
		</form>

	</div>
</body>
</html>