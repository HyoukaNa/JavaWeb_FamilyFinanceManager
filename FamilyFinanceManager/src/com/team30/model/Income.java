package com.team30.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team30.dao.IncomeDao;


public class Income {

	/*
	 * 收入记录编号
	 */
	private String incomeId;
	
	/*
	 * 	收入类型:工资、股票、分红、奖金 0,1,2,3
	 */
	/*public enum incomeTypes{
		incomeSalary,incomeStock,incomeShare,incomeReword
	}*/
	private int incomeType;
	
	/*
	 * 	收入者
	 */
	private User incomeOwner;
	
	/*
	 * 	收入金额	
	 */
	private int incomeCount;
	
	/*
	 * 收入日期	
	 */
	private String incomeDate;
	
	/*
	 * 收入来源	
	 */
	private String incomeSource;
	
	/*
	 * 相关备注
	 */
	private String incomeDetails;
	
	/*
	 * Income 类构造方法
	 */
	public Income(){
		
	}
		
	public Income(String incomeId, int incomeType, User incomeOwner,
			int incomeCount, String incomeDate, String incomeSource,
			String incomeDetails) {
		super();
		this.incomeId = incomeId;
		this.incomeType = incomeType;
		this.incomeOwner = incomeOwner;
		this.incomeCount = incomeCount;
		this.incomeDate = incomeDate;
		this.incomeSource = incomeSource;
		this.incomeDetails = incomeDetails;
	}

	/*
	 * Set&Get
	 */
	public String getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(String incomeId) {
		this.incomeId = incomeId;
	}

	public int getIncomeType() {
		return incomeType;
	}

	public void setIncomeType(int incomeType) {
		this.incomeType = incomeType;
	}

	public User getIncomeOwner() {
		return incomeOwner;
	}

	public void setIncomeOwner(User incomeOwner) {
		this.incomeOwner = incomeOwner;
	}

	public int getIncomeCount() {
		return incomeCount;
	}

	public void setIncomeCount(int incomeCount) {
		this.incomeCount = incomeCount;
	}

	public String getIncomeDate() {
		return incomeDate;
	}

	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}

	public String getIncomeSource() {
		return incomeSource;
	}

	public void setIncomeSource(String incomeSource) {
		this.incomeSource = incomeSource;
	}

	public String getIncomeDetails() {
		return incomeDetails;
	}

	public void setIncomeDetails(String incomeDetails) {
		this.incomeDetails = incomeDetails;
	}
	
	
	public void show() {
		System.out.println("INCOMEID: " + this.getIncomeId());
		System.out.println("INCOMETYPE: " + this.getIncomeType());
		//System.out.println("INCOMEOWNER: " + this.getIncomeOwner().getIdCard());
		System.out.println("INCOMEOWNER: " + this.getIncomeOwner().getUserName());
		System.out.println("INCOMECOUNT: " + this.getIncomeCount());
		System.out.println("INCOMEDATE: " + this.getIncomeDate());
		System.out.println("INCOMESOURCE: " + this.getIncomeSource());
		System.out.println("INCOMEDETAILS: " + this.getIncomeDetails());
	}
	
	/*
	 * 数据库接口
	 */
	
	public static void addIncome(Income income) throws SQLException {
		IncomeDao.addIncome(income);
	}
	
	public static void deleteIncomes(String[] incomeList) {
		IncomeDao.deleteIncomes(incomeList);
	}
	
	public static ArrayList<Income> findAllIncomeList() {
		return IncomeDao.findAllIncomeList();
	}
	
	public static Income findIncomeByIncomeId(String incomeId) {
		return Income.findIncomeByIncomeId(incomeId);
	}
	
	public static void modifyIncome(Income income) {
		Income.modifyIncome(income);
	}
}
