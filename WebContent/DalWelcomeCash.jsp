<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cash Customers</title>
<link rel="stylesheet" href="css/mahajan.css">
</head>
<body>
	<div class="mainDal">
        <div class="home">
            <span>Cash Customer</span>
            <span style="color:green;font-size: 15px">
		    <%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
            <a href="HomeDal.jsp">Home</a>
        </div>
        <div class="box">
            <div class="container">
                <a href="DalCashOrderForm1.jsp"><img src="images/mahajan/cashBill.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalCashOrderForm1.jsp">Cash Bill Entry</a>
                </div>
            </div>
            <div class="container">
                <a href="DalCashBillPerDateInput.jsp"><img src="images/mahajan/view2.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalCashBillPerDateInput.jsp">View Bill</a>
                </div>
            </div>
            <div class="container">
                <a href="DalDatewiseBagCash.jsp"><img src="images/mahajan/AllStock.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalDatewiseBagCash.jsp">Total Cash Sell</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <span>&#169; Designed By: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>