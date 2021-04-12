<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
<meta charset="utf-8">
<title>Mahajan Page</title>
<link rel="stylesheet" href="css/mahajan.css">
</head>
<body>
	<div class="mainDal">
        <div class="home">
            <span>Mahajan</span>
            
            <span style="color: green; font-size: 15px"> <%=(request.getAttribute("success") == null) ? "" : request.getAttribute("success")%></span>
			<span style="color: green; font-size: 15px"> <%=(request.getAttribute("done") == null) ? "" : request.getAttribute("done")%></span>
            
            <a href="HomeDal.jsp">Home</a>
        </div>
        <div class="box">
            <div class="container">
                <a href="DalStockForm1.jsp"><img src="images/mahajan/stockIn.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalStockForm1.jsp">New Stock In</a>
                </div>
            </div>
            <div class="container">
                <a href="DalViewPerDateForm1.jsp"><img src="images/mahajan/view2.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalViewPerDateForm1.jsp">View/Edit Stock In Bills</a>
                </div>
            </div>
            <div class="container">
                <a href="DalDatewiseTotalStockIn.jsp"><img src="images/mahajan/allStockIn.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalDatewiseTotalStockIn.jsp">Check All Stock In</a>
                </div>
            </div>
            <div class="container">
                <a href="DalMdetailsInputForm.jsp"><img src="images/mahajan/viewMahajan.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalMdetailsInputForm.jsp">View Mahajan Details</a>
                </div>
            </div>
            <div class="container">
                <a href="DalExPaymentForm.jsp"><img src="images/mahajan/payment.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalExPaymentForm.jsp">Payment to Mahajan</a>
                </div>
            </div>
            <div class="container">
                <a href="DalMahajanAdd.jsp"><img src="images/mahajan/addNew.jpg" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalMahajanAdd.jsp">Add Mahajan</a>
                </div>
            </div>
            <div class="container">
                <a href="DalMidDeleteInputForm.jsp"><img src="images/mahajan/delete.png" alt="image" height="100px"></a>
                <div class="container-details">
                    <a href="DalMidDeleteInputForm.jsp">Delete Mahajan</a>
                </div>
            </div>
        </div>
        <div class="footer">
            <span>&#169; Designed By: Souvik Dutta & Sayan Jashu</span>
        </div>
    </div>
</body>
</html>