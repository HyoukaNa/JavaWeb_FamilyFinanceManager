package com.team30.service;

import java.util.ArrayList;

import com.team30.dao.UserDao;
import com.team30.model.User;

public class UserManager {

	public static ArrayList<User> findUser(String name, String idcard) {
		
		ArrayList<User> lists = new ArrayList<User>();
		
		if( name != null) {
			lists.add(UserDao.findUserByUserName(name));
		} else if( name == null && idcard != null) {
			lists.add(UserDao.findUserByIdCard(idcard));
		} else if( name != null && idcard != null) {
			lists.add(UserDao.findUserByUserName(name));
			if(lists.get(0).getIdCard() != idcard) 
				lists.remove(0);
		} else {
			lists = null;
		}
		return lists;
	}
}
