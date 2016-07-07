package com.team30.service;

import java.util.ArrayList;
import java.util.List;

import com.team30.model.*;
import com.team30.dao.*;


public class ReportService {
	public static List<Income> queryIncome(String date,String userName){

        return IncomeDao.findIncomeByDateAndOwener(date, UserDao.findUserByUserName(userName).getIdCard());
    }

    public static List<Cost> queryCost(String date,String userName){
        return CostDao.findCostWithDateAndOwner(date, UserDao.findUserByUserName(userName).getIdCard());
    }
    
    public static List<SecuritiesItem> queryItem(String date,String userName,int type){
        List<SecuritiesUser> users = SecuritiesUserDao.findSecuritiesUserByOwner(UserDao.findUserByUserName(userName).getIdCard());
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
