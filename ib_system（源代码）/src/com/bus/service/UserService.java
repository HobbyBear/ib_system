package com.bus.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.bus.dao.model.User;

public interface UserService {

	public String login(User user) throws SQLException;
	
	public List<String> getUserAuthorityList(String userName) throws SQLException;
	
	public String save(Map<String,List<User>> dataMapList);
	
	public List<User> find(User condition) throws SQLException;
	
	public void changePass(String userName, String pass) throws SQLException;
	
	public User findUserByName(String userName) throws SQLException;

	String findUserNameByCode(String userCode) throws SQLException;

    String findCodeByName(String valueOf) throws SQLException;
}
