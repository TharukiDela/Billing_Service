<%@ page import="model.Billing"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="View/bootstrap.min.css">
<script type="Components/jquery-3.6.0.min.js"></script>
<script type="Components/Billing.js"></script>
</head>
<body>
<body>

<div class="container"><div class="row"><div class="col-6">
	<h1>Bill Management</h1>

		<form id = "formBill" name="formBill" method="post" action="Bill.jsp">
			 User Code: <input id="userCode"name="userCode" type="text"
			 			class = "form-control form-control-sm" ><br>
			 				
			 Username: <input id="userName" name="userName" type="text" 
			 			class = "form-control form-control-sm"><br>
			 			
			User Rate: <input id="userRate" name="userRate" type="text" 
			 		class = "form-control form-control-sm"><br>
			 		
			 Consumed: <input id="userConsumed"  name="userConsumed" type="text" 
			 		class = "form-control form-control-sm"><br>
			 		
			 User Charge: <input id="userCharge" name="userCharge" type="text" 
			 		class = "form-control form-control-sm"><br>
			 		
			
			 
			 <input id="btnSave" name="btnSave" type="button" value="Save"
			 		class = "btn btn-primary">
			 		
			 		
			 <input type="hidden" id="hidBillIDSave" name="hidBillIDSave" value="">		
		</form>
		
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	<br>
	
	<div id="divBillGrid">

		<%
		 Billing billObj = new Billing(); 
		 out.print(billObj.readBill()); 
		%>
	</div>
</div> </div> </div> 
		
</body>
</html>