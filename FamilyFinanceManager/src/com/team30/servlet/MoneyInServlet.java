package com.team30.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team30.dao.IncomeDao;
import com.team30.dao.UserDao;
import com.team30.model.Income;
import com.team30.service.IncomeManager;

/**
 * Servlet implementation class MoneyIn
 */
@WebServlet("/MoneyIn")
public class MoneyInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoneyInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//this.doPost(request, response);
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/moneyIn.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String service = request.getParameter("service");
		System.out.println( "Income service :" + service);
		if(service.equals("search")) {
			String incomeSearchName = (String) request.getParameter("form1-name");
			String incomeSearchDate = (String) request.getParameter("form1-date");
			ArrayList<Income> lists = IncomeManager.findIncome(incomeSearchName, incomeSearchDate);
			request.setAttribute("searchlists", lists);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/moneyIn.jsp");
			rd.forward(request, response);
		} else if (service.equals("modify")) {
			PrintWriter out = response.getWriter();
			Income aIncome = new Income();	
			if(IncomeDao.findIncomeByIncomeId(request.getParameter("ID")) == null ) {
				out.print("未找到");
			} else {
				aIncome.setIncomeId(request.getParameter("ID"));
				aIncome.setIncomeType(Integer.parseInt(request.getParameter("type")));
				aIncome.setIncomeOwner(UserDao.findUserByUserName(request.getParameter("name")));
				aIncome.setIncomeCount(Integer.parseInt(request.getParameter("num")));
				aIncome.setIncomeDate(request.getParameter("date"));
				aIncome.setIncomeSource(request.getParameter("source"));
				aIncome.setIncomeDetails(request.getParameter("remark"));
				aIncome.show();
				IncomeManager.modifyIncome(aIncome);
				out.print("true");
			}
		} else if (service.equals("add")) {
			System.out.println("has entered ...");
			Income aIncome = new Income();
			PrintWriter out = response.getWriter();

			Random rand = new Random();
			String id;
			for(;true;) {
				id = String.valueOf(1000 + rand.nextInt(8999));
				if(IncomeDao.findIncomeByIncomeId(id) == null)
					break;
			}			
			
			aIncome.setIncomeId(id);
			aIncome.setIncomeType(Integer.parseInt(request.getParameter("type")));
			aIncome.setIncomeOwner(UserDao.findUserByUserName(request.getParameter("name")));
			aIncome.setIncomeCount(Integer.parseInt(request.getParameter("num")));
			aIncome.setIncomeDate(request.getParameter("date"));
			aIncome.setIncomeSource(request.getParameter("source"));
			aIncome.setIncomeDetails(request.getParameter("remark"));
			try {
				aIncome.addIncome(aIncome);
				out.print("true");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("发生错误");
			}
		
			
		} else if (service.equals("delete")) {
			String [] str = new String[1];
			str[0] = (String) request.getParameter("id");
			//System.out.print("Delete Id = " + str[0]);
			IncomeDao.deleteIncomes(str);
			PrintWriter out = response.getWriter();
			out.print("true");
		} else {
			
		}
		
	}

}
