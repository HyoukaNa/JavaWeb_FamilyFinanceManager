package com.team30.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team30.model.User;

/**
 * Servlet implementation class userManagerServlet
 */
@WebServlet("/userManagerServlet")
public class UserManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); 
	}
	
	public void init() throws ServletException {
		super.init();
		System.out.println("init is doing");
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
		//System.out.println("doPost is doing");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		User aUser = (User) session.getAttribute("currentUser");
		aUser.setUserName(request.getParameter("name"));
		aUser.setCreditCard(request.getParameter("creditId"));
		aUser.setSalary(Integer.parseInt(request.getParameter("salary")));
		aUser.setFamilyCall(request.getParameter("familyCall"));
		aUser.setAge(Integer.parseInt(request.getParameter("age")));
		aUser.setIdCard(request.getParameter("idCard"));
		aUser.setAdmin(false);
		aUser.show();
		aUser.modifyUser(aUser);
		//UserDao.modifyUser(aUser);
		
		//HttpSession session = request.getSession();  
		//session.setAttribute("currentUser", aUser);
		//.setAttribute(userIDKey, userID);
		
		request.setAttribute("resultOfModify", "success");
		RequestDispatcher rd = request.getRequestDispatcher("/FamilyFinanceManager/jsp/userManager.jsp");
		rd.forward(request, response);
		
	}

}
