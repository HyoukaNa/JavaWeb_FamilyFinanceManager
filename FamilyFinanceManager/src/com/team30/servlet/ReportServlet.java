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

import com.team30.model.Income;
import com.team30.model.User;
 

public class ReportServlet extends HttpServlet {

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
		
		String date = request.getParameter("date");
		String type = request.getParameter("query_search");
		System.out.println(date);
		System.out.println(type);
//		if(type.equals("query_in")){
//			ReportService.queryIncome(date,new User().getIdCard());
//			ReportService.queryItem(date,new User().getIdCard(),1);
//		}else if(type.equals("query_out")){
//			ReportService.queryCost(date, new User().getIdCard());
//			ReportService.queryItem(date,new User().getIdCard(),2);
//		}else{
//			ReportService.queryCost(date,"");
//			ReportService.queryItem(date,new User().getIdCard(),3);
//			ReportService.queryCost(date,new User().getIdCard());
//		}
		User user=new User();
		user.setUserName("da");
		Income u=new Income();
		u.setIncomeCount(2);
		u.setIncomeDate("2015-1");
		u.setIncomeOwner(user);
		u.setIncomeSource("da");
		u.setIncomeType(2);
		ArrayList<Income> incomeList = new ArrayList<Income>();
		incomeList.add(u);
		request.setAttribute("incomeList", incomeList);
		RequestDispatcher rd = request.getRequestDispatcher("/html/report.jsp");
		rd.forward(request, response);
		
	}

}
