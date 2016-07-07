package com.team30.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team30.dao.UserDao;
import com.team30.model.User;

/**
 * Servlet implementation class ChangePassServlet
 */
@WebServlet("/ChangePassServlet")
public class ChangePassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassServlet() {
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
		// TODO Auto-generated method stub
		System.out.println("ChangePass.doPost()");
		request.setCharacterEncoding("UTF-8");
		User aUser = UserDao.findUserByIdCard("47839783783783");
		HttpSession session = request.getSession();
		aUser = (User) session.getAttribute("currentUser");
		String newPassword = (String) request.getParameter("newPassword");
		String rePassword = (String) request.getParameter("rePassword");
		System.out.println("newPassword = " + newPassword + "    rePassword = "+ rePassword);
		if( newPassword != null  && rePassword != null &&  newPassword.equals(rePassword)  ) {
			System.out.println("newPassword == rePassword");
			request.setAttribute("resultOfChangePass", "success");
			aUser.setPassword(newPassword);
			aUser.show();
			aUser.modifyUser(aUser);
			aUser.show();
		} else if( newPassword != null  && rePassword != null &&  !newPassword.equals(rePassword) ) {
			System.out.println("newPassword != rePassword");
			request.setAttribute("resultOfChangePass", "fail");
		} else {
			System.out.println("NULL");
			request.setAttribute("resultOfChangePass", "empty");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/changePass.jsp");
		rd.forward(request, response);
		
	}

}
