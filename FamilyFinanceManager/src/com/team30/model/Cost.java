package com.team30.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team30.dao.CostDao;


public class Cost {

	/*
	 * 支出记录编号
	 */
	private String costId;
	
	/*
	 * 	支出类型:税收、衣食住行、医疗、其他
	 */
	/*public enum costTypes{
		costTax,costLife,costMedical,costOthers
	}*/
	private int costType;
	
	/*
	 * 	支出者
	 */
	private User costOwner;
	
	/*
	 *	支出金额	
	 */
	private int costCount;
	
	/*
	 * 支出日期	
	 */
	private String costDate;
	
	/*
	 * 支出来源	
	 */
	private String costSource;
	
	/*
	 * 支出相关备注
	 */
	private String costDetails;

	/*
	 * Cost类构造方法
	 */
	public Cost() {
		
	}
	
	public Cost(String costId, int costType, User costOwner,
			int costCount, String costDate, String costSource,
			String costDetails) {
		super();
		this.costId = costId;
		this.costType = costType;
		this.costOwner = costOwner;
		this.costCount = costCount;
		this.costDate = costDate;
		this.costSource = costSource;
		this.costDetails = costDetails;
	}

	/*
	 * Set&Get
	 */
	public String getCostId() {
		return costId;
	}

	public void setCostId(String costId) {
		this.costId = costId;
	}

	public int getCostType() {
		return costType;
	}

	public void setCostType(int costType) {
		this.costType = costType;
	}

	public User getCostOwner() {
		return costOwner;
	}

	public void setCostOwner(User costOwner) {
		this.costOwner = costOwner;
	}

	public int getCostCount() {
		return costCount;
	}

	public void setCostCount(int costCount) {
		this.costCount = costCount;
	}

	public String getCostDate() {
		return costDate;
	}

	public void setCostDate(String costDate) {
		this.costDate = costDate;
	}

	public String getCostSource() {
		return costSource;
	}

	public void setCostSource(String costSource) {
		this.costSource = costSource;
	}

	public String getCostDetails() {
		return costDetails;
	}

	public void setCostDetails(String costDetails) {
		this.costDetails = costDetails;
	}
	
	public void show() {
		System.out.println("COSTID: " + this.getCostId());
		System.out.println("COSTTYPE: " + this.getCostType());
		System.out.println("COSTOWNER: " + this.getCostOwner().getIdCard());
		System.out.println("COSTCOUNT: " + this.getCostCount());
		System.out.println("COSTDATE: " + this.getCostDate());
		System.out.println("COSTSOURCE: " + this.getCostSource());
		System.out.println("COSTDETAILS: " + this.getCostDetails());
		
	}
	
	/*
	 * 数据库接口
	 */
	public static boolean addCost(Cost cost) throws SQLException {
		return CostDao.addCost(cost);
	}
	
	public static void deleteCosts(String[] costList) {
		CostDao.deleteCosts(costList) ;
	}
	
	public static ArrayList<Cost> findAllCostList() {
		return CostDao.findAllCostList();
	}
	
	public static Cost findCostByCostId(String costId) {
		return CostDao.findCostByCostId(costId);
	}
	
	public static void modifyCost(Cost cost) {
		CostDao.modifyCost(cost);
	}
	
}
