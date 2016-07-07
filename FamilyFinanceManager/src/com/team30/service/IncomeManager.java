package com.team30.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import com.team30.dao.IncomeDao;
import com.team30.model.Income;

public class IncomeManager {

	public static ArrayList<Income> findIncome(String name, String date) {
		
		ArrayList<Income> lists = new ArrayList<Income>();
		
		if( name != null && date == null) {
			lists = IncomeDao.findIncomeByIncomeOwnerName(name);
		} else if( name == null && date != null) {
			lists = IncomeDao.findIncomeByIncomeDate(date);
		} else if( name != null && date != null) {
			lists = IncomeDao.findIncomeByIncomeOwnerName(name);
			int max = lists.size();
			for(int i = 0;i < max;i++) {
				if(lists.get(i).getIncomeDate() == date){
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
	
	public static void modifyIncome(Income aIncome) {
		IncomeDao.modifyIncome(aIncome);
	}
	
	/*public static void addIncome(Income aIncome) 
			throws SQLException {
		Random rand = new Random();
		String id;
		for(;true;) {
			id = String.valueOf(1000 + rand.nextInt(8999));
			if(IncomeDao.findIncomeByIncomeId(id) == null)
				break;
		}
		aIncome.setIncomeId(id);
		IncomeDao.addIncome(aIncome);
	}*/
}
