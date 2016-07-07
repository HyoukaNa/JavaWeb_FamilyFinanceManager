package com.team30.service;

import java.util.ArrayList;

import com.team30.dao.CostDao;
import com.team30.model.Cost;

public class CostManager {

	public static ArrayList<Cost> findCost(String name, String date) {
		
		ArrayList<Cost> lists = new ArrayList<Cost>();
		
		if( name != null && date == null) {
			lists = CostDao.findCostByCostOwnerName(name);
		} else if( name == null && date != null) {
			lists = CostDao.findCostByCostDate(date);
		} else if( name != null && date != null) {
			lists = CostDao.findCostByCostOwnerName(name);
			int max = lists.size();
			for(int i = 0;i < max;i++) {
				if(lists.get(i).getCostDate() == date){
					lists.remove(i);
					i --;
					max --;
				}
			}
		} else {
			lists = null;
		}
		return lists;
	}
	
	public static void modifyCost(Cost aCost) {
		CostDao.modifyCost(aCost);
	}
}
