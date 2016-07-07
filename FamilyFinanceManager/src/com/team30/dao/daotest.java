package com.team30.dao;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.team30.model.*;
import com.team30.utils.DBUtils;

public class daotest {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		
		/*User aUser = new User("wangqi", "7474174", "1154451564894", 5000, 
				"5ү", 80, "47839783783783", true);*/
		
		User aUser = UserDao.findUserByUserName("王五");
		aUser.show();
		
		ArrayList<Cost> list = CostDao.findCostByCostOwnerName("王五");
		for(int i = 0;i < list.size();i ++) {
			list.get(i).show();
			System.out.println("---------  " + (i+1) + "  ---------");
		}
		
		/*
		aUser.show();
		
		String [] lists = new String[2];
		lists[0] = "56101265756715716";
		lists[1] = "56101265727716";
		
		UserDao.addUser(aUser);
		UserDao.deleteUsers(lists);
		
		ArrayList<User> lists = new ArrayList<User> ();
		lists = UserDao.findAllUserList();
		for(int i = 0;i < lists.size();i ++) {
			lists.get(i).show();
			System.out.println("---------  " + (i+1) + "  ---------");
		}
		
		aUser = UserDao.findUserByIdCard("47839783783783");
		aUser.show();
		
		aUser.show();
		System.out.println("-----------------------------------");
		aUser.setAge(100);
		UserDao.modifyUser(aUser);
		aUser.show();
		
		/*Income aIncome = new  Income("20160702003", 2, aUser,
				3000, "2016-07-02 15:14", "Bingo!!!",
				"gogoggogoogogogogogogo") ;*/
		//IncomeDao.addIncome(aIncome);
		
		//Income aIncome = IncomeDao.findIncomeByIncomeId("20140701001");
		//aIncome.show();
		/*aIncome.setIncomeCount(100000);
		IncomeDao.modifyIncome(aIncome);
		System.out.println("--------------");
		aIncome.show();*/
		
		/*Cost aCost = new  Cost("20160702006", 1, aUser,
				400, "2016-07-05 09:00", "shorts",
				"TTTTTTTTTTTTT") ;*/
		//CostDao.addCost(aCost);
		/*String [] strs = new String[2];
		strs[0] = "20160702003";
		strs[1] = "20160702004";
		CostDao.deleteCosts(strs);*/
		
	}
	

	
}
