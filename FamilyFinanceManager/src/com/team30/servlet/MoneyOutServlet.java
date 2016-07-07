package com.team30.servlet;

import java.io.IOException;
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

import com.team30.dao.CostDao;
import com.team30.dao.UserDao;
import com.team30.model.Cost;
import com.team30.service.CostManager;

/**
 * Servlet implementation class MoneyOutServlet
 */
@WebServlet("/MoneyOutServlet")
public class MoneyOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MoneyOutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String service = request.getParameter("service");
		System.out.println( "Cost service :" + service);
		if(service.equals("search")) {
			String costSearchName = (String) request.getParameter("form1-name");
			String costSearchDate = (String) request.getParameter("form1-date");
			ArrayList<Cost> lists = CostManager.findCost(costSearchName, costSearchDate);
			request.setAttribute("searchlists", lists);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/moneyOut.jsp");
			rd.forward(request, response);
		} else if (service.equals("modify")) {
			PrintWriter out = response.getWriter();
			Cost aCost = new Cost();	
			if(CostDao.findCostByCostId(request.getParameter("ID")) == null ) {
				out.print("未找到");
			} else {
				aCost.setCostId(request.getParameter("ID"));
				aCost.setCostType(Integer.parseInt(request.getParameter("type")));
				aCost.setCostOwner(UserDao.findUserByUserName(request.getParameter("name")));
				aCost.setCostCount(Integer.parseInt(request.getParameter("num")));
				aCost.setCostDate(request.getParameter("date"));
				aCost.setCostSource(request.getParameter("source"));
				aCost.setCostDetails(request.getParameter("remark"));
				aCost.show();
				CostManager.modifyCost(aCost);
				out.print("true");
			}
		} else if (service.equals("add")) {
			System.out.println("has entered ...");
			Cost aCost = new Cost();
			PrintWriter out = response.getWriter();

			Random rand = new Random();
			String id;
			for(;true;) {
				id = String.valueOf(1000 + rand.nextInt(8999));
				if(CostDao.findCostByCostId(id) == null)
					break;
			}			
			
			aCost.setCostId(id);
			aCost.setCostType(Integer.parseInt(request.getParameter("type")));
			aCost.setCostOwner(UserDao.findUserByUserName(request.getParameter("name")));
			aCost.setCostCount(Integer.parseInt(request.getParameter("num")));
			aCost.setCostDate(request.getParameter("date"));
			aCost.setCostSource(request.getParameter("source"));
			aCost.setCostDetails(request.getParameter("remark"));
			try {
				aCost.addCost(aCost);
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
			CostDao.deleteCosts(str);
			PrintWriter out = response.getWriter();
			out.print("true");
		} else {
			
		}
		
	}
}


