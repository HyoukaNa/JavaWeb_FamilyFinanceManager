package com.team30.model;

import java.sql.SQLException;
import java.util.ArrayList;

import com.team30.dao.UserDao;

public class User {

	/*
	 * 用户姓名
	 */
	private String userName;
	
	/*
	 * 用户密码
	 */
	private String password;	
	
	/*
	 * 银行帐号
	 */
	private String creditCard;
	
	/*
	 * 用户工资(月薪)
	 */
	private int salary;
	
	/*
	 * 家庭称呼
	 */
	private String familyCall;	
	
	/*
	 * 用户年龄
	 */
	private int age;
	
	/*
	 * 身份证号
	 */
	private String idCard;
	
	/*
	 * 用户权限
	 */
	private boolean isAdmin;
	
	/*
	 * SecuritiesUser队列
	 */
	private ArrayList<SecuritiesUser> securitiesUserLists;

	/*
	 * 类构造方法
	 */
	public User() {
		super();
		this.userName = null;
		this.password = null;
		this.creditCard = null;
		this.salary = 0;
		this.familyCall = null;
		this.age = 0;
		this.idCard = null;
		this.isAdmin = false;
		this.securitiesUserLists = new ArrayList<SecuritiesUser>();
	}
	
	public User(String userName, String password, String creditCard,
			int salary, String familyCall, int age, String idCard,
			boolean isAdmin) {
		super();
		this.userName = userName;
		this.password = password;
		this.creditCard = creditCard;
		this.salary = salary;
		this.familyCall = familyCall;
		this.age = age;
		this.idCard = idCard;
		this.isAdmin = isAdmin;
		this.securitiesUserLists = new ArrayList<SecuritiesUser>();
	}

	
	/*
	 * Set&Get
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getFamilyCall() {
		return familyCall;
	}

	public void setFamilyCall(String familyCall) {
		this.familyCall = familyCall;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public boolean isAdmin() {
		return isAdmin;
	}


	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	/*
	 * 该User账户下的所有SecuritiesUser管理(增加，删除，查询)
	 */
	public SecuritiesUser securitiesUserSearch(String SecuritiesUserId) {
		SecuritiesUser aSecuritiesUser = new SecuritiesUser();
		for(int i = 0;i < this.securitiesUserLists.size();i ++) {
			aSecuritiesUser = this.securitiesUserLists.get(i);
			if( aSecuritiesUser.getSecuritiesUserId() == SecuritiesUserId ) {
				return aSecuritiesUser;
			}
		}
		return null;
	}
	
	public void securitiesUserAdd(SecuritiesUser aSecuritiesUser) {
		this.securitiesUserLists.add(aSecuritiesUser);
	}

	public void securitiesUserDelete(String SecuritiesUserId) {
		for(int i = 0;i < this.securitiesUserLists.size();i ++) {
			if( this.securitiesUserLists.get(i).getSecuritiesUserId() == SecuritiesUserId ) {
				this.securitiesUserLists.remove(i);
				break;
			}
		}
	}
	
	public void show() {
		System.out.println("userName:" + userName); 
		System.out.println("password:" + password); 
		System.out.println("creditCard:" + creditCard); 
		System.out.println("salary:" + salary); 
		System.out.println("familyCall:" + familyCall); 
		System.out.println("age:" + age); 
		System.out.println("idCard:" + idCard); 
		System.out.println("isAdmin:" + isAdmin); 
	}

	/*
	 * 数据库接口
	 */
	public static boolean addUser(User user) throws SQLException {
		return UserDao.addUser(user);
	}
	
	public static void deleteUsers(String[] idCradList) {
		UserDao.deleteUsers(idCradList);
	}
	
	public static ArrayList<User> findAllUserList() {
		return UserDao.findAllUserList();
	}
	
	public static User findUserByIdCard(String idCard) {
		return UserDao.findUserByIdCard(idCard);
	}
	
	public static void modifyUser(User user) {
		UserDao.modifyUser(user);
	}
	
	public static User findUserByUserName(String userName) {
		return UserDao.findUserByUserName(userName) ;
	}
}
