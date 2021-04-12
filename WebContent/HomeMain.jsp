<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<link rel="stylesheet" href="css/home.css">
</head>
<body>
	<div class="main">
        <div class="nav_bar">
            <a href="HomeMain.jsp" class="home">Homepage</a>
            <div class="links">
                <a href="Home.jsp">Rice Management</a>
                <a href="HomeOil.jsp">Oil Management</a>
                <a href="HomeDal.jsp">Dal Management</a>
            </div>
            <form class="logout" action="Logout" method="post">
				<input type="submit" value="Logout">
		    </form>
        </div>
        <div class="details">
            <span><h1>Welcome Admin !</h1></span>
            <p>It is the primary tool for you to work with your online store. Here you can manage products and orders, stocks, interact with your lenders, track transactions, generate bills and do much more.</p>
        </div>
    </div>
</body>
</html>