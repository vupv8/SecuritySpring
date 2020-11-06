package com.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import com.dao.UserInfoDAO;
import com.mapper.LoginMapper;
import com.mapper.UserInfoMapper;
import com.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {

	@Autowired
	public UserInfoDAOImpl(DataSource dataSource) {
		this.setDataSource(dataSource);
	}

	@Override
	public UserInfo findUserInfo(String username) {
		String sql = "Select username,password "//
				+ " from Users where username = ? ";

		Object[] params = new Object[] { username };
		LoginMapper mapper = new LoginMapper();
		try {
			UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public List<String> getUserRoles(String username) {
		String sql = "Select Role "//
				+ " from users where username = ? ";

		Object[] params = new Object[] { username };

		List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

		return roles;
	}

	
	@Override
	public List<UserInfo> getAllInfoUser() {
		String sql = "Select * "//
				+ " from users ";

		UserInfoMapper mapper = new UserInfoMapper();
		try {
			List<UserInfo> userInfo = this.getJdbcTemplate().query(sql, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public UserInfo getInfoUserByUserid(int userid) {
		String sql = "Select * "//
				+ " from users where userid=? ";

		Object[] params = new Object[] { userid };
		UserInfoMapper mapper = new UserInfoMapper();
		try {
			UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	@Override
	public UserInfo getInfoUserByUsername(String username) {
		String sql = "Select * "//
				+ " from users where username=? ";

		Object[] params = new Object[] { username };
		UserInfoMapper mapper = new UserInfoMapper();
		try {
			UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
			return userInfo;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}
	@Override
	public void setUpdateUser(UserInfo userInfo, Integer userid) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (userInfo.getPassword().isEmpty()) {
			String sql = "UPDATE users SET username=?, fullname=?, "
					+ "email=?, phone=?, role=?, enabled=? WHERE userid=?";
			Object[] params = new Object[] { userInfo.getUsername(), userInfo.getFullname(), userInfo.getEmail(),
					userInfo.getPhone(), userInfo.getRole(), userInfo.getEnabled(), userid };
			System.out.print(userInfo.getEnabled());
			try {
				this.getJdbcTemplate().update(sql, params);

			} catch (EmptyResultDataAccessException e) {

			}
		} else {
			String sql = "UPDATE users SET username=?, password=?, fullname=?, "
					+ "email=?, phone=?, role=?,enabled=? WHERE userid=?";
			Object[] params = new Object[] { userInfo.getUsername(), encoder.encode(userInfo.getPassword()), userInfo.getFullname(),
					userInfo.getEmail(), userInfo.getPhone(), userInfo.getRole(), userInfo.getEnabled(), userid };
			try {
				this.getJdbcTemplate().update(sql, params);

			} catch (EmptyResultDataAccessException e) {

			}
		}

	}

	@Override
	public void setAddUser(UserInfo userInfo) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String sql = "INSERT users SET username=?, password=?, fullname=?, email=?, phone=?, role=?, enabled=?";

		Object[] params = new Object[] { userInfo.getUsername(), encoder.encode(userInfo.getPassword()),
				userInfo.getFullname(), userInfo.getEmail(), userInfo.getPhone(), userInfo.getRole(),
				userInfo.getEnabled() };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (EmptyResultDataAccessException e) {

		}
	}
	@Override
	public void setDelUser(int userid) {
		String sql = "DELETE from users where userid=? ";

		Object[] params = new Object[] { userid };
		try {
			this.getJdbcTemplate().update(sql, params);
			
		} catch (EmptyResultDataAccessException e) {
			
		}

	}
	@Override
	public void setSaveInfo(UserInfo userInfo,String username) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String sql = "UPDATE users SET  password=?, "
				+ "email=?, phone=? WHERE username=?";
		Object[] params = new Object[] {  encoder.encode(userInfo.getPassword()),
				userInfo.getEmail(), userInfo.getPhone(), username };
		try {
			this.getJdbcTemplate().update(sql, params);

		} catch (EmptyResultDataAccessException e) {

		}
	}
}