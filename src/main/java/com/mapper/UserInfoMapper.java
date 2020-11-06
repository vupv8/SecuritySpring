package com.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.model.UserInfo;
import org.springframework.jdbc.core.RowMapper;

public class UserInfoMapper implements RowMapper<UserInfo> {

	@Override
	public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		int userid = rs.getInt("userid");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String fullname = rs.getString("fullname");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		String role = rs.getString("role");
		int enabled = rs.getInt("enabled");

		return new UserInfo(userid,username, password,fullname,email,phone,role,enabled);
	}
	/*
	 * @Override public String toString() { final StringBuilder sb = new
	 * StringBuilder("Car{"); sb.append("id=").append(id);
	 * sb.append(", name='").append(name).append('\'');
	 * sb.append(", price=").append(price); sb.append('}'); return sb.toString(); }
	 */
	
}