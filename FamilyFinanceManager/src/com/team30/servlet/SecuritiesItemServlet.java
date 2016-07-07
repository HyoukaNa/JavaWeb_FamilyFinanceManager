package com.team30.servlet;

import com.team30.dao.IncomeDao;
import com.team30.dao.SecuritiesItemDao;
import com.team30.dao.SecuritiesUserDao;
import com.team30.model.SecuritiesItem;
import com.team30.model.SecuritiesUser;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangxu on 16/7/5.
 */
public class SecuritiesItemServlet extends HttpServlet{

	/**
     * @see HttpServlet#HttpServlet()
     */
    public SecuritiesItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		;this.doPost(request, response);
	}


    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String service = request.getParameter("service");
        System.out.println( "securitiesItem service :" + service);

        SecuritiesItem securitiesItem = new SecuritiesItem();
        if(service.equals("query")){
            String securitiesUser =(String) request.getParameter("secuser");
            System.out.println(securitiesUser);
            String startDate =(String) request.getParameter("startDate");
            String endDate =(String) request.getParameter("endDate");
            String typeStr =(String) request.getParameter("type");
            int type = 0;
            if (typeStr.equals("buy")){
                type = 1;
            }else {
                type = 2;
            }
            ArrayList<SecuritiesItem> securitiesItemList = SecuritiesItemDao.findItemListWithDateAndItemIDAndType(startDate,endDate,securitiesUser,type);
           System.out.println(securitiesItemList.size());
            request.setAttribute("securitiesItemList",securitiesItemList);
            RequestDispatcher rd=request.getRequestDispatcher("/jsp/securitiesItem.jsp");
            rd.forward(request,response);
        }else if (service.equals("modify")){
            SecuritiesUser user = SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(request.getParameter("account"));
            PrintWriter out = response.getWriter();
            if (user == null){
                out.print("未找到该证券账户");
            }else {
                int type = 0;
                if (request.getParameter("type").equals("买入")){
                    type = 1;
                }else {
                    type = 2;
                }
                System.out.println(securitiesItem.getItemStockPrice());
                securitiesItem.setItemId(request.getParameter("ID"));
                securitiesItem.setItemOwner(user);
                securitiesItem.setItemStockCount(Integer.parseInt(request.getParameter("count")));
                securitiesItem.setItemStockName(request.getParameter("stock"));
                securitiesItem.setItemStockPrice(Integer.parseInt(request.getParameter("price")));
                securitiesItem.setItemTradeDate(request.getParameter("date"));
                securitiesItem.setItemTradeType(type);
                System.out.println(securitiesItem.getItemStockPrice());
                SecuritiesItemDao.modifySecuritiesItem(securitiesItem);
                out.print("true");

            }
        }else if (service.equals("add")){
        	System.out.println(request.getParameter("ID"));
            SecuritiesUser user = SecuritiesUserDao.findSecuritiesUserBySecuritiesUserID(request.getParameter("account"));
            SecuritiesItem item = SecuritiesItemDao.findSecuritiesItemByItemId(request.getParameter("ID"));
            System.out.println(item);
            PrintWriter out = response.getWriter();
            if (user == null){
                out.print("该证券账户不存在");
            }else if (item.getItemId()  != null){
                out.print("该流水账已存在");
            }else {
                int type = 0;
                if (request.getParameter("type").equals("买入")){
                    type = 1;
                }else {
                    type = 2;
                }
                securitiesItem.setItemId(request.getParameter("ID"));
                securitiesItem.setItemOwner(user);
                securitiesItem.setItemStockCount(Integer.parseInt(request.getParameter("count")));
                securitiesItem.setItemStockName(request.getParameter("stock"));
                securitiesItem.setItemStockPrice(Integer.parseInt(request.getParameter("price")));
                securitiesItem.setItemTradeDate(request.getParameter("date"));
                securitiesItem.setItemTradeType(type);
                try {
                    SecuritiesItemDao.addSecuritiesItem(securitiesItem);
                    out.print("true");
                }catch (SQLException e){
                    System.out.println("error");
                }
            }
        }else if(service.equals("delete")){
        	String [] str = new String[1];
			str[0] = (String) request.getParameter("id");
			//System.out.print("Delete Id = " + str[0]);
			SecuritiesItemDao.deleteSecuritiesItem(str);
			PrintWriter out = response.getWriter();
			out.print("true");
        }
    }
}
