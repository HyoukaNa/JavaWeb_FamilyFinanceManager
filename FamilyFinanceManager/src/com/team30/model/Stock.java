package com.team30.model;

public class Stock {

	/*
	 * 关联证券账户:
	 */
	private SecuritiesUser stockOwner;
	
	/*
	 * 股票名称
	 */
	private String stockName;
	
	/*
	 * 股票代号 id only
	 */
	private String stockId;
	
	/*
	 * 持股数
	 */
	private int stockCount;
	
	/*
	 * 
	 */
	private int stockPrice;

	/*
	 * Stock构造方法
	 */
	public Stock() {
		
	}
	
	public Stock(SecuritiesUser stockOwner, String stockName, String stockId,
			int stockCount,int stockPrice) {
		super();
		this.stockOwner = stockOwner;
		this.stockName = stockName;
		this.stockId = stockId;
		this.stockCount = stockCount;
		this.stockPrice = stockPrice;
		this.stockOwner.stockAdd(this);
	}
	
	/*
	 * Set&Get
	 */
	public SecuritiesUser getStockOwner() {
		return stockOwner;
	}

	public void setStockOwner(SecuritiesUser stockOwner) {
		this.stockOwner = stockOwner;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public int getStockCount() {
		return stockCount;
	}

	public void setStockCount(int stockCount) {
		this.stockCount = stockCount;
	}
	
	public int getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(int stockPrice) {
		this.stockPrice = stockPrice;
	}
	/*
	 * DateBase
	 */
}
