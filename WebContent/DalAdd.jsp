<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rice Entry</title>
<link rel="stylesheet" href="css/Log.css">
</head>
<body>
	<div class="mainDal">
		<form class="box" action="DalAddCheckDalId" method="post" autocomplete="on">
			<h1>NEW DAL ENTRY</h1>
			<span style="color:red;font-size: 15px"><%=(request.getAttribute("error") == null) ? "" : request.getAttribute("error")%></span>
			<span style="color:green;font-size: 15px"><%=(request.getAttribute("saved") == null) ? "" : request.getAttribute("saved")%></span>
			<table>
				<tr>
					<td><label>DAL ID :</label></td>
					<td><input type="text" name="dal_id" placeholder="Rice ID" required="required"></td>
				</tr> 
				 <tr>
					 <td><label>QUANTITY :</label></td>
					 <td><input type="text" name="quan" placeholder="Number of Bag" required="required"></td>
				 </tr>
				</table>	
				<input type="submit" value="SAVE">
				<a href="DalWelcomeStock.jsp">Home</a>
			
		</form>
	</div>
</body>
</html>