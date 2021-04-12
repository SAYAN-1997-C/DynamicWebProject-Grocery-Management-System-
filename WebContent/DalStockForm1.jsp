<%@page import="master.DAO.DalMahajanDAO"%>
<%@ page import="java.util.*"%>
<%@page import="master.utilities.ConnectionFactory"%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Form1</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainDal">
		<form class="box" action="DalStockForm1CheckMidDate" method="post">

			<span style="color: red; font-size: 15px">
		    <%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>


			<input class="date" type="date" name="sdate" placeholder="Date"
				required="required">
			<%
				DalMahajanDAO obj=new DalMahajanDAO();
				ArrayList arr = new ArrayList();

				arr = obj.getMid();
				Iterator itr = arr.iterator();
				
			%>
			<select id="rice_id" name="mid" required="required">
				<%
					while (itr.hasNext()) {
						
						String x=(String) itr.next();
				%>
				<option value="<%=x%>"><%=x%></option>
				<%
					}
				%>
			</select> <input type="submit" value="PROCEED">
		</form>
	</div>
</body>
</html>