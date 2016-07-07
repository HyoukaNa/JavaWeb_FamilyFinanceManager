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

import com.team30.dao.IncomeDao;
import com.team30.dao.UserDao;
import com.team30.model.Income;
import com.team30.model.User;
import com.team30.service.IncomeManager;
import com.team30.service.UserManager;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String service = request.getParameter("service");
		if(service.equals("search")) {
			String name = (String) request.getParameter("name");
			String idcard = (String) request.getParameter("id");
			//System.out.println("Name: "+ name + " Id: " + idcard);
			ArrayList<User> lists = UserManager.findUser(name, idcard);
			request.setAttribute("searchlists", lists);
			RequestDispatcher rd = request.getRequestDispatcher("/jsp/user.jsp");
			rd.forward(request, response);
		} else if(service.equals("modify")) {
			System.out.println("service = modify");
			PrintWriter out = response.getWriter();
			User aUser = new User();		
			if(IncomeDao.findIncomeByIncomeId(request.getParameter("md_ID")) == null ) {
				request.setAttribute("resultOfModify", "NotFound");
			} else {
				aUser.setUserName(request.getParameter("name"));
				aUser.setPassword(request.getParameter("password"));
				aUser.setCreditCard(request.getParameter("account"));
				aUser.setSalary(Integer.parseInt(request.getParameter("wage")));
				aUser.setFamilyCall(request.getParameter("call"));
				aUser.setAge(Integer.parseInt(request.getParameter("age")));
				aUser.setIdCard(request.getParameter("ID"));
				aUser.modifyUser(aUser);
				
				out.println("true");
			}
		} else if(service.equals("add")) {
			User aUser = new User();
			PrintWriter out = response.getWriter();
			
			aUser.setUserName(request.getParameter("name"));
			aUser.setPassword(request.getParameter("password"));
			aUser.setCreditCard(request.getParameter("account"));
			aUser.setSalary(Integer.parseInt(request.getParameter("wage")));
			aUser.setFamilyCall(request.getParameter("call"));
			aUser.setAge(Integer.parseInt(request.getParameter("age")));
			aUser.setIdCard(request.getParameter("ID"));
			try {
				aUser.addUser(aUser);
				out.print("true");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				out.print("添加失败！");
			}
		} else if(service.equals("delete")) {
			
		} else {
			
		}
	}

}
