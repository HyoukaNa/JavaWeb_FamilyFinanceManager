package com.team30.service;

import java.util.ArrayList;
import java.util.List;

import com.team30.model.*;
import com.team30.dao.*;


public class ReportService {
	public static ArrayList<Income> queryIncome(String date,String userID){

        return IncomeDao.findIncomeByDateAndOwener(date, userID);
    }

    public static ArrayList<Cost> queryCost(String date,String userID){
        return CostDao.findCostWithDateAndOwner(date, userID);
    }
    
    public static ArrayList<SecuritiesItem> queryItem(String date,String userID,int type){
        List<SecuritiesUser> users = SecuritiesUserDao.findSecuritiesUserByOwner(userID);
        ArrayList<SecuritiesItem> items = new ArrayList<SecuritiesItem>();
        for (SecuritiesUser user:
             users) {
            List<SecuritiesItem> result = SecuritiesItemDao.findItemListWithDateAndOwner(date,user.getSecuritiesUserId(),type);
            for (SecuritiesItem item:
                 result) {
                items.add(item);
            }
        }
        return items;
    }
}
