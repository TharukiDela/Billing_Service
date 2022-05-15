package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public class Billing  {
	
	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/bill", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
	
	
public String insertBill(String code, String name, String rate, String consumed,String charge)
{ 
	 String output = ""; 
	 
	try
	 { 
		Connection con = connect(); 
		
		 if (con == null) 
		 { 
			 return "Error while connecting to the database"; 
		 } 
	 
		 //insert
		 // create a prepared statement
		 String query = " insert into billinfo (`userID`,`userCode`,`userName`,`userRate`,`userConsumed`,`userCharge`)"
		 + " values (?, ?, ?, ?, ?,?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, 0); 
		 preparedStmt.setString(2, code); 
		 preparedStmt.setString(3, name); 
		 preparedStmt.setDouble(4, Double.parseDouble(rate)); 
		 preparedStmt.setString(5, consumed);
		 preparedStmt.setDouble(6, Double.parseDouble(charge));
		 
		//execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newBill = readBill(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}"; 
		 
		 
	} 
	
	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while inserting.\"}"; 
		 System.err.println(e.getMessage()); 
	 } 
	
	return output; 
}
	


	
public String readBill()
{ 
	
	 String output = "";
	 
	try
	 { 
		Connection con = connect(); 
		
		 if (con == null) 
		 { 
			 return "Error while connecting to the database for reading."; 
		 } 
		 
		 
		 // Prepare the html table to be displayed
		 
		 output = "<table border='1'><tr><th>user Code</th>" 
		 +"<th>user Name</th><th>user Rate</th>"
		 + "<th>user Consumed</th>" 
		 + "<th>user Charge</th>"
		 + "<th>Update</th><th>Remove</th></tr>";
		
		 
		 
		 String query = "select * from billinfo"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
			 String userID = Integer.toString(rs.getInt("userID")); 
			 String userCode = rs.getString("userCode"); 
			 String userName = rs.getString("userName"); 
			 String userRate = Double.toString(rs.getDouble("userRate")); 
			 String userConsumed = rs.getString("userConsumed"); 
			 String userCharge = Double.toString(rs.getDouble("userCharge")); 
		 
			 // Add a row into the html table
			 output += "<tr><td>" + userCode + "</td>"; 
			 output += "<td>" + userName + "</td>"; 
			 output += "<td>" + userRate + "</td>";
			 output += "<td>" + userConsumed + "</td>";
			 output += "<td>" + userCharge + "</td>"; 
		
		
			 // buttons
			 // buttons
			 output += "<td><input name='btnUpdate' type='button' value='Update'"
			 		+ "class='btnUpdate btn btn-secondary' data-userid='" + userID+ "'></td>"
				
				 + "<td><input name='btnRemove' type='button' value='Remove'"
				 + "class='btnRemove btn btn-danger' data-userid='" + userID + "'></td></tr>";
		 
		 } 
		 
		 con.close();
		 
		 // Complete the html table
		 output += "</table>"; 
	 } 
	catch (Exception e)
	 { 
		 output = "Error while reading the details."; 
		 System.err.println(e.getMessage()); 
	 }
	
	return output; 
	
}
	

	
public String deleteBill(String userID) 
{ 
	 String output = ""; 
	 
	try
	 { 
		Connection con = connect(); 
		
		 if (con == null) 
		 { 
			 return "Error while connecting to the database for deleting."; 
		 } 
		 
		 
		 // create a prepared statement
		 
		 String query = "delete from billinfo where userID=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(userID)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newBill = readBill();
		 
		 output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
	 } 
	
	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while deleting.\"}";
		 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	
public String updateBill(String ID,String code, String name, String rate, String consumed,String charge) 

{ 
		 String output = ""; 
		 
		 try
		 { 
			 Connection con = connect(); 
			 if (con == null) 
			 {return "Error while connecting to the database for updating."; } 
			 
			 // create a prepared statement
			 
			 String query = "UPDATE billinfo SET userCode=?,userName=?,userRate=?,userConsumed=?, userCharge=?  WHERE userID=?";
			
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			
			 preparedStmt.setString(1, code); 
			 preparedStmt.setString(2, name); 
			 preparedStmt.setDouble(3, Double.parseDouble(rate)); 
			 preparedStmt.setString(4, consumed);
			 preparedStmt.setDouble(5, Double.parseDouble(charge)); 
			 preparedStmt.setInt(6, Integer.parseInt(ID)); 
			
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newBill = readBill();
			 output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
			 }
		 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\": \"Error while updating.\"}";
			 System.err.println(e.getMessage());
			 
		 } 
		 return output; 
		}



}
