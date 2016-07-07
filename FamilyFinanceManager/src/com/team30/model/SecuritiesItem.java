package com.team30.model;

public class SecuritiesItem {

	/*
	 * 帐单号
	 */
	private String itemId;
	
	/*
	 * 证券账户
	 */
	private SecuritiesUser itemOwner;
	
	/*
	 * 股票id
	 */
	private String itemStockName;

	/*
	 * 股票价格:
	 */
	private int itemStockPrice;

	/*
	 * 交易量
	 */
	private int itemStockCount;
	
	/*
	 * 交易时间:
	 */
	private String itemTradeDate;
	
	/*
	 * 交易类型:  所有  卖出   购入
	 */
	/*public enum tradeTypes{
		tradeAll,tradeSell,tradeBuy
	}*/
	private int itemTradeType;
	
	/*
	 * SecuritiesItem构造方法
	 */
	public SecuritiesItem() {
		super();
		this.itemId = null;
		this.itemOwner = null;
		this.itemStockName = null;
		this.itemStockPrice = 0;
		this.itemStockCount = 0;
		this.itemTradeDate = null;
		this.itemTradeType = 0;
	}
	
	public SecuritiesItem(String itemId, SecuritiesUser itemOwner,
			String itemStockName, int stockPrice, int stockCount,
			String tradeDate, int tradeType) {
		super();
		this.itemId = itemId;
		this.itemOwner = itemOwner;
		this.itemStockName = itemStockName;
		this.itemStockPrice = stockPrice;
		this.itemStockCount = stockCount;
		this.itemTradeDate = tradeDate;
		this.itemTradeType = tradeType;
	}

	/*
	 * Set&Get
	 */
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public SecuritiesUser getItemOwner() {
		return itemOwner;
	}

	public void setItemOwner(SecuritiesUser itemOwner) {
		this.itemOwner = itemOwner;
	}

	public String getItemStockName() {
		return itemStockName;
	}

	public void setItemStockName(String itemStockName) {
		this.itemStockName = itemStockName;
	}

	public int getItemStockPrice() {
		return itemStockPrice;
	}

	public void setItemStockPrice(int itemStockPrice) {
		this.itemStockPrice = itemStockPrice;
	}

	public int getItemStockCount() {
		return itemStockCount;
	}

	public void setItemStockCount(int itemStockCount) {
		this.itemStockCount = itemStockCount;
	}

	public String getItemTradeDate() {
		return itemTradeDate;
	}

	public void setItemTradeDate(String itemTradeDate) {
		this.itemTradeDate = itemTradeDate;
	}

	public int getItemTradeType() {
		return itemTradeType;
	}

	public void setItemTradeType(int itemTradeType) {
		this.itemTradeType = itemTradeType;
	}
	
	public String getItemTradeTypeString(){
		switch (itemTradeType){
			case 1:
				return "买入";
			case 2:
				return "卖出";

		}
		return "";
	}

	public void setItemTradeTypeString(String typeString){
		if (typeString.equals("买入")){
			itemTradeType = 1;
		}else if (typeString.equals("卖出")){
			itemTradeType = 2;
		}
	}
	
	
	/*
	 * DateBase
	 */
	
}
