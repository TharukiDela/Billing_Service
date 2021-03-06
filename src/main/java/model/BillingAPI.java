package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Servlet implementation class BillingAPI
 */
@WebServlet("/BillingAPI")
public class BillingAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;

    Billing billObj = new Billing();
    
    public BillingAPI() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String output = billObj.insertBill(
				request.getParameter("userCode"), 	
				request.getParameter("userName"),
				request.getParameter("userRate"),
				request.getParameter("userConsumed"), 
				request.getParameter("userCharge")); 
				
		
		response.getWriter().write(output);
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		Map paras = getParasMap(request); 
		
		String output = billObj.updateBill(
				paras.get("hidBillIDSave").toString(), 
				paras.get("userCode").toString(), 
				paras.get("userName").toString(),
				paras.get("userRate").toString(),
				paras.get("userConsumed").toString(), 
				paras.get("userCharge").toString(), 
				
			    
		response.getWriter().write(output); 
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 String output = billObj.deleteBill(paras.get("userID").toString()); 
		response.getWriter().write(output); 
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request) 
		{ 
			Map<String, String> map = new HashMap<String, String>(); 
			
			try
			 { 
				 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
				 String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : ""; 
				 scanner.close(); 
				 
				 String[] params = queryString.split("&");
				 
				 for (String param : params) 
				 { 
				 
					 String[] p = param.split("="); 
					 map.put(p[0], p[1]); 
				 } 
			 } 
			catch (Exception e) 
			 { 
			 } 
			return map; 
			}

}
