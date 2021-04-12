<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Homepage</title>
<link rel="stylesheet" href="css/home1.css">
</head>
<body>
	<div class="mainDal">
        <div class="nav_bar">
            <a href="HomeMain.jsp" class="home"><font color="black">Dalpage</font></a>
            <div class="links">
                <a href="DalWelcomeMahajan.jsp">Mahajan</a>
                <div class="dropdown">
                    <a href="#" dropbtn>Customers</a>
                    <div class="dropdown-content">
                        <a href="DalWelcomeDueCust.jsp">Due Customers</a>
                        <a href="DalWelcomeCash.jsp">Cash Customers</a>
                    </div>
                </div>
                <a href="DalWelcomeStock.jsp">Stocks</a>
            </div>
            <div class="logout">
                <a href="HomeMain.jsp">Home</a>
            </div>
        </div>
        <div class="details">
            <span><h1>Welcome Admin !</h1></span>
            <p>It is the primary tool for you to work with your online store. Here you can manage products and orders, stocks, interact with your lenders, track transactions, generate bills and do much more.</p>
            <a href="AllDalDetailsOutput.jsp">Check Stocks</a>
        </div>
    </div>
</body>
</html>