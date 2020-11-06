package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

public class LoginMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		
		String username = rs.getString("username");
		String password = rs.getString("password");
		

		return new UserInfo(username, password);
	}
	

}