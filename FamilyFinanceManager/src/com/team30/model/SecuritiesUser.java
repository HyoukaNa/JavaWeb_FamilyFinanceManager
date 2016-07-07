package com.team30.model;

import java.util.ArrayList;

public class SecuritiesUser {

	/*
	 * 证券账户类型
	 */
	/*public enum securitiesUserTypes{
		shangA,shangB,shenA,shenB
	}*/
	private int securitiesUserType;
	
	/*
	 * 账号id
	 */
	private String securitiesUserId;
	
	/*
	 * 账号余额
	 */
	private int securitiesUserRest;
	
	/*
	 * 持有股票列
	 */
	private ArrayList<Stock> stockLists = new ArrayList<Stock>();
	
	/*
	 * 所关联用户
	 */
	private User securitiesUserOwner;

	public SecuritiesUser( ){
		this.stockLists = new ArrayList<Stock>();
	}
	
	public SecuritiesUser(int securitiesUserType,
			String securitiesUserId, int securitiesUserRest,
			User securitiesUserOwner) {
		super();
		this.securitiesUserType = securitiesUserType;
		this.securitiesUserId = securitiesUserId;
		this.securitiesUserRest = securitiesUserRest;
		this.stockLists = new ArrayList<Stock>();
		this.securitiesUserOwner = securitiesUserOwner;
		this.securitiesUserOwner.securitiesUserAdd(this);
	}

	public int getSecuritiesUserType() {
		return securitiesUserType;
	}

	public void setSecuritiesUserType(int securitiesUserType) {
		this.securitiesUserType = securitiesUserType;
	}

	public String getSecuritiesUserId() {
		return securitiesUserId;
	}

	public void setSecuritiesUserId(String securitiesUserId) {
		this.securitiesUserId = securitiesUserId;
	}

	public int getSecuritiesUserRest() {
		return securitiesUserRest;
	}

	public void setSecuritiesUserRest(int securitiesUserRest) {
		this.securitiesUserRest = securitiesUserRest;
	}

	public ArrayList<Stock> getStockLists() {
		return stockLists;
	}

	public void setStockLists(ArrayList<Stock> stockLists) {
		this.stockLists = stockLists;
	}

	public User getSecuritiesUserOwner() {
		return securitiesUserOwner;
	}

	public void setSecuritiesUserOwner(User securitiesUserOwner) {
		this.securitiesUserOwner = securitiesUserOwner;
	}
	
	/*
	 * 该SecuritiesUser账户下的所有Stock管理(增加，删除，查询)
	 */
	public Stock stockSearch(String stockId) {
		Stock aStock = new Stock();
		for(int i = 0;i < this.stockLists.size();i ++) {
			aStock = this.stockLists.get(i);
			if( aStock.getStockId() == stockId ) {
				return aStock;
			}
		}
		return null;
	}
	
	public void stockAdd(Stock aStock) {
		this.stockLists.add(aStock);
	}

	public void stockDelete(String stockId) {
		for(int i = 0;i < this.stockLists.size();i ++) {
			if( this.stockLists.get(i).getStockId() == stockId ) {
				this.stockLists.remove(i);
				break;
			}
		}
	}
	
	/*
	 * DateBase
	 */
	
	
}
