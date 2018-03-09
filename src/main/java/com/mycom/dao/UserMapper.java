package com.mycom.dao;

public interface UserMapper {
	
	//传入username返回password
	String selectPassword(String username);

}
