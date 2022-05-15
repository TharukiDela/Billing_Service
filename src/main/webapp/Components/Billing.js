$(document).ready(function() 
{ 
 
	 $("#alertSuccess").hide(); 
	  
	 $("#alertError").hide(); 
}); 


// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
		// Clear alerts---------------------
		 $("#alertSuccess").text(""); 
		 $("#alertSuccess").hide(); 
		 $("#alertError").text(""); 
		 $("#alertError").hide(); 
	
	
		// Form validation-------------------
		var status = validateBillForm(); 
		if (status != true) 
		 { 
			 $("#alertError").text(status); 
			 $("#alertError").show(); 
			 return; 
		 } 
	
	
		// If valid------------------------
		var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT"; 
		 $.ajax( 
		 { 
			 url : "BillingAPI", 
			 type : type, 
			 data : $("#formBill").serialize(), 
			 dataType : "text", 
			 complete : function(response, status) 
		 { 
		 	 onBillSaveComplete(response.responseText, status); 
		 } 
		 }); 
});

 
function onBillSaveComplete(response, status) 
{ 
		if (status == "success") 
		 { 
			 var resultSet = JSON.parse(response); 
		
			 if (resultSet.status.trim() == "success") 
			 { 
				 $("#alertSuccess").text("Successfully saved."); 
				 $("#alertSuccess").show(); 
				 $("#divBillGrid").html(resultSet.data); 
			
			 } else if (resultSet.status.trim() == "error") 
			 { 
				 $("#alertError").text(resultSet.data); 
				 $("#alertError").show(); 
			 } 
		
		} else if (status == "error") 
		{ 
			 $("#alertError").text("Error while saving."); 
			 $("#alertError").show();
		 
		 } else
		 { 
			 $("#alertError").text("Unknown error while saving.."); 
			 $("#alertError").show(); 
		 } 
		 
		 $("#hidBillIDSave").val(""); 
		 $("#formBill")[0].reset(); 
}



 
// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
	 $("#hidBillIDSave").val($(this).data(userID)); 
	 $("#userCode").val($(this).closest("tr").find('td:eq(0)').text()); 
	 $("#userName").val($(this).closest("tr").find('td:eq(1)').text()); 
	 $("#userRate").val($(this).closest("tr").find('td:eq(2)').text()); 
	 $("#userConsumed").val($(this).closest("tr").find('td:eq(3)').text()); 
	 $("#userCharge").val($(this).closest("tr").find('td:eq(3)').text()); 
	
}); 



$(document).on("click", ".btnRemove", function(event) 
{ 
		 $.ajax( 
		 { 
			 url : "BillingAPI", 
			 type : "DELETE", 
			 data : "userID=" + $(this).data("userid"),
			 dataType : "text", 
			 complete : function(response, status) 
		 { 
		 	 onBillDeleteComplete(response.responseText, status); 
		 } 
 	}); 
});



function onBillDeleteComplete(response, status) 
{ 
		if (status == "success") 
		 { 
			 var resultSet = JSON.parse(response); 
		
			 if (resultSet.status.trim() == "success") {
				 
					 $("#alertSuccess").text("Successfully deleted."); 
					 $("#alertSuccess").show(); 
					 $("#divBillGrid").html(resultSet.data); 
				
			 } else if (resultSet.status.trim() == "error") {
				 
					 $("#alertError").text(resultSet.data); 
					 $("#alertError").show(); 
			 } 
		
			 } else if (status == "error") { 
				
					 $("#alertError").text("Error while deleting."); 
					 $("#alertError").show(); 
				
			 } else{
				 
					 $("#alertError").text("Unknown error while deleting.."); 
					 $("#alertError").show(); 
 		} 
}

// CLIENT-MODEL================================================================
function validateItemForm() 
{ 
	// CODE
	if ($("#userCode").val().trim() == "") 
	 { 
	 return "Please Insert User Code."; 
	 } 

	// NAME
	if ($("#userName").val().trim() == "") 
	 { 
	 return "Please Insert User Name."; 
	 }

	// Rate-------------------------------
	if ($("#userRate").val().trim() == "") 
	 { 
	 return "Please Insert Rate."; 
	 } 

	// Consumed-------------------------------
	if ($("#userConsumed").val().trim() == "") 
	 { 
	 return "Please Insert User Consumed."; 
	 } 

	// Charge------------------------------
	if ($("#userCharge").val().trim() == "") 
	 { 
	 return "Please Insert User Charge."; 
	 } 
	
	
return true; 
}

/**
 * 
 */