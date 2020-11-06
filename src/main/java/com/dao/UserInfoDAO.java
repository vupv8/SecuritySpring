package com.dao;

import java.util.List;

import com.model.UserInfo;

public interface UserInfoDAO {

	public UserInfo findUserInfo(String userName);

	// [USER,AMIN,..]
	public List<String> getUserRoles(String userName);


	// get toan bo user info
	public List<UserInfo> getAllInfoUser();

	// get user info theo username
	public UserInfo getInfoUserByUserid(int userid);
	
	public UserInfo getInfoUserByUsername(String username);
	
	public void setUpdateUser(UserInfo userInfo, Integer userid );
	
	public void setAddUser(UserInfo userInfo);
	
	public void setDelUser(int userid);
	
	public void setSaveInfo(UserInfo userInfo,String username);
	
}