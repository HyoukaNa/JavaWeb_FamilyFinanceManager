package com.team30.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team30.dao.SecuritiesItemDao;
import com.team30.model.Cost;
import com.team30.model.Income;
import com.team30.model.SecuritiesItem;
import com.team30.model.User;
import com.team30.service.ReportService;
 

public class ReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User user =(User)  session.getAttribute("currentUser");
		String userId = user.getIdCard();
		String date = request.getParameter("date");
		String type = request.getParameter("query_search");
		if(type.equals("query_in")){
			ArrayList<Income> incomeList=  ReportService.queryIncome(date,userId);
			request.setAttribute("incomeList",incomeList);
			
		 	ArrayList<SecuritiesItem> securitiesItemList= ReportService.queryItem(date,userId,2);
	         request.setAttribute("securitiesItemList",securitiesItemList);
		}else if(type.equals("query_out")){
			ArrayList<Cost> costList =  ReportService.queryCost(date,userId);
			request.setAttribute("costList", costList);	
			
			ArrayList<SecuritiesItem> securitiesItemList= ReportService.queryItem(date,userId,1);
	         request.setAttribute("securitiesItemList",securitiesItemList);
		}else{
			ArrayList<Cost> costList =  ReportService.queryCost(date,userId);
			request.setAttribute("costList", costList);
			
			ArrayList<SecuritiesItem> securitiesItemList= ReportService.queryItem(date,userId,3);
	         request.setAttribute("securitiesItemList",securitiesItemList);
	         
			ArrayList<Income> incomeList=  ReportService.queryIncome(date,userId);
			request.setAttribute("incomeList",incomeList);
			System.out.println("cost"+costList.size()+"secur"+securitiesItemList.size()+"income"+incomeList.size());
		}		
         RequestDispatcher rd=request.getRequestDispatcher("/jsp/report.jsp");
         rd.forward(request,response);
	}

}
